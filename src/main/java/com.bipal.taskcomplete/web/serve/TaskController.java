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
                                       @RequestParam String taskGroupId) {
        UUID taskUUID = UUID.fromString(taskId);
        UUID taskGroupUUID = UUID.fromString(taskGroupId);

        RegisteredTask registeredTask = service.registerTask(taskUUID, taskGroupUUID, taskId, taskGroupId);

        logger.log(Level.INFO, "Registered task: " + registeredTask.toString());
        return registeredTask;
    }

    @RequestMapping(value = "/taskComplete", method = RequestMethod.GET)
    public void taskComplete(@RequestParam String taskId,
                             @RequestParam String taskGroupId,
                             @RequestParam String taskProcessGroupId) {
        UUID taskUUID = UUID.fromString(taskId);
        UUID taskGroupUUID = UUID.fromString(taskGroupId);
        UUID taskProcessGroupUUID = UUID.fromString(taskProcessGroupId);

        DoneTask doneTask = service.saveTaskDone(taskUUID, taskGroupUUID, taskProcessGroupUUID);
        logger.log(Level.INFO, doneTask.toString());
    }

    @RequestMapping(value = "/isDone", method = RequestMethod.GET)
    public boolean taskComplete(@RequestParam String taskGroupId,
                                @RequestParam String taskProcessGroupId) {
        UUID taskGroupUUID = UUID.fromString(taskGroupId);
        UUID taskProcessGroupID = UUID.fromString(taskProcessGroupId);

        return  service.areAllTasksDone(taskGroupUUID, taskProcessGroupID);
    }
}
