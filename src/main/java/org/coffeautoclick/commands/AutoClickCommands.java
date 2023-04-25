package org.coffeautoclick.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.coffeautoclick.Coffe_Autoclick;

public class AutoClickCommands implements CommandExecutor {
    FileConfiguration config = Coffe_Autoclick.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("autoclick")) {
                if (p.hasPermission("autoclick.use")) {
                    if (args.length > 0 && args[0].equalsIgnoreCase("use")) {
                    double d = config.getDouble("autoclick.blocks-of-range");
                    double dmg = config.getDouble("autoclick.damage");
                    p.sendMessage(ChatColor.GREEN + "Hitando...");
                    for (Entity target : p.getNearbyEntities(d, d, d)) {
                        if (target instanceof LivingEntity) {
                            ((LivingEntity) target).damage(dmg);
                        } else {
                            p.sendMessage(ChatColor.RED + "Poxa garotinho, Não pode hitar o amiguinho não...");
                            return true;
                        }
                    }
                }
                } else {
                    p.sendMessage(ChatColor.RED + "Você não tem permissão!");
                    return true;
                }

                if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("autoclick.reload")) {
                        p.sendMessage(ChatColor.GREEN + "Config foi recarregado");
                        Coffe_Autoclick.getInstance().reloadConfig();
                        config = Coffe_Autoclick.getInstance().getConfig(); // Atualiza a configuração
                    } else {
                        p.sendMessage(ChatColor.RED + "Você não tem permissão!");
                        return true;
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Apenas para jogadores in-game.");
        }
        return false;
    }
}
