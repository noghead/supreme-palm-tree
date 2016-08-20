package com.bipal.taskcomplete.adapter;

import com.bipal.taskcomplete.query.model.RegisteredTask;
import com.bipal.taskcomplete.service.model.Task;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskToRegisteredTaskAdapter implements Adapter<Task, RegisteredTask> {
    @Override
    public RegisteredTask convert(Task task) {
        Objects.requireNonNull(task);

        return new RegisteredTask(task.getTaskId(), task.getGroupId(), task.getTaskName(), task.getGroupName());
    }
}
