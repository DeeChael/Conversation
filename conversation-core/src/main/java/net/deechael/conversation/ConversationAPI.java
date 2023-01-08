package net.deechael.conversation;

import net.deechael.conversation.builder.ButtonBuilder;
import net.deechael.conversation.builder.ConversationBuilder;
import net.deechael.conversation.builder.NodeBuilder;
import net.deechael.conversation.impl.builder.ButtonBuilderImpl;
import net.deechael.conversation.impl.builder.ConversationBuilderImpl;
import net.deechael.conversation.impl.builder.NodeBuilderImpl;

/**
 * A utility class of Conversation library
 */
public final class ConversationAPI {

    public static ConversationBuilder newConversation() {
        return new ConversationBuilderImpl();
    }

    public static NodeBuilder newNode() {
        return new NodeBuilderImpl();
    }

    public static ButtonBuilder newButton() {
        return new ButtonBuilderImpl();
    }

    private ConversationAPI() {}

}
