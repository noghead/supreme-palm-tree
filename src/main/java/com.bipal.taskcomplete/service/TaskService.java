package com.bipal.taskcomplete.service;

import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.query.model.RegisteredTask;

import java.util.UUID;

public interface TaskService {

    public RegisteredTask registerTask(UUID taskId, UUID taskGroupId, String taskName, String taskGroupName);

    public DoneTask saveTaskDone(UUID taskUUID, UUID taskGroupId, UUID taskProcessGroupId);

    public boolean areAllTasksDone(UUID taskGroupId, UUID taskProcessGroupId);
}
