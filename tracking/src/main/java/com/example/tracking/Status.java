package com.example.tracking;

public class Status {
    private int id;
    private String name;

    public Status(
            int id,
            String name) {
        this.id = id;
        this.name = name;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
