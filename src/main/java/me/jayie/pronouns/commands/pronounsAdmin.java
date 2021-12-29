package me.jayie.pronouns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pronounsAdmin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("apronouns")){
                if (!player.hasPermission("pronouns.admin") | !player.hasPermission("pronouns.*")){
                    player.sendMessage("*insert error message*");
                }

                if (args.length < 1){
                    player.sendMessage("*insert error message*");
                }

                try{
                    if (args[0].equalsIgnoreCase("reload")){

                    }else if (args[0].equalsIgnoreCase("forceset")){

                    }else{
                        player.sendMessage("*insert error message*");
                    }
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
                    e.printStackTrace();
                }
            }

        }else{
            sender.sendMessage("*insert error message*");
        }



        return true;
    }
}
