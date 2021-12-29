package me.jayie.pronouns.commands;

import me.jayie.pronouns.Pronouns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class pronounsDefault implements CommandExecutor {

    Plugin plugin = Pronouns.getPlugin(Pronouns.class);
    String prefix = plugin.getConfig().getString("PrefixValue");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Checks if a player is running the command.
        if(sender instanceof Player){
            // Sets up the player variable.
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("pronouns")) {
                if (args.length < 1) {
                    player.sendMessage("*insert help message*");
                }
                try {
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage("*insert help message*");
                    } else if (args[0].equalsIgnoreCase("set")) {

                    } else if (args[0].equalsIgnoreCase("check")) {

                    } else if (args[0].equalsIgnoreCase("color")) {

                    } else {
                        player.sendMessage("*insert error message*");
                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    player.sendMessage("*insert error message*");
                }
            }
        }else{
            sender.sendMessage("*insert error message*");
        }



        return true;
    }
}
