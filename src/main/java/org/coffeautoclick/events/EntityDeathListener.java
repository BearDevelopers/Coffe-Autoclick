package org.coffeautoclick.events;

import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.coffeautoclick.Coffe_Autoclick;
import org.coffeautoclick.commands.AutoClickCommands;
import org.coffeautoclick.database.MongoDB;


public class EntityDeathListener implements Listener {

    public void onDeathEntity(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Player p = e.getEntity().getKiller();
        int i = AutoClickCommands.i;
        i++;
        if (Coffe_Autoclick.players.containsKey(p)) {
            Document doc = new Document()
                    .append("name", p.getName())
                    .append("souls", i);
            MongoDB.getDocument().insertOne((Document) doc);
        }
        Document query = new Document("name", p.getName());
        Document result = (Document) MongoDB.getDocument().find(query).first();

        sendPlayerTitle(p.getPlayer(), "Parabens!", "Foi adicionado + 1 a sua quantidade de almas");
        if (result != null) {
            int souls = result.getInteger("souls", 0);
            p.sendMessage(ChatColor.RED + "Agora você está com " + souls);
        }
    }
    public void sendPlayerTitle(Player player, String title, String subtitle) {
        try {
            player.sendTitle(title, subtitle);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
