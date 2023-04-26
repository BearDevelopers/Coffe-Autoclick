package org.coffeautoclick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.coffeautoclick.commands.AutoClickCommands;
import org.coffeautoclick.database.MongoDB;
import org.coffeautoclick.events.EntityDeathListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Coffe_Autoclick extends JavaPlugin {
    public static Coffe_Autoclick instance;
    public static Map<UUID, Player> players = new HashMap<>();

    @Override
    public void onEnable() {
        MongoDB.connection();
        instance=this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Coffe-AutoClick]  " + ChatColor.GREEN + "Iniciado com sucesso");
        saveDefaultConfig();
        saveConfig();
        registerCommands();
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new EntityDeathListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
        MongoDB.getClient().close();
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Coffe-AutoClick]  " + ChatColor.RED + "Desabilitado com sucesso");
    }
    public void registerCommands() {
        getCommand("autoclick").setExecutor(new AutoClickCommands());
    }

    public static Coffe_Autoclick getInstance() {
        return instance;
    }
}
