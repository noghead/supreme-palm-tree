package com.bipal.taskcomplete.adapter;

import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.service.model.Task;

import java.util.Objects;

/**
 * Created by bipalA on 8/21/16.
 */
public class DoneTaskToTaskAdapter implements Adapter<DoneTask, Task> {
    @Override
    public Task convert(DoneTask dt) {
        Objects.requireNonNull(dt);
        return new Task(dt.getTaskId(), dt.getGroupId());
    }
}
