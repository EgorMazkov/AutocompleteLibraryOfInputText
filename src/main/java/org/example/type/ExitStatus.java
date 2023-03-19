package org.example.type;

public enum ExitStatus {
    ERROR(1),
    SUCCESS(0);
    public final int count;

    ExitStatus(int count) {
        this.count = count;
    }
}
