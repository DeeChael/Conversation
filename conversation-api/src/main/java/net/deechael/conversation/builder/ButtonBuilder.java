package net.deechael.conversation.builder;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.event.ConversationEvent;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Builder to create a new button
 *
 * @author DeeChael
 */
public interface ButtonBuilder {

    /**
     * The name of this button
     *
     * @param component name
     * @return builder
     */
    @NotNull
    ButtonBuilder name(@NotNull Component component);

    /**
     * The hover text of this button
     *
     * @param component hover tips
     * @return builder
     */
    @NotNull
    ButtonBuilder hover(@NotNull Component component);

    /**
     * Set the executor of the button
     *
     * @param executor executor
     * @return builder
     */
    @NotNull
    ButtonBuilder executor(@Nullable Consumer<ConversationEvent> executor);

    /**
     * Build button object
     *
     * @return button
     */
    @NotNull
    Button build();

}
