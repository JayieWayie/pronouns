package me.jayie.pronouns;

import me.jayie.pronouns.commands.pronounsAdmin;
import me.jayie.pronouns.commands.pronounsDefault;
import me.jayie.pronouns.database.database;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pronouns extends JavaPlugin {



    @Override
    public void onEnable() {
        // Plugin startup logic
        startupMessage();
        commands();
        listeners();
        this.DB();
    }


    public void commands(){
        // Execute all command classes.
        getCommand("pronouns").setExecutor(new pronounsDefault());
        getCommand("apronouns").setExecutor(new pronounsAdmin());
    }



    public void listeners(){
        // Execute all listener classes.

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
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
