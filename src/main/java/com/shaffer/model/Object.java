package com.shaffer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Object implements Serializable{
    public String id;
    public String name;
    public LocalDateTime timestamp;

    public Object(String id, String name) {
        this.id = id;
        this.name = name;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Object{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
