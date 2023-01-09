package net.deechael.conversation.builder;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Builder to create a new conversation
 *
 * @author DeeChael
 */
public interface ConversationBuilder {

    /**
     * The text to show
     *
     * @param text text
     * @return builder
     */
    @NotNull
    ConversationBuilder text(@NotNull Component text);

    /**
     * The name of the npc who is having conversation with the player
     *
     * @param name npc name
     * @return builder
     */
    @NotNull
    ConversationBuilder name(@NotNull Component name);

    /**
     * Append nodes
     *
     * @param nodes new nodes
     * @return builder
     */
    @NotNull
    ConversationBuilder node(@NotNull Node... nodes);

    /**
     * Append nodes
     *
     * @param nodes new nodes
     * @return builder
     */
    @NotNull
    ConversationBuilder node(@NotNull List<Node> nodes);

    /**
     * Set wait how long to show options or next text
     *
     * @param seconds time
     * @return builder
     */
    @NotNull
    ConversationBuilder waiting(int seconds);

    /**
     * Set the sound to play when conversation
     *
     * @param sound sound type
     * @return builder
     */
    @NotNull
    ConversationBuilder sound(@Nullable Sound sound);

    /**
     * Build conversation object
     *
     * @return conversation
     */
    Conversation build();

}
