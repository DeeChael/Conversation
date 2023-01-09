package net.deechael.conversation.test;

import net.deechael.conversation.api.Button;
import net.deechael.conversation.api.Conversation;
import net.deechael.conversation.api.Node;
import net.deechael.conversation.event.ConversationEndEvent;
import net.deechael.conversation.event.ConversationStartEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class ConversationTest extends JavaPlugin implements Listener {

    private Conversation buildConversation() {
        return Conversation.of()
                .name(Component.text("Old Man").color(NamedTextColor.GOLD))
                .text(Component.text("Oh, young man"))
                .sound(Sound.ENTITY_VILLAGER_TRADE)
                .node(Node.of()
                        .button(Button.of()
                                .name(Component.text("[Who are your?]").color(NamedTextColor.GRAY))
                                .build())
                        .text(Component.text("I'm just an old man"))
                        .sound(Sound.ENTITY_VILLAGER_TRADE)
                        .waiting(2)
                        .sub(Node.of()
                                .text(Component.text("It's for a long time that I've seen anybody here"))
                                .sound(Sound.ENTITY_VILLAGER_TRADE)
                                .sub(Node.of()
                                        .button(Button.of()
                                                .name(Component.text("[Oh...]").color(NamedTextColor.GRAY))
                                                .build())
                                        .text(Component.text("Come and sit down, let's talk for a while"))
                                        .sound(Sound.ENTITY_VILLAGER_TRADE)
                                        .sub(Node.of()
                                                .button(Button.of()
                                                        .name(Component.text("[I gtg]").color(NamedTextColor.GRAY))
                                                        .build())
                                                .text(Component.text("Well, you are same as others. Bye."))
                                                .sound(Sound.ENTITY_VILLAGER_NO)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    @EventHandler
    public void onStarting(ConversationStartEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999, 10, false, false));
            }
        }.runTask(this);
    }

    @EventHandler
    public void onEnding(ConversationEndEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
            }
        }.runTask(this);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        try {
            CommandMap commandMap = (CommandMap) Bukkit.getServer().getClass().getMethod("getCommandMap").invoke(Bukkit.getServer());
            commandMap.register("conversation", new Command("test") {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
                    if (!(sender instanceof Player player))
                        return true;
                    buildConversation().start(player);
                    return true;
                }
            });
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}
