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



    @GetMapping("/updatetask")
    public String updateTask() {
        return "updatetask";
    }


}

