package uk.undev.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BlazeAttackListener implements Listener {

    @EventHandler
    public void onBlazeAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Blaze && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Play custom sound at the player's location
            player.getWorld().playSound(
                    player.getLocation(),
                    "bolt.attack", // replace with your custom namespaced sound key
                    1.0f, // volume
                    1.0f  // pitch
            );
        }
    }
}
