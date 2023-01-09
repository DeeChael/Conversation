package net.deechael.conversation;

import net.deechael.conversation.builder.ButtonBuilder;
import net.deechael.conversation.builder.ConversationBuilder;
import net.deechael.conversation.builder.NodeBuilder;
import org.bukkit.entity.Player;

/**
 * A utility class of Conversation library
 */
public final class ConversationAPI {

    /**
     * Create new conversation
     *
     * @return conversation builder
     */
    public static ConversationBuilder newConversation() {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Create new node
     *
     * @return node builder
     */
    public static NodeBuilder newNode() {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Create new button
     *
     * @return button builder
     */
    public static ButtonBuilder newButton() {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Try to interrupt a conversation of a player
     *
     * @param player player
     */
    public static void interrupt(Player player) {
        throw new RuntimeException("Not implemented yet");
    }

    private ConversationAPI() {
    }

}
