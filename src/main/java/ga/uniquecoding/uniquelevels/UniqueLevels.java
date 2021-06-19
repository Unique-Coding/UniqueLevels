package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquecore.command.CommandManager;
import ga.uniquecoding.uniquecore.command.CommandModule;
import ga.uniquecoding.uniquelevels.modules.levels.LevelsModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class UniqueLevels extends JavaPlugin {

    private final CommandManager commandManager = CommandManager.getInstance();

    @Override
    public void onEnable() {
    /*
        All the enable stuff.
    */
        this.saveDefaultConfig();
        this.reloadConfig();
        this.registerCommands();
    }

    private void registerCommands() {
        var storageFolder = new File(getDataFolder() + "/data/");

        CommandModule[] modules = {
                new LevelsModule(this, storageFolder)
        };

        for (CommandModule m : modules)
            commandManager.register(m.getCommands());
    }

    /*
        Reload the configuration and other stuff.
    */
    public void reload() {
        this.reloadConfig();
    }
}
