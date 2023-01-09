package net.deechael.conversation.event;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ConversationEndEvent extends Event implements ConversationEvent {

    private final static HandlerList handlerList = new HandlerList();

    private final Conversation conversation;
    private final Node node;
    private final Player player;
    private final Reason reason;

    public ConversationEndEvent(Conversation conversation, Node node, Player player, Reason reason) {
        super(true);
        this.conversation = conversation;
        this.node = node;
        this.player = player;
        this.reason = reason;
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

    public Reason getReason() {
        return reason;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public enum Reason {

        /**
         * Complete the conversation entirely
         */
        COMPLETE,
        /**
         * When the conversation is interrupted by some accident
         */
        INTERRUPT,
        /**
         * Conversation is ended by plugin
         */
        PLUGIN,
        /**
         * Conversation ends because player quits the game
         */
        QUIT,
        /**
         * Conversation ends because of bad data
         */
        BAD_DATA

    }

}
