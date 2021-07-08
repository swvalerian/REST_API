package com.swvalerian.restapi.model;

import java.util.List;

public class User {
    private Integer userId;
    private String eventName;
    private Integer fileId;
    List<Event> events;

    public User() {
    }

    public User(Integer userId, String eventName, Integer fileId, List<Event> events) {
        this.userId = userId;
        this.eventName = eventName;
        this.fileId = fileId;
        this.events = events;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", fileId=" + fileId +
                ", events=" + events +
                '}';
    }
}
