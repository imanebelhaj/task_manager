package ma.xproce.task_manager.web;

import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/tasks")
@Controller
public class TaskController {

    @GetMapping("/addtask")
    public String addTask() {
        return "addtask";
    }




    /*
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task addedTask = taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTask);
    }



    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
    /*

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> editTask(@PathVariable Long taskId, @RequestParam String newName) {
        Task updatedTask = taskService.editTask(taskId, newName);
        if (updatedTask != null) {
            return ResponseEntity.ok().body(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     */
/*
    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskCompletion(@PathVariable Long taskId, @RequestParam boolean completed) {
        Task updatedTask = taskService.updateTaskCompletion(taskId, completed);
        if (updatedTask != null) {
            return ResponseEntity.ok().body(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long taskId) {
        Task task = taskService.findTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok().body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

//    @GetMapping("/completed")
//    public ResponseEntity<List<Task>> getAllCompletedTasks() {
//        List<Task> tasks = taskService.findAllCompletedTask();
//        return ResponseEntity.ok().body(tasks);
//    }
//
//    @GetMapping("/incomplete")
//    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
//        List<Task> tasks = taskService.findAllInCompleteTask();
//        return ResponseEntity.ok().body(tasks);
//    }

 */
}

