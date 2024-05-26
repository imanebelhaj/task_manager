package ma.xproce.task_manager.web;

import jakarta.servlet.http.HttpSession;
import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.service.TaskListService;
import ma.xproce.task_manager.service.TaskManager;
import ma.xproce.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {
    private final TaskListService taskListService;
    private final TaskService taskService;

    @Autowired
    public DashboardController(TaskListService taskListService, TaskService taskService) {
        this.taskListService = taskListService;
        this.taskService = taskService;
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/signin";
        }
        List<TaskList> taskLists = taskListService.getTaskListsByUser(loggedInUser);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("taskLists", taskLists);
        Long selectedTaskListId = (Long) session.getAttribute("selectedTaskListId");
        if (selectedTaskListId != null) {
            TaskList selectedTaskList = taskListService.getTaskListById(selectedTaskListId);
            model.addAttribute("selectedTaskList", selectedTaskList);
            model.addAttribute("tasks", taskService.getTasksByTaskListId(selectedTaskListId));
        }

        return "dashboard";
    }

    @GetMapping("/dashboard/select/{taskListId}")
    public String selectTaskList(@PathVariable("taskListId") Long taskListId, HttpSession session) {
        // Store the ID of the selected task list in session
        session.setAttribute("selectedTaskListId", taskListId);
        return "redirect:/dashboard";
    }








}
