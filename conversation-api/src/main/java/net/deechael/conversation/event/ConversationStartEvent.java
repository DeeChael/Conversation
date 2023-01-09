package net.deechael.conversation.event;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ConversationStartEvent extends Event implements ConversationEvent {

    private final static HandlerList handlerList = new HandlerList();

    private final Conversation conversation;
    private final Node node;
    private final Player player;

    public ConversationStartEvent(Conversation conversation, Node node, Player player) {
        super(true);
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

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
