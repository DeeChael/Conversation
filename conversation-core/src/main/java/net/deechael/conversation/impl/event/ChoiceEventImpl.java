package net.deechael.conversation.impl.event;

import net.deechael.conversation.event.ChoiceEvent;
import org.bukkit.entity.Player;

public class ChoiceEventImpl extends ConversationEventImpl implements ChoiceEvent {

    public ChoiceEventImpl(Player player) {
        super(player);
    }

}
