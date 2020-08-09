package Models;

import java.io.Serializable;

public class Message implements Serializable {
    String from;
    Manager to;
    String title;
    String text;

    public Message(Person from, Manager to, String title, String text) {
        this.from = from.getUsername();
        this.to = to;
        this.title = title;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public Manager getTo() {
        return to;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
