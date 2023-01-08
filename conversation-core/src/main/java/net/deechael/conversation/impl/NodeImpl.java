package net.deechael.conversation.impl;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Node;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NodeImpl implements Node {

    private final Button button;
    private final List<Node> nodes;
    private final Component text;
    private final int waiting;

    public NodeImpl(Button button, List<Node> nodes, Component text, int waiting) {
        this.button = button;
        this.nodes = nodes;
        this.text = text;
        this.waiting = waiting;
    }

    @Override
    public @Nullable Button button() {
        return this.button;
    }

    @Override
    public @NotNull List<Node> sub() {
        return this.nodes;
    }

    @Override
    public @NotNull Component text() {
        return this.text;
    }

    @Override
    public int waiting() {
        return this.waiting;
    }

}
