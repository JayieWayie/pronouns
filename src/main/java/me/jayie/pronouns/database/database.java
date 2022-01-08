package me.jayie.pronouns.database;

import me.jayie.pronouns.Pronouns;
import org.bukkit.plugin.Plugin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class database {

    Plugin plugin = Pronouns.getPlugin(Pronouns.class);


    private Connection connection;

    public boolean isConnected(){
        return(connection != null);
    }

    public void connect() throws SQLException {
        String host = plugin.getConfig().getString("Address");
        String port = plugin.getConfig().getString("Port");
        String user = plugin.getConfig().getString("Username");
        String pass = plugin.getConfig().getString("Password");
        String database = plugin.getConfig().getString("Database");

            connection = DriverManager.getConnection("jdbc:mysql://" + URLEncoder.encode(user, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(pass, StandardCharsets.UTF_8) + "@" + host + ":" + port + "/" + database + "?autoReconnect=true");

    }

    public void disconnect(){
        if (isConnected()){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }



}
