package let.xiaochen.trapdoorfix;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack
;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TrapdoorListener implements Listener {

    private final TrapdoorFix plugin;
    private boolean enabled;
    private Set<Material> materials = new HashSet<>();
    private boolean isBlacklist;

    public TrapdoorListener(TrapdoorFix plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.saveDefaultConfig();
        FileConfiguration cfg = plugin.getConfig();
        enabled = cfg.getBoolean("enabled", true);
        isBlacklist = cfg.getString("mode", "BLACKLIST").equalsIgnoreCase("BLACKLIST");
        materials = cfg.getStringList("list").stream()
                .map(String::toUpperCase)
                .map(Material::matchMaterial)
                .filter(m -> m != null && Tag.TRAPDOORS.isTagged(m))
                .collect(Collectors.toSet());
    }

    @EventHandler
    public void onRedstoneUpdate(BlockRedstoneEvent event) {
        if (!enabled) return;
        Block block = event.getBlock();
        if (!Tag.TRAPDOORS.isTagged(block.getType())) return;
        boolean inList = materials.contains(block.getType());
        if (isBlacklist ? inList : !inList) return;

        Block above = block.getRelative(BlockFace.UP);
        if (above.getType() == Material.REDSTONE_WIRE) {
            TrapDoor trapdoor = (TrapDoor) block.getBlockData();
            if (!trapdoor.isOpen() || event.getNewCurrent() > 0) {
                above.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(),
                        new ItemStack(Material.REDSTONE));
            }
        }
    }
}
