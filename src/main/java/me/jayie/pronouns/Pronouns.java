package me.jayie.pronouns;

import me.jayie.pronouns.commands.pronounsAdmin;
import me.jayie.pronouns.commands.pronounsDefault;
import me.jayie.pronouns.database.database;
import me.jayie.pronouns.database.databaseQueries;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Pronouns extends JavaPlugin implements Listener {

    public database DB;
    public databaseQueries DBQ;

    @Override
    public void onEnable() {
        // Plugin startup logic
        startupMessage();
        commands();
        listeners();
        database();
        if (!DB.isConnected()){
            try {
                DB.connect();
            } catch (SQLException e) {
                getLogger().severe(Color("&8[&6Pronouns&8] &4Database needs connecting."));
            }
        }else{
            try {
                DBQ.createTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void commands(){
        // Execute all command classes.
        getCommand("pronouns").setExecutor(new pronounsDefault());
        getCommand("apronouns").setExecutor(new pronounsAdmin());
    }



    public void listeners(){
        // Execute all listener classes.
        getServer().getPluginManager().registerEvents(this, this);

    }

    public void database(){
        this.DB = new database();
        this.DBQ = new databaseQueries(this);
    }


    public void startupMessage(){
        // Startup Message
        getLogger().info(Color("&8[&cPronouns&8] &aPlugin Started Correctly."));
        getLogger().info(" - + - &7Created by Jayie. - + - ");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(Color("&8[&cPronouns&8] &aPlugin Closed."));
        getLogger().info("&7Thank you for choosing my plugin!");
        DB.disconnect();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();
        DBQ.createPlayer(player);
        player.sendMessage("hello");
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
