package net.deechael.conversation.impl;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.manager.ConversationManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConversationImpl implements Conversation {

    private final Component text;
    private final Component name;
    private final List<Node> nodes;
    private final int waiting;

    public ConversationImpl(Component text, Component name, List<Node> nodes, int waiting) {
        this.text = text;
        this.name = name;
        this.nodes = nodes;
        this.waiting = waiting;
    }

    @Override
    public @NotNull Component text() {
        return this.text;
    }

    @Override
    public @NotNull Component name() {
        return this.name;
    }

    @Override
    public @NotNull List<Node> nodes() {
        return this.nodes;
    }

    @Override
    public int waiting() {
        return this.waiting;
    }

    @Override
    public void start(@NotNull Player player) {
        Node node = ConversationAPI.newNode()
                .text(this.text)
                .sub(this.nodes)
                .waiting(this.waiting)
                .build();
        ConversationManager.INSTANCE.nextNode(player, this.name, node);
    }

}
