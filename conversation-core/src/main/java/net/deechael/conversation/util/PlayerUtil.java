package net.deechael.conversation.util;

import net.deechael.conversation.ConversationPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public final class PlayerUtil {

    private final static Component SCREEN_CLEANER = Component.text("    ").appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline()
            .append(Component.text("    ")).appendNewline();

    public static void clearScreen(Player player) {
        send(player, SCREEN_CLEANER);
    }

    public static void send(Player player, Component component) {
        ConversationPlugin.getAdventure().player(player).sendMessage(component);
    }

    private PlayerUtil() {
    }

}
