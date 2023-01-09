package net.deechael.conversation.impl.event;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ConversationEvent;
import org.bukkit.entity.Player;

public class ConversationEventImpl implements ConversationEvent {

    private final Conversation conversation;
    private final Node node;
    private final Player player;

    public ConversationEventImpl(Conversation conversation, Node node, Player player) {
        this.conversation = conversation;
        this.node = node;
        this.player = player;
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

}
