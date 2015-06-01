package nl.ovsoftware;

import java.io.Serializable;

/**
 * Created by jeroen.weijers on 1-6-2015.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -2588451022159554593L;

    private final String sender;

    private final String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
