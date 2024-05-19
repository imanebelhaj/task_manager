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

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @GetMapping("/updatelist/{listId}")
    public String showUpdateForm(@PathVariable("listId") Long listId, Model model) {
        // Retrieve the task list by ID
        TaskList taskList = taskListService.getTaskListById(listId);

        // Check if the task list exists
        if (taskList == null) {
            // Handle case where task list with given ID is not found
            return "redirect:/dashboard";
        }

        // Add the task list to the model
        model.addAttribute("tasklist", taskList);

        return "updatelist";
    }

    @PostMapping("/updatelist/{listId}")
    public String updateListAction(@PathVariable("listId") Long listId, @ModelAttribute("tasklist") @Valid TaskList updatedTaskList, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "updatelist";
        }

        // Retrieve the original task list from the database
        TaskList originalTaskList = taskListService.getTaskListById(listId);

        // Update the properties of the original task list with the values from the updated task list
        originalTaskList.setName(updatedTaskList.getName());
        originalTaskList.setDescription(updatedTaskList.getDescription());
        originalTaskList.setLastUpdateDate(new Date()); // Update last update date

        // Save the updated task list
        taskListService.save(originalTaskList);

        return "redirect:/dashboard"; // Redirect to dashboard after updating the task list
    }
    @PostMapping("/deletelist/{listId}")
    public String deleteList(@PathVariable("listId") Long listId, HttpSession session) {
        // Delete the task list with the given ID
        taskListService.deleteTaskList(listId);

        // Check if the deleted list was previously selected
        Long selectedTaskListId = (Long) session.getAttribute("selectedTaskListId");
        if (selectedTaskListId != null && selectedTaskListId.equals(listId)) {
            // Clear the selected task list from session
            session.removeAttribute("selectedTaskListId");
        }

        // Redirect to dashboard after deletion
        return "redirect:/dashboard";
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
