package net.deechael.conversation.impl.event;

import net.deechael.conversation.event.ConversationEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConversationEventImpl implements ConversationEvent {

    private final Player player;

    public ConversationEventImpl(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull Player player() {
        return this.player;
    }

}
