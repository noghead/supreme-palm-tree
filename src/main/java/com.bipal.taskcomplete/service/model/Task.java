package com.bipal.taskcomplete.service.model;

import java.util.Objects;
import java.util.UUID;

public class Task {
    private final UUID taskId;
    private final UUID groupId;

    public Task(UUID taskId, UUID groupId) {
        this.taskId = taskId;
        this.groupId = groupId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) &&
                Objects.equals(groupId, task.groupId);
    }
}
