package cn.pixelwar.pwpayment;

import cn.pixelwar.pwpayment.SQL.ConnectionPool;
import cn.pixelwar.pwpayment.SQL.Execution;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class PWPayment extends JavaPlugin {
    public static FileConfiguration config;
    private static Plugin plugin;
    static PWPayment instance;
    @Override
    public void onEnable() {
        setupConfig();
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

    public void checkSQL(){
        //建立连接池
        ConnectionPool connectionPool = new ConnectionPool();
        hikariDataSource = connectionPool.getHikariDataSource();
        try {
            hikariDataSource.getConnection();
            Execution execution = new Execution();
            Connection connection = PWPayment.hikariDataSource.getConnection();
            execution.createTables(connection);
            System.out.println("[PWPayment] 连接获取成功!");
        }catch (SQLException exception){
            System.out.println("[PWPayment] 连接获取失败!");
        }

    }


}
