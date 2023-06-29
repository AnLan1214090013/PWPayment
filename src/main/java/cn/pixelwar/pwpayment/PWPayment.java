package cn.pixelwar.pwpayment;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwpayment.SQL.ConnectionPool;
import cn.pixelwar.pwpayment.SQL.Execution;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public final class PWPayment extends JavaPlugin {
    public static FileConfiguration config;
    SkriptAddon addon;
    public static ConnectionPool connectionPool;
    private static Plugin plugin;
    static PWPayment instance;
    @Override
    public void onEnable() {
        setupConfig();
        setupSK();
        checkSQL();
    }
    public static PWPayment getInstance() {
        return instance;
    }
    public static Plugin getPlugin() {
        return plugin;
    }

    public static HikariDataSource hikariDataSource;

    @Override
    public void onDisable() {
        // Plugin shutdown logic




    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
    }

    public void setupSK(){
        this.addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("cn.pixelwar.pwpayment");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PWPayment-skript] 已经成功启动!");
    }

    public void checkSQL(){
        //建立连接池
        connectionPool = new ConnectionPool();
        hikariDataSource = connectionPool.getHikariDataSource();
        try {
            hikariDataSource.getConnection();
            Execution execution = new Execution();
            Connection connection = PWPayment.hikariDataSource.getConnection();
            connectionPool.createTables();
            Bukkit.getLogger().info("[PWPayment] 连接获取成功!");
        }catch (SQLException exception){
            Bukkit.getLogger().info("[PWPayment] 连接获取失败!");
        }

    }


}
