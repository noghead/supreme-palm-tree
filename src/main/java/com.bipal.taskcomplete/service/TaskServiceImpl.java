package com.bipal.taskcomplete.service;

import com.bipal.taskcomplete.adapter.DoneTaskToTaskAdapter;
import com.bipal.taskcomplete.adapter.RegisteredTaskToTaskAdapter;
import com.bipal.taskcomplete.query.DoneTasksRepository;
import com.bipal.taskcomplete.query.RegisteredTaskRepository;
import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.query.model.RegisteredTask;
import com.bipal.taskcomplete.service.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    public static Logger logger = Logger.getLogger(TaskServiceImpl.class.getSimpleName());

    private RegisteredTaskToTaskAdapter registeredTaskToTaskAdapter;
    private DoneTaskToTaskAdapter doneTaskToTaskAdapter;
    private RegisteredTaskRepository registeredTaskRepository;
    private DoneTasksRepository doneTasksRepository;

    @Autowired
    public TaskServiceImpl(RegisteredTaskToTaskAdapter registeredTaskToTaskAdapter,
                           DoneTaskToTaskAdapter doneTaskToTaskAdapter,
                           RegisteredTaskRepository registeredTaskRepository,
                           DoneTasksRepository doneTasksRepository) {
        this.registeredTaskToTaskAdapter = registeredTaskToTaskAdapter;
        this.doneTaskToTaskAdapter = doneTaskToTaskAdapter;
        this.registeredTaskRepository = registeredTaskRepository;
        this.doneTasksRepository = doneTasksRepository;
    }

    @Override
    public RegisteredTask registerTask(UUID taskId, UUID taskGroupId, String taskName, String taskGroupName) {
        Objects.requireNonNull(taskId);
        Objects.requireNonNull(taskGroupId);
        Objects.requireNonNull(taskName);
        Objects.requireNonNull(taskGroupName);
        RegisteredTask registeredTask = new RegisteredTask(taskId, taskGroupId, taskName, taskGroupName);
        return registeredTaskRepository.save(registeredTask);
    }

    @Override
    public DoneTask saveTaskDone(UUID taskId, UUID taskGroupId, UUID taskProcessGroupId) {
        Objects.requireNonNull(taskId);
        Objects.requireNonNull(taskGroupId);
        Objects.requireNonNull(taskProcessGroupId);
        DoneTask doneTask = new DoneTask(taskId, taskGroupId, taskProcessGroupId);
        return doneTasksRepository.save(doneTask);
    }

    @Override
    public boolean areAllTasksDone(UUID taskGroupId, UUID taskProcessGroupId) {
        Objects.requireNonNull(taskGroupId);
        Objects.requireNonNull(taskProcessGroupId);

        List<Task> registeredTasksSet = registeredTaskRepository.findAllByTaskGroup(taskGroupId)
                .stream().map(registeredTaskToTaskAdapter::convert)
                .collect(Collectors.toList());

        List<Task> doneTasksSet = doneTasksRepository.findAll(taskGroupId, taskProcessGroupId)
                .stream().map(doneTaskToTaskAdapter::convert)
                .collect(Collectors.toList());

        return doneTasksSet.containsAll(registeredTasksSet);
    }
}
