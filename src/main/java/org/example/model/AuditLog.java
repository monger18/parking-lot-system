package org.example.model;

import java.time.LocalDateTime;

public class AuditLog {
    private int id;
    private String actionType;
    private String description;
    private LocalDateTime createdAt;

    public AuditLog() {}

    public AuditLog(int id, String actionType, String description, LocalDateTime createdAt) {
        this.id = id;
        this.actionType = actionType;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
