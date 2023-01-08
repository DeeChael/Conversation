package net.deechael.conversation.manager;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ChoiceEvent;
import net.deechael.conversation.impl.event.ChoiceEventImpl;
import net.deechael.conversation.util.PlayerUtil;
import net.deechael.conversation.util.RunnerUtil;
import net.deechael.conversation.util.StrUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.Consumer;

public final class ConversationManager implements Listener {

    private final static Map<String, NodeData> QUEUE = new HashMap<>();
    private final static Map<UUID, String> PLAYER = new HashMap<>();

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
        if (!Objects.equals(queueId, PLAYER.get(player.getUniqueId())))
            return;
        if (!StrUtil.isInteger(split[2]))
            return;
        int buttonIndex = Integer.parseInt(split[2]);
        NodeData data = QUEUE.get(queueId);
        List<Node> nodes = data.node.sub();
        if (buttonIndex < 0 || buttonIndex >= nodes.size())
            return;
        Node nextNode = nodes.get(buttonIndex);
        Button button = nextNode.button();
        if (button == null) {
            clearPlayerData(player);
            return;
        }
        Consumer<ChoiceEvent> executor = button.executor();
        if (executor != null)
            executor.accept(new ChoiceEventImpl(player));
        nextNode(player, data.name, nextNode);
    }

    @EventHandler
    public void event(PlayerQuitEvent event) {
        clearPlayerData(event.getPlayer());
    }

    public void clearPlayerData(Player player) {
        if (PLAYER.containsKey(player.getUniqueId())) {
            QUEUE.remove(PLAYER.get(player.getUniqueId()));
            PLAYER.remove(player.getUniqueId());
        }
    }

    public void nextNode(Player player, Component name, Node node) {
        if (PLAYER.containsKey(player.getUniqueId())) {
            QUEUE.remove(PLAYER.get(player.getUniqueId()));
        }
        String queueId = StrUtil.random(16, QUEUE.keySet());
        PLAYER.put(player.getUniqueId(), queueId);
        QUEUE.put(queueId, NodeData.of(name, node));

        Component message = Component.text("    ").appendNewline()
                .append(Component.text("    ")).appendNewline()
                .append(Component.text("    ")).append(name).appendNewline()
                .append(Component.text("    ")).appendNewline()
                .append(Component.text("    ")).append(node.text())
                .append(Component.text("    ")).appendNewline()
                .append(Component.text("    ")).appendNewline();

        BukkitRunnable runnable = null;

        List<Node> nodes = node.sub();
        boolean pass = false;
        if (nodes.size() == 1) {
            Node next = nodes.get(0);
            if (next.button() == null) {
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        nextNode(player, name, next);
                    }
                };
                pass = true;
            }
        }
        if (!pass)
            if (nodes.size() >= 1) {
                Component buttonBase = Component.text("");
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
                buttonBase = buttonBase.append(Component.text("    ")).appendNewline();

                Component finalButtonBase = buttonBase;
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        PlayerUtil.send(player, finalButtonBase);
                    }
                };
            }

        PlayerUtil.clearScreen(player);
        PlayerUtil.send(player, message);
        if (runnable != null) {
            RunnerUtil.run(runnable, node.waiting());
        }
    }

    private static class NodeData {

        private Component name;
        private Node node;

        private static NodeData of(Component name, Node node) {
            NodeData data = new NodeData();
            data.name = name;
            data.node = node;
            return data;
        }

    }

    private ConversationManager() {
    }

}
