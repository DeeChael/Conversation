package net.deechael.conversation.impl;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ConversationStartEvent;
import net.deechael.conversation.manager.ConversationManager;
import net.deechael.conversation.util.RunnerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConversationImpl implements Conversation {

    private final Component text;
    private final Component name;
    private final List<Node> nodes;
    private final int waiting;
    private final Sound sound;

    public ConversationImpl(Component text, Component name, List<Node> nodes, int waiting, Sound sound) {
        this.text = text;
        this.name = name;
        this.nodes = nodes;
        this.waiting = waiting;
        this.sound = sound;
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
    public @Nullable Sound sound() {
        return this.sound;
    }

    @Override
    public void start(@NotNull Player player) {
        Node node = ConversationAPI.newNode()
                .text(this.text)
                .sub(this.nodes)
                .waiting(this.waiting)
                .sound(this.sound)
                .build();
        ConversationStartEvent event = new ConversationStartEvent(this, node, player);
        RunnerUtil.call(event, true);
        ConversationManager.INSTANCE.nextNode(player, this, node);
    }

}
