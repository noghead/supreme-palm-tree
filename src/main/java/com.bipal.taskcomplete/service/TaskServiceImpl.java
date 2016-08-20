package com.bipal.taskcomplete.service;

import com.bipal.taskcomplete.adapter.TaskToRegisteredTaskAdapter;
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

    private TaskToRegisteredTaskAdapter adapter;
    private RegisteredTaskRepository registeredTaskRepository;
    private DoneTasksRepository doneTasksRepository;

    @Autowired
    public TaskServiceImpl(TaskToRegisteredTaskAdapter adapter, RegisteredTaskRepository registeredTaskRepository,
                           DoneTasksRepository doneTasksRepository) {
        this.adapter = adapter;
        this.registeredTaskRepository = registeredTaskRepository;
        this.doneTasksRepository = doneTasksRepository;
    }

    @Override
    public List<RegisteredTask> registerTasks(List<Task> groupOfTasks) {
        List<RegisteredTask> tasksToRegister = groupOfTasks.stream()
                .map(task -> adapter.convert(task))
                .collect(Collectors.toList());

        return registeredTaskRepository.save(tasksToRegister);
    }

    @Override
    public RegisteredTask registerTask(Task task) {
        RegisteredTask registeredTask = adapter.convert(task);
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
        List<DoneTask> doneTasks = doneTasksRepository.findAll(taskGroupId, processId);
        doneTasks.containsAll(registeredTasks);
        return true;
    }
}
