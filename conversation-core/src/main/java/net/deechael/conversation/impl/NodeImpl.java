package net.deechael.conversation.impl;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Node;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NodeImpl implements Node {

    private final Button button;
    private final List<Node> nodes;
    private final Component text;
    private final int waiting;
    private final Sound sound;

    public NodeImpl(Button button, List<Node> nodes, Component text, int waiting, Sound sound) {
        this.button = button;
        this.nodes = nodes;
        this.text = text;
        this.waiting = waiting;
        this.sound = sound;
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
    public @Nullable Sound sound() {
        return this.sound;
    }

    @Override
    public int waiting() {
        return this.waiting;
    }

}
