package uk.undev.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Blaze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Entity;
import org.bukkit.World;

public class BlazeDeathListener implements Listener {

    @EventHandler
    public void onBlazeDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Blaze) {
            World world = entity.getWorld();

            // Play custom sound at the Blaze's death location
            world.playSound(
                    entity.getLocation(),
                    "bolt.death", // replace with your custom namespaced sound key
                    1.0f, // volume
                    1.0f  // pitch
            );
        }
    }
}
