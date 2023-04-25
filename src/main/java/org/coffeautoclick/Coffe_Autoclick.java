package org.coffeautoclick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.coffeautoclick.commands.AutoClickCommands;

public final class Coffe_Autoclick extends JavaPlugin {
    public static Coffe_Autoclick instance;

    @Override
    public void onEnable() {
        instance=this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Coffe-AutoClick]  " + ChatColor.GREEN + "Iniciado com sucesso");
        saveDefaultConfig();
        saveConfig();
        registerCommands();
    }

    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Coffe-AutoClick]  " + ChatColor.RED + "Desabilitado com sucesso");
    }
    public void registerCommands() {
        getCommand("autoclick").setExecutor(new AutoClickCommands());
    }

    public static Coffe_Autoclick getInstance() {
        return instance;
    }
}
