package net.deechael.conversation.util;

import net.deechael.conversation.ConversationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

public final class RunnerUtil {

    public static void run(BukkitRunnable runnable, long delay) {
        runnable.runTaskLater(ConversationPlugin.getInstance(), delay * 20L);
    }

    public static void run(BukkitRunnable runnable, boolean async) {
        if (async)
            runnable.runTaskAsynchronously(ConversationPlugin.getInstance());
        else
            runnable.runTask(ConversationPlugin.getInstance());
    }

    public static void call(Event event, boolean async) {
        run(new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(event);
            }
        }, async);
    }

    private RunnerUtil() {
    }

}
