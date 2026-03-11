package com.asutosh5941;

class StudentData {
    private String name;
    private int id;
    private float percentage;

    // default data-holding constructor
    StudentData(String name, int id, float percentage) {
        this.name = name;
        this.id = id;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPercentage() {
        return percentage;
    }
}
