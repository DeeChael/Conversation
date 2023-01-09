package net.deechael.conversation.builder;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Node;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Builder to create new node
 *
 * @author DeeChael
 */
public interface NodeBuilder {

    /**
     * Set the button of this node
     *
     * @param button button, null for clearing the button
     * @return builder
     */
    NodeBuilder button(@Nullable Button button);

    /**
     * The text to show
     *
     * @param text text
     * @return builder
     */
    @NotNull
    NodeBuilder text(@NotNull Component text);

    /**
     * Set wait how long to show options or next text
     *
     * @param seconds time
     * @return builder
     */
    @NotNull
    NodeBuilder waiting(int seconds);

    /**
     * Add sub nodes
     *
     * @param nodes sub nodes
     * @return builder
     */
    @NotNull
    NodeBuilder sub(@NotNull Node... nodes);

    /**
     * Add sub nodes
     *
     * @param nodes sub nodes
     * @return builder
     */
    @NotNull
    NodeBuilder sub(@NotNull List<Node> nodes);

    /**
     * Set the sound to play when conversation
     *
     * @param sound sound type
     * @return builder
     */
    @NotNull
    NodeBuilder sound(@Nullable Sound sound);

    /**
     * Build node object
     *
     * @return node
     */
    @NotNull
    Node build();

}
