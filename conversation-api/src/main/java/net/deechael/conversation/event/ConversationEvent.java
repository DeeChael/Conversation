package net.deechael.conversation.event;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * The base class for all Conversation events
 */
public interface ConversationEvent {

    /**
     * Get the player of this event
     *
     * @return player
     */
    @NotNull
    Player player();

}
