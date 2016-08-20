package com.bipal.taskcomplete.query.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "registered_task")
public class RegisteredTask {
    @Id
    @Column(name = "task_id")
    private UUID taskId;
    @Column(name = "group_id")
    private UUID groupId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "group_name")
    private String groupName;

    protected RegisteredTask() {}

    public RegisteredTask(UUID taskId, UUID groupId, String taskName, String groupName) {
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

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "RegisteredTask{" +
                "taskId=" + taskId +
                ", groupId=" + groupId +
                ", taskName='" + taskName + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
