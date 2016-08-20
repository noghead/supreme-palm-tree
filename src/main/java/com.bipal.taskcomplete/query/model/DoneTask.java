package com.bipal.taskcomplete.query.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "done_task")
public class DoneTask {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "task_id")
    private UUID taskId;
    @Column(name = "group_id")
    private UUID groupId;
    @Column(name = "process_id")
    private UUID processId;

    public DoneTask() {
    }

    public DoneTask(UUID taskId, UUID groupId, UUID processId) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.processId = processId;
    }

    public Long getId() {
        return id;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public UUID getProcessId() {
        return processId;
    }

    @Override
    public String toString() {
        return "DoneTask{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", groupId=" + groupId +
                ", processId=" + processId +
                '}';
    }
}
