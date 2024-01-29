package com.example.samst;

public class EventData {
    private final String eventName;
    private final String eventlink;
    private final String mode;

    public EventData(String eventName, String eventlink, String mode) {
        this.eventName = eventName;
        this.eventlink = eventlink;
        this.mode = mode;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventlink() {
        return eventlink;
    }

    public String getMode() {
        return mode;
    }
}
