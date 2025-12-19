package com.example.Clinica.modules.admission.model;

public enum TriageLevel {
    RED(1), ORANGE(2), YELLOW(3), GREEN(4), BLUE(5);

    private final int priority;
    TriageLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

}
