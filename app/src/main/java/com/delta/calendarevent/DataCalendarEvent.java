package com.delta.calendarevent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrabVN_1791 on 26.07.2017.
 */

public class DataCalendarEvent {

    private String eventName;
    private String eventDesc;
    private String eventType;
    private String eventDate;

    public DataCalendarEvent(String eventName, String eventDesc, String eventType, String eventDate) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
