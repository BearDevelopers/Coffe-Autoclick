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
        Document query = new Document("name", p.getName());
        Document result = (Document) MongoDB.getDocument().find(query).first();
        if (Coffe_Autoclick.players.containsKey(p)) {
            Document doc = new Document()
                    .append("name", p.getName())
                    .append("souls", i);
            MongoDB.getDocument().insertOne((Document) doc);
        }
        else {

            p.sendMessage(ChatColor.RED + "Parabens!");
            p.sendMessage("Foi adicionado + 1 a sua quantidade de almas");

        }
        if (result != null) {
            int souls = result.getInteger("souls", 0);
            p.sendMessage(ChatColor.RED + "Agora você está com " + souls);
        }
        else {
            int souls = result.getInteger("souls");
            p.sendMessage("Agora você está com " + souls);
        }
    }
    public void sendTitle() {
        Object handle = player.getClass().getMethod("getHandle").invoke(player);
        Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
    }
}
