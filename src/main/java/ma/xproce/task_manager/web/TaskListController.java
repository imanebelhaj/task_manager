package ma.xproce.task_manager.web;


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
//    @GetMapping("/addlist")
//    public String addList() {
//        return "addlist";
//    }
    TaskListService taskListService;
    private final UserService userService;
    @Autowired
    public TaskListController(TaskListService taskListService, UserService userService) {
        this.taskListService = taskListService;
        this.userService = userService;
    }

    @GetMapping("/updatelist")
    public String updateList() {
        return "updatelist";
    }
    @GetMapping("/addlisttask")
    public String addlistask(Model model) {
        TaskList taskList = new TaskList();
        model.addAttribute("tasklist", taskList);
        return "addlist";
    }
//    @GetMapping("/addlisttask")
//    public String addlistask(Model model) {
//        // Method implementation
//    }
    @PostMapping("/add")
    public String addListAction(Model model,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "id", defaultValue =  "") Integer id,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "iconUrl") String iconUrl){
        TaskList taskList = new TaskList();
        taskList.setName(name);
        taskList.setDescription(description);
        taskList.setIconUrl(iconUrl);


        return "redirect:dashboard";
    }

    @PostMapping("/AddOnce")
    public String addList(@ModelAttribute("list") @Valid TaskList list, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addlist"; // Return the view to display validation errors
        }
        taskListService.addList( list);
        return "redirect:dashboard"; // Redirect to dashboard after successful addition
    }
    @GetMapping("/addlist")
    public String addlist(Model model) {
        model.addAttribute("list", new TaskList());
        return "addlist";
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
