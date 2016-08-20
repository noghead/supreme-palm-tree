package com.bipal.taskcomplete.service;

import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.query.model.RegisteredTask;
import com.bipal.taskcomplete.service.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    public List<RegisteredTask> registerTasks(List<Task> groupOfTasks);

    public RegisteredTask registerTask(Task task);

    public DoneTask saveTaskDone(UUID taskUUID, UUID taskGroupId, UUID processId);

    public boolean areAllTasksDone(UUID taskGroupId, UUID processId);
}
