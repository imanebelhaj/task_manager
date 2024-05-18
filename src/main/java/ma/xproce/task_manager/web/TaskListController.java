package ma.xproce.task_manager.web;


import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@RequestMapping("/tasklists")
@Controller
public class TaskListController {
    @GetMapping("/addlist")
    public String addList() {
        return "addlist";
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
