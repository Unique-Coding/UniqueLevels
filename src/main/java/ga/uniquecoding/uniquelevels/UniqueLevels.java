package ga.uniquecoding.uniquelevels;

import ga.uniquecoding.uniquelevels.commands.MainCommand;
import ga.uniquecoding.uniquelevels.events.PlayerInitializer;
import org.bukkit.plugin.java.JavaPlugin;


public final class UniqueLevels extends JavaPlugin {

    @Override
    public void onEnable() {
    /*
        All the enable stuff.
    */
        this.load();
        this.registerEvents();
        this.registerCommands();
    }

    /*
        Reload the configuration and other stuff.
    */
    public void reload() {
        this.reloadConfig();
    }
    /*
        The loading stuff.
    */
    public void load() {
        getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
    /*
        Registering all the events.
    */
    public void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerInitializer(), this);
    }
    /*
        Registering all the commands.
    */
    public void registerCommands() {
        this.getCommand("UniqueLevels").setExecutor(new MainCommand(this));
    }
}
