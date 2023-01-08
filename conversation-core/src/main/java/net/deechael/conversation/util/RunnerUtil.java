package net.deechael.conversation.util;

import net.deechael.conversation.ConversationPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class RunnerUtil {

    public static void run(BukkitRunnable runnable, long delay) {
        runnable.runTaskLater(ConversationPlugin.getInstance(), delay * 20L);
    }

    private RunnerUtil() {
    }

}
