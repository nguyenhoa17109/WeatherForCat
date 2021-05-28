package com.nguyenhoa.weatherforcast.model;

import java.util.List;

public class Alert {
    private String sender_name;
    private String event;
    private float start;
    private float end;
    private String description;
    public List<String> tags;

    public Alert() {
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public float getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "sender_name='" + sender_name + '\'' +
                ", event='" + event + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }
}
