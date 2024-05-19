package ma.xproce.task_manager.web;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.service.TaskListService;
import ma.xproce.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/tasklists")
public class TaskListController {

    TaskListService taskListService;
    private final UserService userService;
    @Autowired
    public TaskListController(TaskListService taskListService, UserService userService) {
        this.taskListService = taskListService;
        this.userService = userService;
    }

    @GetMapping("/addlist")
    public String showListForm(Model model) {
        model.addAttribute("tasklist", new TaskList());
        return "addlist";
    }


    @PostMapping("/addlist")
    public String addListAction(@ModelAttribute("tasklist") @Valid TaskList taskList,BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "addlist";
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/users/signin";
        }
        taskList.setUser(loggedInUser);
        taskList.setCreationDate(new Date());
        taskList.setLastUpdateDate(new Date());

        taskListService.save(taskList);
        return "redirect:/dashboard"; // Assuming dashboard is the correct redirect URL
    }

    @GetMapping("/updatelist")
    public String updateList() {
        return "updatelist";
    }




    /*
    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskList> addTaskList(@RequestBody TaskList taskList) {
        TaskList createdTaskList = taskListService.addList(taskList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskList);
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long listId) {
        taskListService.deleteList(listId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{listId}")
    public ResponseEntity<TaskList> getTaskList(@PathVariable Long listId) {
        Optional<TaskList> optionalTaskList = taskListService.readList(listId);
        return optionalTaskList.map(taskList -> ResponseEntity.ok().body(taskList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{listId}/tasks")
    public ResponseEntity<TaskList> addTaskToList(@PathVariable Long listId, @RequestBody Task task) {
        TaskList updatedTaskList = taskListService.addTaskToList(listId, task);
        return ResponseEntity.ok().body(updatedTaskList);
    }



     */


}
