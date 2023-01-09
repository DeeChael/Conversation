package net.deechael.conversation.impl;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.event.ConversationEvent;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ButtonImpl implements Button {

    private final Component name;
    private final Component hover;
    private final Consumer<ConversationEvent> executor;

    public ButtonImpl(Component name, Component hover, Consumer<ConversationEvent> executor) {
        this.name = name;
        this.hover = hover;
        this.executor = executor;
    }

    @Override
    public @NotNull Component name() {
        return this.name;
    }

    @Override
    public @Nullable Component hover() {
        return this.hover;
    }

    @Override
    public @Nullable Consumer<ConversationEvent> executor() {
        return this.executor;
    }

}
