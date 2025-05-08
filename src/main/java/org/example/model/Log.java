package org.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Log {
    private String orderId;
    private String employeeId;
    private LocalDateTime date;
    private String state;

    public Log(String orderId, String employeeId, String state) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.date = LocalDateTime.now();
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Log{" +
                "orderId='" + orderId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                '}';
    }
}
