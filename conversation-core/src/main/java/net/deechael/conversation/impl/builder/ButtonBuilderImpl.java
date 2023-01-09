package net.deechael.conversation.impl.builder;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.builder.ButtonBuilder;
import net.deechael.conversation.event.ConversationEvent;
import net.deechael.conversation.impl.ButtonImpl;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ButtonBuilderImpl implements ButtonBuilder {

    private Component name;
    private Component hover;
    private Consumer<ConversationEvent> executor;

    @Override
    public @NotNull ButtonBuilder name(@NotNull Component component) {
        this.name = component;
        return this;
    }

    @Override
    public @NotNull ButtonBuilder hover(@NotNull Component component) {
        this.hover = component;
        return this;
    }

    @Override
    public @NotNull ButtonBuilder executor(@Nullable Consumer<ConversationEvent> executor) {
        this.executor = executor;
        return this;
    }

    @Override
    public @NotNull Button build() {
        if (this.name == null)
            throw new RuntimeException("text should not be null!");
        return new ButtonImpl(this.name, this.hover, this.executor);
    }

}
