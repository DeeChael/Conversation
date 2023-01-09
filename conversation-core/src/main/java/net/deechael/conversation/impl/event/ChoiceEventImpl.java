package net.deechael.conversation.impl.event;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ChoiceEvent;
import org.bukkit.entity.Player;

public class ChoiceEventImpl extends ConversationEventImpl implements ChoiceEvent {

    private final Button button;

    public ChoiceEventImpl(Conversation conversation, Node node, Button button, Player player) {
        super(conversation, node, player);
        this.button = button;
    }

    @Override
    public Button getButton() {
        return button;
    }

}
