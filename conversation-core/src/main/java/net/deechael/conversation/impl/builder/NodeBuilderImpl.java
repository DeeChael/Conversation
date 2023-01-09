package net.deechael.conversation.impl.builder;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.builder.NodeBuilder;
import net.deechael.conversation.impl.NodeImpl;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeBuilderImpl implements NodeBuilder {

    private Button button;
    private final List<Node> nodes = new ArrayList<>();
    private Component text;
    private int waiting = 0;
    private Sound sound;

    @Override
    public NodeBuilder button(@Nullable Button button) {
        this.button = button;
        return this;
    }

    @Override
    public @NotNull NodeBuilder text(@NotNull Component text) {
        this.text = text;
        return this;
    }

    @Override
    public @NotNull NodeBuilder waiting(int seconds) {
        this.waiting = seconds;
        return this;
    }

    @Override
    public @NotNull NodeBuilder sub(@NotNull Node... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

    @Override
    public @NotNull NodeBuilder sub(@NotNull List<Node> nodes) {
        this.nodes.addAll(nodes);
        return this;
    }

    @Override
    public @NotNull NodeBuilder sound(@Nullable Sound sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public @NotNull Node build() {
        if (this.text == null)
            throw new RuntimeException("text should not be null!");
        return new NodeImpl(this.button, this.nodes, this.text, this.waiting, this.sound);
    }

}
