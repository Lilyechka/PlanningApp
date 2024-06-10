package com.liliia.Controllers;

import com.liliia.model.Task;
import com.liliia.model.User;
import com.liliia.service.TaskService;
import com.liliia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showTasksPage(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping
    public String getTasksByCurrentUser(Model model) {
        User currentUser = getCurrentUser();
        List<Task> tasks = taskService.getTasksByUserId(currentUser.getId());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task_form";
    }

    @PostMapping("/new")
    public String createTask(@ModelAttribute Task task) {
        User currentUser = getCurrentUser();
        task.setUser(currentUser);
        task.setDateOfCreation(LocalDateTime.now());
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        Task existingTask = taskService.getTaskById(id);
        task.setId(id);
        task.setDateOfCreation(existingTask.getDateOfCreation());
        taskService.saveTask(task);
        return "redirect:/tasks";
    }



    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        return userService.getUserByUsername(username);
    }
}
