/**
 * Event.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

import java.util.LinkedList;

public class Event {

    private EventType eventType;

    LinkedList<String> text = new LinkedList<>();

    public Event(EventType eventType) {
        this.setEventType(eventType);
    }

    public Event(EventType eventType, LinkedList<String> text) {
        this.setEventType(eventType);
        this.setText(text);
    }

    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventType
     *            the eventType to set
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the text
     */
    public LinkedList<String> getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(LinkedList<String> text) {
        this.text = text;
    }

}
