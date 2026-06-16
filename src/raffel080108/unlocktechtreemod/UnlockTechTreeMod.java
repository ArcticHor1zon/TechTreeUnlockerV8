package raffel080108.unlocktechtreemod;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.type.*;
import mindustry.ui.dialogs.*;

/**
 * UnlockTechTreeMod – updated for Mindustry v8 (build 158).
 */
public class UnlockTechTreeMod extends Mod {

    private static final String KEY_AUTO_UNLOCK = "uttm-auto-unlock";

    @Override
    public void init() {
        Events.on(WorldLoadBeginEvent.class, e -> {
            if (Core.settings.getBool(KEY_AUTO_UNLOCK, false) && Vars.state.isCampaign()) {
                Core.app.post(() -> {
                    int n = unlockAll(null);
                    if (n > 0) Log.info("[UnlockTechTreeMod] Auto-unlocked @ research nodes.", n);
                });
            }
        });

        Events.on(ClientLoadEvent.class, e -> registerSettings());
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {

        handler.register("unlockall", "[planet]",
                "Unlock all tech-tree research. planet = serpulo | erekir | all (default: all)",
                args -> {
                    Planet filter = args.length > 0 ? parsePlanet(args[0]) : null;
                    int count = unlockAll(filter);
                    Log.info("[UnlockTechTreeMod] Unlocked @ node(s).", count);
                    if (count == 0) Log.info("[UnlockTechTreeMod] Nothing new to unlock.");
                });

        handler.register("lockall", "[planet]",
                "Reset all tech-tree research. planet = serpulo | erekir | all (default: all)",
                args -> {
                    Planet filter = args.length > 0 ? parsePlanet(args[0]) : null;
                    int count = lockAll(filter);
                    Log.info("[UnlockTechTreeMod] Reset @ node(s).", count);
                });
    }

    // ---------------------------------------------------------------
    // Settings UI
    // ---------------------------------------------------------------

    private void registerSettings() {
        try {
            Vars.ui.settings.addCategory("Unlock Tech Tree", t -> {

                t.checkPref(KEY_AUTO_UNLOCK, false);

                t.row();

                t.button("Unlock All  (Serpulo + Erekir)", () -> {
                    int n = unlockAll(null);
                    showResult("Unlocked [green]" + n + "[] research nodes.");
                }).pad(4).fillX().row();

                t.button("Unlock Serpulo Only", () -> {
                    int n = unlockAll(Planets.serpulo);
                    showResult("Unlocked [green]" + n + "[] Serpulo nodes.");
                }).pad(4).fillX().row();

                t.button("Unlock Erekir Only", () -> {
                    int n = unlockAll(Planets.erekir);
                    showResult("Unlocked [green]" + n + "[] Erekir nodes.");
                }).pad(4).fillX().row();

                t.row();

                t.button("[scarlet]Reset All Research[]", () ->
                    Vars.ui.showConfirm(
                        "[scarlet]Reset ALL research?[]\nThis cannot be undone.",
                        () -> {
                            int n = lockAll(null);
                            showResult("Reset [red]" + n + "[] research nodes.");
                        }
                    )
                ).pad(4).fillX().row();

                t.button("[scarlet]Reset Serpulo Only[]", () ->
                    Vars.ui.showConfirm(
                        "[scarlet]Reset Serpulo research?[]",
                        () -> {
                            int n = lockAll(Planets.serpulo);
                            showResult("Reset [red]" + n + "[] Serpulo nodes.");
                        }
                    )
                ).pad(4).fillX().row();

                t.button("[scarlet]Reset Erekir Only[]", () ->
                    Vars.ui.showConfirm(
                        "[scarlet]Reset Erekir research?[]",
                        () -> {
                            int n = lockAll(Planets.erekir);
                            showResult("Reset [red]" + n + "[] Erekir nodes.");
                        }
                    )
                ).pad(4).fillX();
            });
        } catch (Exception ex) {
            Log.warn("[UnlockTechTreeMod] Could not register settings panel: @", ex.getMessage());
        }
    }

    private void showResult(String msg) {
        Core.app.post(() -> {
            try {
                Vars.ui.settings.hide();
            } catch (Exception ignored) {}
            Vars.ui.showInfo(msg);
        });
    }

    // ---------------------------------------------------------------
    // Core logic
    // ---------------------------------------------------------------

    public static int unlockAll(@Nullable Planet planetFilter) {
        int count = 0;

        for (TechTree.TechNode node : TechTree.all) {
            if (planetFilter != null && node.planet != planetFilter) continue;
            count += unlockNode(node);
        }

        // Flush all settings to disk in one shot
        Core.settings.saveValues();
        return count;
    }

    public static int lockAll(@Nullable Planet planetFilter) {
        int count = 0;

        for (TechTree.TechNode node : TechTree.all) {
            if (planetFilter != null && node.planet != planetFilter) continue;
            // Reset finished requirements
            node.reset();
            // Lock the content directly via settings
            Core.settings.put(node.content.name + "-unlocked", false);
            // Also clear the in-memory unlocked flag
            node.content.clearUnlock();
            count++;
        }

        Core.settings.saveValues();
        return count;
    }

    // ---------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------

    /**
     * Unlock a single node.
     * Bypasses objective checks by writing directly to settings and
     * calling unlock() which sets the in-memory flag + fires UnlockEvent.
     */
    private static int unlockNode(TechTree.TechNode node) {
        // Fill finished requirements so the research screen shows it as paid
        for (int i = 0; i < node.requirements.length; i++) {
            node.finishedRequirements[i].amount = node.requirements[i].amount;
        }
        node.save(); // persist requirement progress

        // unlock() is a no-op if already unlocked, so safe to call always
        if (!node.content.unlocked()) {
            node.content.unlock();
            return 1;
        }
        return 0;
    }

    @Nullable
    private static Planet parsePlanet(@Nullable String arg) {
        if (arg == null) return null;
        return switch (arg.toLowerCase()) {
            case "serpulo" -> Planets.serpulo;
            case "erekir"  -> Planets.erekir;
            default        -> null;
        };
    }
}
