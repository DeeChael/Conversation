package net.deechael.conversation.impl;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.event.ChoiceEvent;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ButtonImpl implements Button {

    private final Component name;
    private final Component hover;
    private final Consumer<ChoiceEvent> executor;

    public ButtonImpl(Component name, Component hover, Consumer<ChoiceEvent> executor) {
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
    public @Nullable Consumer<ChoiceEvent> executor() {
        return this.executor;
    }

}
