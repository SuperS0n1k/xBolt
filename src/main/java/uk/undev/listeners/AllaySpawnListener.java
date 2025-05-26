package uk.undev.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import uk.undev.entities.bolt.Bolt;
import uk.undev.xBolt;

public class AllaySpawnListener implements Listener {
    @EventHandler
    public void SpawnEvent(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.ALLAY) return;
        Bolt bolt = new Bolt();
        bolt.spawn(event.getLocation(), entity.getWorld());
        entity.remove();
        event.setCancelled(true);
    }
}
