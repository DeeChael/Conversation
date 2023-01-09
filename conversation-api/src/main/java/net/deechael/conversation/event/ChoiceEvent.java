package net.deechael.conversation.event;


import net.deechael.conversation.api.Button;

/**
 * Event which will be called when player choose a choice
 */
public interface ChoiceEvent extends ConversationEvent {

    /**
     * Get the button clicked
     *
     * @return button
     */
    Button getButton();

}
