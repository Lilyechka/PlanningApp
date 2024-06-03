package com.liliia.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import com.liliia.model.Task;
import com.liliia.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            task.setTopic(taskDetails.getTopic());
            task.setDeadline(taskDetails.getDeadline());
            task.setDescriptionTask(taskDetails.getDescriptionTask());
            return taskService.saveTask(task);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}


