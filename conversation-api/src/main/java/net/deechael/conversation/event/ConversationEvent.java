package net.deechael.conversation.event;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import org.bukkit.entity.Player;

/**
 * Event about conversation
 */
public interface ConversationEvent {

    Conversation getConversation();

    Node getNode();

    Player getPlayer();

}
