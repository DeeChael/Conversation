package net.deechael.conversation.api;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.builder.ButtonBuilder;
import net.deechael.conversation.event.ConversationEvent;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Choice button
 *
 * @author DeeChael
 */
public interface Button {

    /**
     * Get the name of the button
     *
     * @return name
     */
    @NotNull
    Component name();

    /**
     * Get the hover text of the button
     *
     * @return hover
     */
    @Nullable
    Component hover();

    /**
     * Get the executor of this button
     *
     * @return executor
     */
    @Nullable
    Consumer<ConversationEvent> executor();

    static ButtonBuilder of() {
        return ConversationAPI.newButton();
    }

}
