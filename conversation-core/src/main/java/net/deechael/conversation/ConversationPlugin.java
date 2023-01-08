package net.deechael.conversation;

import net.deechael.conversation.manager.ConversationManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConversationPlugin extends JavaPlugin {

    private static BukkitAudiences adventure;

    @Override
    public void onEnable() {
        adventure = BukkitAudiences.create(this);
        Bukkit.getPluginManager().registerEvents(ConversationManager.INSTANCE, this);
    }

    @Override
    public void onDisable() {
        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }

    public static ConversationPlugin getInstance() {
        return JavaPlugin.getPlugin(ConversationPlugin.class);
    }

    public static BukkitAudiences getAdventure() {
        return adventure;
    }

}
