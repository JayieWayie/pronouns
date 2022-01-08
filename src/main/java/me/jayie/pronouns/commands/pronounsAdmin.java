package me.jayie.pronouns.commands;

import me.jayie.pronouns.Pronouns;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class pronounsAdmin implements CommandExecutor {

    private Pronouns plugin;
    public pronounsAdmin(Pronouns plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String noPermissionem = plugin.getConfig().getString("NoPermission");
        String invalidInputsem = plugin.getConfig().getString("InvalidInputs");
        String invalidArgumentem = plugin.getConfig().getString("InvalidArgument");
        String modForceSetm = plugin.getConfig().getString("");
        List<String> pronounsListc = plugin.getConfig().getStringList("Pronouns");

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("apronouns")) {
                if (!player.hasPermission("pronouns.admin") | !player.hasPermission("pronouns.*")) {
                    player.sendMessage(C(noPermissionem));
                } else {

                    if (args.length < 1) {
                        player.sendMessage(C(invalidArgumentem));
                    }

                    try {
                        if (args[0].equalsIgnoreCase("reload")) {

                        } else if (args[0].equalsIgnoreCase("forceset")) {
                            Player target = Bukkit.getPlayer(args[2]);
                            PreparedStatement fs1 = plugin.DB.getConnection().prepareStatement("UPDATE pronouns SET pronounsSet=? WHERE playerUUID=?");
                            fs1.setString(1, args[3]);
                            fs1.setString(2, target.getUniqueId().toString());
                            fs1.executeUpdate();
                            player.sendMessage(C());
                        } else {
                            player.sendMessage(C(invalidArgumentem));
                        }
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException | SQLException e) {
                        player.sendMessage(C(invalidArgumentem));
                    }
                }
            }

        }else{
            sender.sendMessage("*insert error message*");
        }



        return true;
    }

    private String C(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
