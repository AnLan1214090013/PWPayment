package cn.pixelwar.pwpayment.SQL;

import cn.pixelwar.pwpayment.PWPayment;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ConnectionPool {

    private final HikariDataSource hikariDataSource;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = PWPayment.config.getString("mysql.username");
    private final String PASSWORD = PWPayment.config.getString("mysql.password");
    private final String IP = PWPayment.config.getString("mysql.address");
    private final String PORT = PWPayment.config.getString("mysql.port");
    private final String DATABASE = PWPayment.config.getString("mysql.database");
    private final String URL = "jdbc:mysql://"+IP+":"+PORT+"/"+DATABASE+"?charactorEncoding=utf-8&useSSL=false";
    private final long connectionTimeout = PWPayment.config.getLong("mysql.pool.connectionTimeout");
    private final long connectionIdleTimeout = PWPayment.config.getLong("mysql.pool.connectionIdleTimeout");
    private final int maxConnectionAmount = PWPayment.config.getInt("mysql.pool.maxConnectionAmount");
    public ConnectionPool(){
        this.hikariDataSource = new HikariDataSource();
        this.hikariDataSource.setDriverClassName(DRIVER);
        this.hikariDataSource.setJdbcUrl(URL);
        this.hikariDataSource.setUsername(USER);
        this.hikariDataSource.setPassword(PASSWORD);
        this.hikariDataSource.setPoolName("PWPayment");
        this.hikariDataSource.setConnectionTimeout(connectionTimeout);
        this.hikariDataSource.setIdleTimeout(connectionIdleTimeout);
        this.hikariDataSource.setMaximumPoolSize(maxConnectionAmount);
        this.hikariDataSource.setMinimumIdle(maxConnectionAmount);
    }





    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }



    public void createTables(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getHikariDataSource().getConnection();
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `player_rank`(`uuid` VARCHAR(100) NOT NULL, `playername` VARCHAR(100) NOT NULL, `rank` VARCHAR(100) NOT NULL, PRIMARY KEY ( `uuid` ))");
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(preparedStatement,connection,null);
        }
    }

    public void addPlayerRank(UUID uuid, String playerName, String rank){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getHikariDataSource().getConnection();
            preparedStatement = connection.prepareStatement("REPLACE INTO player_rank (uuid, playername, rank) VALUES ('"+uuid+"', '"+playerName+"', '"+rank+"')");
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(preparedStatement,connection,null);
        }
    }
    public String getPlayerRank(UUID uuid, String playerName){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getHikariDataSource().getConnection();
            preparedStatement = connection.prepareStatement("SELECT playername,rank FROM player_rank WHERE uuid = '"+uuid+"'");
            resultSet = preparedStatement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }

        if (resultSet==null){
            closeConnection(preparedStatement,connection,null);
            return "none";
        }
        //判断名字是否一样
        String name = null;
        String rank = "none";
        try {
            while (resultSet.next()) {
//                Bukkit.broadcastMessage(resultSet.getString(1));
//                Bukkit.broadcastMessage(resultSet.getString(2));
//                Bukkit.broadcastMessage(resultSet.getString(3));
                name = resultSet.getString(1);
                rank = resultSet.getString(2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (!(name.equals(playerName))){
            closeConnection(preparedStatement,connection,null);
            return "none";
        }
        //返回rank
        closeConnection(preparedStatement,connection,null);
        return rank;

    }


    public void closeConnection(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet){
        try{
            if(!(connection.isClosed())) {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                connection.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (NullPointerException e2){
            e2.printStackTrace();

        }
    }

}
