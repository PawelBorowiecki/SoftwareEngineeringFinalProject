package org.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Log {
    private int orderId;
    private int employeeId;
    private LocalDateTime date;
    private String state;

    public Log(int orderId, int employeeId, String state) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.date = LocalDateTime.now();
        this.state = state;
    }
}
