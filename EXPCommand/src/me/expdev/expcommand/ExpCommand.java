package me.expdev.expcommand;

import me.expdev.expcommand.commands.EXPCommandCommand;
import me.expdev.expcommand.events.CommandPreProcessEvent;
import me.expdev.expcommand.utils.YmlMaker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExpCommand extends JavaPlugin {

    private YmlMaker cfg;

    public void onEnable(){
        cfg = new YmlMaker(this, "config.yml");
        cfg.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new CommandPreProcessEvent(this), this);
        getCommand("expcommand").setExecutor(new EXPCommandCommand());
    }

    public YmlMaker getCfg() {
        return cfg;
    }
}
