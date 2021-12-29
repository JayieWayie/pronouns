package me.jayie.pronouns.database;

import me.jayie.pronouns.Pronouns;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class databaseQueries {

    private Pronouns plugin;
    public databaseQueries(Pronouns plugin){
        this.plugin = plugin;
    }


    public void createTable() throws SQLException {
            PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXIST pronouns(playerUUID varchar(255), pronounsSet varchar(100), colorSet varchar(255), PRIMARY KEY(playerUUID))");
            table.executeUpdate();
    }

    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT INTO pronouns (playerUUID, pronounsSet, colorSet) VALUES (?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setString(2, "N/A");
            createPlayer.setString(3, "WHITE");
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM pronouns WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

}
