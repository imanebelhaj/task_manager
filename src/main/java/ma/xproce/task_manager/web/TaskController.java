package ma.xproce.task_manager.web;

import jakarta.servlet.http.HttpSession;
import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.service.TaskListService;
import ma.xproce.task_manager.service.TaskManager;
import ma.xproce.task_manager.service.TaskService;
import ma.xproce.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import jakarta.validation.Valid;


@RequestMapping("/tasks")
@Controller
public class TaskController {
    private final TaskService taskService;
    private final TaskListService taskListService;

    @Autowired
    public TaskController(TaskService taskService, TaskListService taskListService) {
        this.taskService = taskService;
        this.taskListService = taskListService;
    }


    @GetMapping("/addtask")
    public String showTaskForm(Model model) {
        List<TaskList> taskLists = taskListService.getAllTaskLists(); // Retrieve all task lists
        model.addAttribute("task", new Task());
        model.addAttribute("taskLists", taskLists);
        return "addtask";
    }

    @PostMapping("/addtask")
    public String addTask(@ModelAttribute("task") Task task, @RequestParam("taskListId") Long taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId);
        if (taskList == null) {
            return "redirect:/tasks/addtask";
        }
        task.setTaskList(taskList);
        task.setCreationDate(new Date());
        task.setLastUpdateDate(new Date());
        taskService.save(task);
        return "redirect:/dashboard";
    }



//    @GetMapping("/updatetask")
//    public String updateTask() {
//        return "updatetask";
//    }
    @GetMapping("/updatetask/{taskId}")
    public String showUpdateTaskForm(@PathVariable("taskId") Long taskId, Model model) {
        // Retrieve the task by ID
        Task task = taskService.findTaskById(taskId);

        // Check if the task exists
        if (task == null) {
            // Handle case where task with given ID is not found
            return "redirect:/dashboard";
        }

        // Retrieve all task lists
        List<TaskList> taskLists = taskListService.getAllTaskLists();

        // Add the task and task lists to the model
        model.addAttribute("task", task);
        model.addAttribute("taskLists", taskLists);

        return "updatetask"; // Return the name of the HTML template for updating a task
    }

    @PostMapping("/updatetask/{taskId}")
    public String updateTask(@PathVariable("taskId") Long taskId, @ModelAttribute("task") @Valid Task updatedTask, BindingResult result) {
        if (result.hasErrors()) {
            return "updatetask";
        }

        // Retrieve the original task from the database
        Task originalTask = taskService.findTaskById(taskId);

        // Update the properties of the original task with the values from the updated task
        originalTask.setName(updatedTask.getName());
        originalTask.setDescription(updatedTask.getDescription());
        originalTask.setDeadline(updatedTask.getDeadline());
        originalTask.setLastUpdateDate(new Date()); // Update last update date

        // Save the updated task
        taskService.save(originalTask);

        return "redirect:/dashboard"; // Redirect to dashboard after updating the task
    }

    
    @PostMapping("/deletetask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        // Implement the logic to delete the task with the given ID
        taskService.deleteTask(taskId);
        return "redirect:/dashboard"; // Redirect to the dashboard after deleting the task
    }




}

