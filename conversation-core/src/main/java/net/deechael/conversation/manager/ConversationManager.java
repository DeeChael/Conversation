package net.deechael.conversation.manager;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ConversationEndEvent;
import net.deechael.conversation.event.ConversationEvent;
import net.deechael.conversation.impl.event.ChoiceEventImpl;
import net.deechael.conversation.util.PlayerUtil;
import net.deechael.conversation.util.RunnerUtil;
import net.deechael.conversation.util.StrUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.Consumer;

public final class ConversationManager implements Listener {

    private final static Map<String, Node> QUEUE = new HashMap<>();
    private final static Map<UUID, ConversationData> PLAYER = new HashMap<>();

    public final static ConversationManager INSTANCE = new ConversationManager();

    @EventHandler
    public void event(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        if (command.startsWith("/"))
            command = command.substring(1);
        if (!command.startsWith("conversation_button_handler "))
            return;
        event.setCancelled(true);
        if (!PLAYER.containsKey(player.getUniqueId()))
            return;
        command = command.trim();
        if (StrUtil.count(command, " ") < 2)
            return;
        String[] split = command.split(" ");
        String queueId = split[1];
        if (!QUEUE.containsKey(queueId))
            return;
        ConversationData data = PLAYER.get(player.getUniqueId());
        if (!Objects.equals(queueId, data.queueId))
            return;
        if (!StrUtil.isInteger(split[2]))
            return;
        int buttonIndex = Integer.parseInt(split[2]);
        Node node = QUEUE.get(queueId);
        List<Node> nodes = node.sub();
        if (buttonIndex < 0 || buttonIndex >= nodes.size())
            return;
        Node nextNode = nodes.get(buttonIndex);
        Button button = nextNode.button();
        if (button == null) {
            clearPlayerData(player, ConversationEndEvent.Reason.BAD_DATA);
            return;
        }
        Consumer<ConversationEvent> executor = button.executor();
        if (executor != null)
            executor.accept(new ChoiceEventImpl(data.conversation, node, button, player));
        nextNode(player, data.conversation, nextNode);
    }

    @EventHandler
    public void event(PlayerQuitEvent event) {
        clearPlayerData(event.getPlayer(), ConversationEndEvent.Reason.QUIT);
    }

    public void clearPlayerData(Player player, ConversationEndEvent.Reason reason) {
        if (PLAYER.containsKey(player.getUniqueId())) {
            Node node = QUEUE.remove(PLAYER.get(player.getUniqueId()).queueId);
            ConversationData data = PLAYER.remove(player.getUniqueId());
            ConversationEndEvent event = new ConversationEndEvent(data.conversation, node, player, reason);
            RunnerUtil.call(event, true);
        }
    }

    public void nextNode(Player player, Conversation conversation, Node node) {
        if (PLAYER.containsKey(player.getUniqueId())) {
            QUEUE.remove(PLAYER.get(player.getUniqueId()).queueId);
        }
        String queueId = StrUtil.random(16, QUEUE.keySet());
        if (!PLAYER.containsKey(player.getUniqueId()))
            PLAYER.put(player.getUniqueId(), ConversationData.of(conversation));
        PLAYER.get(player.getUniqueId()).queueId = queueId;
        QUEUE.put(queueId, node);

        Component message = Component.text("    ").appendNewline()
                .append(Component.text("    ")).appendNewline()
                .append(Component.text("    ")).append(conversation.name()).appendNewline()
                .append(Component.text("    ")).appendNewline()
                .append(Component.text("    ")).append(node.text())
                .append(Component.text("    ")).appendNewline();

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                clearPlayerData(player, ConversationEndEvent.Reason.COMPLETE);
            }
        };

        List<Node> nodes = node.sub();
        boolean pass = false;
        if (nodes.size() == 1) {
            Node next = nodes.get(0);
            if (next.button() == null) {
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        nextNode(player, conversation, next);
                    }
                };
                pass = true;
            }
        }
        if (!pass)
            if (nodes.size() >= 1) {
                Component buttonBase = Component.text("    ").appendNewline();
                for (int i = 0; i < nodes.size(); i++) {
                    Node buttonNode = nodes.get(i);
                    if (buttonNode.button() == null)
                        continue;
                    Button button = buttonNode.button();
                    assert button != null;
                    TextComponent.Builder buttonComponent = Component.text().append(button.name());
                    if (button.hover() != null)
                        buttonComponent.hoverEvent(button.hover());
                    buttonComponent.clickEvent(ClickEvent.runCommand("/conversation_button_handler " + queueId + " " + i));

                    buttonBase = buttonBase.append(Component.text("    ")).append(buttonComponent.build()).appendNewline();
                }

                Component finalButtonBase = buttonBase;
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        PlayerUtil.send(player, finalButtonBase);
                    }
                };
            }
        Sound sound = node.sound();
        if (sound != null)
            player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
        PlayerUtil.clearScreen(player);
        PlayerUtil.send(player, message);
        RunnerUtil.run(runnable, node.waiting());
    }

    private static class ConversationData {

        private Conversation conversation;
        private String queueId;

        private static ConversationData of(Conversation conversation) {
            ConversationData data = new ConversationData();
            data.conversation = conversation;
            return data;
        }

        private static ConversationData of(Conversation conversation, String queueId) {
            ConversationData data = new ConversationData();
            data.conversation = conversation;
            data.queueId = queueId;
            return data;
        }

    }

    private ConversationManager() {
    }

}
