package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquelevels.commands.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class UniqueLevels extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading...");

        //Copies the default config.yml from within the .jar if "plugins/UniqueLevels/config.yml" does not exist
        getConfig().options().copyDefaults(true);

        getLogger().info("Initializing commands...");

        this.getCommand("UniqueLevels").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
