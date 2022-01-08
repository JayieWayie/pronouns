package me.jayie.pronouns.listeners;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.jayie.pronouns.Pronouns;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class placeholders extends PlaceholderExpansion {

    private Pronouns plugin;
    public placeholders(Pronouns plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public @NotNull String getAuthor() {
        return "Jayie";
    }
    @Override
    public @NotNull String getIdentifier() {
        return "pronouns";
    }
    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }


    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if (identifier.equals("set")) {
            String pronounsChoice = null;
            try {
                PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT pronounsSet FROM pronouns WHERE playerUUID=?");
                ps1.setString(1, p.getUniqueId().toString());
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()){
                    pronounsChoice = rs1.getString("pronounsSet");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "" + pronounsChoice;
        }

        return null;
    }

}
