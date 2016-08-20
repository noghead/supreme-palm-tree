package com.bipal.taskcomplete.service.model;

import java.util.UUID;

public class Task {
    private final UUID taskId;
    private final UUID groupId;
    private String taskName;
    private String groupName;

    public Task(UUID taskId, UUID groupId, String taskName, String groupName) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.taskName = taskName;
        this.groupName = groupName;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
