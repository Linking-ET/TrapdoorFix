package let.xiaochen.trapdoorfix;

import org.bukkit.plugin.java.JavaPlugin;


public final class TrapdoorFix extends JavaPlugin {

    private let.xiaochen.trapdoorfix.TrapdoorListener listener;

    @Override
    public void onEnable() {
        listener = new let.xiaochen.trapdoorfix.TrapdoorListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
        getCommand("trapdoorfix").setExecutor(new ReloadCommand(this));
        getLogger().info("TrapdoorFix enabled!");
    }

    public let.xiaochen.trapdoorfix.TrapdoorListener getListener() {
        return listener;
    }
}