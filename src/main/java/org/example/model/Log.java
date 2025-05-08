package org.example.model;

import java.time.LocalDateTime;

public class Log {
    private final String orderId;
    private final String employeeId;
    private final LocalDateTime date;
    private final String state;

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
