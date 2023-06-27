package cn.pixelwar.pwpayment;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PWPayment extends JavaPlugin {
    public static FileConfiguration config;
    private static Plugin plugin;
    static PWPayment instance;
    @Override
    public void onEnable() {
        setupConfig();
    }
    public static PWPayment getInstance() {
        return instance;
    }
    public static Plugin getPlugin() {
        return plugin;
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
    }


}
