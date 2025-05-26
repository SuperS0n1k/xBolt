package uk.undev.entities.bolt;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import uk.undev.xBolt;

public class Bolt {
    private int HEAD_MODEL_DATA = 6668;
    private int RODS_MODEL_DATA = 6669;
    private int DROP_MODEL_DATA = 6667;

    public void spawn(Location origin, World world) {
        // init origins (locations)
        Location headOrigin = origin.clone().add(0.25, 1.25, 0.25);
        Location rodsOrigin = origin.clone().add(0.4375, 0.62089375, 0.45985);
        Location puppetMasterOrigin = origin.clone();

        // init materials (itemstacks)
        ItemStack headMaterial = new ItemStack(Material.PAPER);
        ItemStack rodsMaterial = new ItemStack(Material.PAPER);

        // get material attribute containers (itemmeta)
        ItemMeta headAttributes = headMaterial.getItemMeta();
        ItemMeta rodAttributes = headMaterial.getItemMeta();

        // set material attributes (itemmeta)
        // head
        headAttributes.setCustomModelData(HEAD_MODEL_DATA);
        headMaterial.setItemMeta(headAttributes);

        // body
        rodAttributes.setCustomModelData(RODS_MODEL_DATA);
        rodsMaterial.setItemMeta(rodAttributes);

        // spawn puppetmaster (blaze)
        Blaze blazeEntity = world.spawn(puppetMasterOrigin, Blaze.class);
        blazeEntity.setInvisible(true);
        // blazeEntity.setSilent(true);

        // spawn puppets (itemdisplay)
        ItemDisplay headDisplay = world.spawn(headOrigin, ItemDisplay.class);
        ItemDisplay rodsDisplay = world.spawn(rodsOrigin, ItemDisplay.class);

        // skin puppets (itemstack)
        headDisplay.setItemStack(headMaterial);
        rodsDisplay.setItemStack(rodsMaterial);
        headDisplay.setTransformation(new Transformation(
                new Vector3f(), // no translation
                new AxisAngle4f(), // no left rotation
                new Vector3f(1, 1, 1), // scale up by a factor of 2 on all axes
                new AxisAngle4f(0,0,0,0) // no right rotation
        ));
        rodsDisplay.setTransformation(new Transformation(
                new Vector3f(), // no translation
                new AxisAngle4f(), // no left rotation
                new Vector3f(1, 1, 1), // scale up by a factor of 2 on all axes
                new AxisAngle4f(0,0,0,0) // no right rotation
        ));
        rodsDisplay.setInterpolationDelay(-1);
        headDisplay.setInterpolationDelay(-1);

        // start main entity mechanic loop (bukkitrunnable)
        new BukkitRunnable() {
            private float rodRotation = 0;

            @Override
            public void run() {
                if (blazeEntity.isDead()) {
                    headDisplay.remove();
                    rodsDisplay.remove();
                    cancel();
                    return;
                }

                Location baseLoc = blazeEntity.getLocation();

                headDisplay.teleport(baseLoc.clone().add(0, 1.75, 0));
                rodsDisplay.teleport(baseLoc.clone().add(0, 1.12, 0));

                rodRotation += 7f; // smoother, slower spin
                if (rodRotation >= 360f) rodRotation -= 360f;

                rodsDisplay.setRotation(rodRotation % 360f, 0);
            }
        }.runTaskTimer(xBolt.getInstance(), 0L, 1L); // every 2 ticks
    }
}
