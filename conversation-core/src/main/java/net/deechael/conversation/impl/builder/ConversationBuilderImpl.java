package net.deechael.conversation.impl.builder;

import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.builder.ConversationBuilder;
import net.deechael.conversation.impl.ConversationImpl;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationBuilderImpl implements ConversationBuilder {

    private Component text;
    private Component name;
    private final List<Node> nodes = new ArrayList<>();
    private int waiting = 0;
    private Sound sound;

    @Override
    public @NotNull ConversationBuilder text(@NotNull Component text) {
        this.text = text;
        return this;
    }

    @Override
    public @NotNull ConversationBuilder name(@NotNull Component name) {
        this.name = name;
        return this;
    }

    @Override
    public @NotNull ConversationBuilder node(Node @NotNull ... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

    @Override
    public @NotNull ConversationBuilder node(@NotNull List<Node> nodes) {
        this.nodes.addAll(nodes);
        return this;
    }

    @Override
    public @NotNull ConversationBuilder waiting(int seconds) {
        this.waiting = seconds;
        return this;
    }

    @Override
    public @NotNull ConversationBuilder sound(@Nullable Sound sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public Conversation build() {
        if (this.text == null)
            throw new RuntimeException("text should not be null!");
        if (this.name == null)
            throw new RuntimeException("name should not be null!");
        return new ConversationImpl(this.text, this.name, this.nodes, this.waiting, this.sound);
    }

}
