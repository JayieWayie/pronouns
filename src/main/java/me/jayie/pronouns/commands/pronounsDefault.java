package me.jayie.pronouns.commands;

import me.jayie.pronouns.Pronouns;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class pronounsDefault implements CommandExecutor {

    private Pronouns plugin;
    public pronounsDefault(Pronouns plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String invalidInputsem = plugin.getConfig().getString("InvalidInputs");
        String invalidArgumentem = plugin.getConfig().getString("InvalidArgument");
        String invalidSenderem = plugin.getConfig().getString("InvalidSender");
        String successm = plugin.getConfig().getString("Success");
        String reloadm = plugin.getConfig().getString("ReloadMessage");
        String prefix = plugin.getConfig().getString("PrefixValue");
        List<String> pronounsListc = plugin.getConfig().getStringList("Pronouns");
        List<String> helpm = plugin.getConfig().getStringList("HelpMessage");
        List<String> checkm = plugin.getConfig().getStringList("PronounsCheck");

        // Checks if a player is running the command.
        if(sender instanceof Player){
            // Sets up the player variable.
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("pronouns")) {
                if (args.length < 1) {
                    for (String helpmessage : helpm){
                        player.sendMessage(C(helpmessage));
                    }
                }
                try {
                    if (args[0].equalsIgnoreCase("help")) {
                        for (String helpmessage : helpm){
                            player.sendMessage(C(helpmessage));
                        }
                    } else if (args[0].equalsIgnoreCase("set")) {
                        boolean checkpn = false;
                        for (String pnl:pronounsListc) {
                            if (args[1].equalsIgnoreCase(pnl)) {
                                checkpn = true;
                                break;
                            }
                        }

                        if (checkpn){
                            PreparedStatement set1 = plugin.DB.getConnection().prepareStatement("UPDATE pronouns SET pronounsSet=? WHERE playerUUID=?");
                            set1.setString(1, args[1]);
                            set1.setString(2, player.getUniqueId().toString());
                            set1.executeUpdate();
                            successm = successm.replace("%pronouns_new%", args[1]);
                            player.sendMessage(C(prefix + " " + successm));
                        }
                    } else if (args[0].equalsIgnoreCase("check")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        String pronounsChoice = null;
                        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT pronounsSet FROM pronouns WHERE playerUUID=?");
                        ps1.setString(1, target.getUniqueId().toString());
                        ResultSet rs1 = ps1.executeQuery();
                        if (rs1.next()){
                            pronounsChoice = rs1.getString("pronounsSet");
                        }
                        for (String pn : checkm){
                            pn = pn.replace("%pronouns_set%", pronounsChoice);
                            pn = pn.replace("%player%", target.getName());
                            player.sendMessage(C(pn));
                        }
                    }else if(args[0].equalsIgnoreCase("list")){
                        for (String pnlist : pronounsListc){
                            player.sendMessage(C(pnlist));
                        }
                    } else {
                        player.sendMessage(C(prefix + invalidArgumentem));
                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    player.sendMessage(C(prefix + invalidInputsem));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            sender.sendMessage(C(prefix + " " + invalidSenderem));
        }



        return true;
    }
    private String C(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
