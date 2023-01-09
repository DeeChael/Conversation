package net.deechael.conversation.api;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.builder.NodeBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Node in conversation which provides more possibility for conversation
 *
 * @author DeeChael
 */
public interface Node {

    /**
     * Get the button of the node
     *
     * @return button
     */
    @Nullable
    Button button();

    /**
     * Get sub nodes
     *
     * @return nodes
     */
    @NotNull
    List<Node> sub();

    /**
     * Get text to show
     *
     * @return text
     */
    @NotNull
    Component text();

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


    static NodeBuilder of() {
        return ConversationAPI.newNode();
    }

}
