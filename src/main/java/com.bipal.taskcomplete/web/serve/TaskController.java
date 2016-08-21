package com.bipal.taskcomplete.web.serve;

import com.bipal.taskcomplete.query.model.DoneTask;
import com.bipal.taskcomplete.query.model.RegisteredTask;
import com.bipal.taskcomplete.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class TaskController {

    public static Logger logger = Logger.getLogger(TaskController.class.getSimpleName());

    private TaskService service;

    @Autowired
    public TaskController(TaskService taskService){
        this.service = taskService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public RegisteredTask registerTask(@RequestParam String taskId,
                                       @RequestParam String groupId) {
        UUID taskUUID = UUID.fromString(taskId);
        UUID groupUUID = UUID.fromString(groupId);

        RegisteredTask registeredTask = service.registerTask(taskUUID, groupUUID, taskId, groupId);

        logger.log(Level.INFO, "Registered task: " + registeredTask.toString());
        return registeredTask;
    }

    @RequestMapping(value = "/taskComplete", method = RequestMethod.GET)
    public void taskComplete(@RequestParam String taskId,
                             @RequestParam String groupId,
                             @RequestParam String processId) {
        UUID taskUUID = UUID.fromString(taskId);
        UUID groupUUID = UUID.fromString(groupId);
        UUID processID = UUID.fromString(processId);

        DoneTask doneTask = service.saveTaskDone(taskUUID, groupUUID, processID);
        logger.log(Level.INFO, doneTask.toString());
    }

    @RequestMapping(value = "/isDone", method = RequestMethod.GET)
    public boolean taskComplete(@RequestParam String groupId,
                                @RequestParam String processId) {
        UUID groupUUID = UUID.fromString(groupId);
        UUID processID = UUID.fromString(processId);

        return  service.areAllTasksDone(groupUUID, processID);
    }
}
