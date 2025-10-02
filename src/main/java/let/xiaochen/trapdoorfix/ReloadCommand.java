package let.xiaochen.trapdoorfix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final TrapdoorFix plugin;

    public ReloadCommand(TrapdoorFix plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command cmd,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("trapdoorfix.reload")) {
            sender.sendMessage(ChatColor.RED + "你没有权限执行此命令。");
            return true;
        }
        plugin.reloadConfig();
        plugin.getListener().reload();
        sender.sendMessage(ChatColor.GREEN + "TrapdoorFix 配置已重载。");
        return true;
    }
}