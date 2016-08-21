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
        RegisteredTask registeredTask = new RegisteredTask(taskId, taskGroupId, taskName, taskGroupName);
        return registeredTaskRepository.save(registeredTask);
    }

    @Override
    public DoneTask saveTaskDone(UUID taskId, UUID taskGroupId, UUID processId) {
        DoneTask doneTask = new DoneTask(taskId, taskGroupId, processId);
        return doneTasksRepository.save(doneTask);
    }

    @Override
    public boolean areAllTasksDone(UUID taskGroupId, UUID processId) {
        List<RegisteredTask> registeredTasks = registeredTaskRepository.findAllByTaskGroup(taskGroupId);
        List<Task> registeredTasksSet = registeredTasks.stream().map(registeredTaskToTaskAdapter::convert)
                .collect(Collectors.toList());

        List<DoneTask> doneTasks = doneTasksRepository.findAll(taskGroupId, processId);
        List<Task> doneTasksSet = doneTasks.stream().map(doneTaskToTaskAdapter::convert)
                .collect(Collectors.toList());

        return doneTasksSet.containsAll(registeredTasksSet);
    }
}
