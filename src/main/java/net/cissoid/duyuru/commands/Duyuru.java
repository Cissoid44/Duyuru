package net.cissoid.duyuru.commands;

import net.cissoid.duyuru.constants.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Duyuru implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("duyuru.broadcast")) {
            if (args.length > 0) {
                String message = "";

                for (String string : args) {
                    message += string + " ";
                }

                message = message.substring(0, message.length() - 1);

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Messages.PLUGIN_PREFIX.toString() + message);
                }

                Bukkit.getConsoleSender().sendMessage(Messages.PLUGIN_PREFIX.toString() + message);
            } else {
                Messages.FAILURE_SYNTAX.send(sender);
            }

        }else {
            Messages.FAILURE_PERMISSION.send(sender);
        }

        return true;
    }

}
