package uk.undev;

import org.bukkit.plugin.java.JavaPlugin;
import uk.undev.listeners.AllaySpawnListener;
import uk.undev.listeners.BlazeAttackListener;
import uk.undev.listeners.BlazeDeathListener;

public final class xBolt extends JavaPlugin {

    private static xBolt instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        // getServer().getPluginManager().registerEvents(new BlazeAttackListener(), this);
        // getServer().getPluginManager().registerEvents(new BlazeDeathListener(), this);
        getServer().getPluginManager().registerEvents(new AllaySpawnListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static xBolt getInstance() {
        return instance;
    }

}
