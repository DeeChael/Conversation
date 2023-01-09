package net.deechael.conversation.api;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.builder.ConversationBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Conversation object which contains the nodes of the whole conversation
 *
 * @author DeeChael
 */
public interface Conversation {

    /**
     * Get the text of the conversation
     *
     * @return text
     */
    @NotNull
    Component text();

    /**
     * The name of the npc who is having conversation with the player
     *
     * @return name
     */
    @NotNull
    Component name();

    /**
     * Get nodes of the conversation
     *
     * @return nodes
     */
    @NotNull
    List<Node> nodes();

    /**
     * Get how long to wait
     *
     * @return time
     */
    int waiting();

    /**
     * Get the sound to play when conversation
     *
     * @return sound
     */
    @Nullable
    Sound sound();

    /**
     * Start conversation for a player
     *
     * @param player player
     */
    void start(@NotNull Player player);

    static ConversationBuilder of() {
        return ConversationAPI.newConversation();
    }

}
