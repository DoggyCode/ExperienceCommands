package me.expdev.expcommand.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * EXPCommand - Created by Marius on 02.04.2016.
 */

public class EXPCommandCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        commandSender.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        commandSender.sendMessage(ChatColor.GRAY + "Made by: " + ChatColor.RED + "ExpDev ( http://www.mc-market.org/members/11048/ )");
        commandSender.sendMessage(ChatColor.GRAY + "Updates: " + ChatColor.RED + "Link coming soon!");
        commandSender.sendMessage(ChatColor.GRAY + "Plugin Version: " + ChatColor.RED + "v1.0");
        commandSender.sendMessage(ChatColor.GRAY + "Info: " + ChatColor.GREEN + "Pay for commands using EXP!");
        commandSender.sendMessage("" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        return false;
    }
}
