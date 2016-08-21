package com.bipal.taskcomplete.adapter;

import com.bipal.taskcomplete.query.model.RegisteredTask;
import com.bipal.taskcomplete.service.model.Task;

import java.util.Objects;

/**
 * Created by bipalA on 8/21/16.
 */
public class RegisteredTaskToTaskAdapter implements Adapter<RegisteredTask, Task> {
    @Override
    public Task convert(RegisteredTask rt) {
        Objects.requireNonNull(rt);
        return new Task(rt.getTaskId(), rt.getGroupId());
    }
}
