package net.deechael.conversation.example;

import net.deechael.conversation.ConversationAPI;
import net.deechael.conversation.api.Conversation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Example {

    public static void conversation(Player player) {
        Conversation conversation = ConversationAPI.newConversation()
                .text(Component.text("Hello, traveller!"))
                .node(ConversationAPI.newNode()
                        .text(Component.text("What can I do for ya?"))
                        .sub(ConversationAPI.newNode()
                                .button(ConversationAPI.newButton()
                                        .name(Component.text("[YES, AN APPLE PLEASE]").color(NamedTextColor.RED))
                                        .hover(Component.text("Click to get an apple").color(NamedTextColor.YELLOW))
                                        .executor(event -> {
                                            event.player().getInventory().addItem(new ItemStack(Material.APPLE));
                                        })
                                        .build())
                                .text(Component.text("Okay, here you are!"))
                                .build(),
                                ConversationAPI.newNode()
                                        .button(ConversationAPI.newButton()
                                                .name(Component.text("[NO, THANKS]").color(NamedTextColor.GRAY))
                                                .build())
                                        .text(Component.text("Well, good bye!"))
                                        .build()
                        )
                        .build()
                ).build();
        conversation.start(player);
    }

}
