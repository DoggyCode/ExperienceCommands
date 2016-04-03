package me.expdev.expcommand.events;

import me.expdev.expcommand.ExpCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * EXPCommand - Created by Marius on 02.04.2016.
 */

public class CommandPreProcessEvent implements Listener {

    ExpCommand plugin;

    public CommandPreProcessEvent(ExpCommand instance){
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        if(p.isOp()){
            return;
        }
        String message = event.getMessage().toLowerCase();
        String[] command = message.split(" ");
        String permission = "";
        int level = 0;
        ConfigurationSection section = this.plugin.getCfg().getConfig().getConfigurationSection("commands");
        for(String s : section.getKeys(false)){
            s = s.toLowerCase();
            if(command[0].equalsIgnoreCase("/"+s)){
                permission = this.plugin.getCfg().getConfig().getString("commands."+s+".permission").toLowerCase();
                if(!p.hasPermission(permission)){
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " +
                            ChatColor.RED + "You don't have permission to do that!");
                    return;
                }
                level = this.plugin.getCfg().getConfig().getInt("commands."+s+".level");
                if(p.getLevel()<level){
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " +
                            ChatColor.RED + "You" + ChatColor.GRAY + " have " + ChatColor.RED + (NumberFormat.getNumberInstance(Locale.US).format(p.getLevel())) + ChatColor.GRAY + " level(s) and need " +
                            ChatColor.RED + (NumberFormat.getNumberInstance(Locale.US).format(level-p.getLevel())) + ChatColor.GRAY + " more level(s) to perform that command (" + ChatColor.RED + command[0] + ChatColor.GRAY + ").");
                }else{
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + ChatColor.BOLD + "!" + ChatColor.DARK_GRAY + "] " +
                            ChatColor.GREEN + "You" + ChatColor.GRAY + " paid " + ChatColor.GREEN + (NumberFormat.getNumberInstance(Locale.US).format(level)) + "" +
                            ChatColor.GRAY + " level(s) to execute that command (" + ChatColor.GREEN + command[0] + ChatColor.GRAY + ").");
                    p.setLevel(p.getLevel()-level);
                }
                return;
            }
        }
    }
}
