package ma.xproce.task_manager.web;

import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.service.TaskManager;
import ma.xproce.task_manager.service.TaskService;
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
    @Autowired
    TaskService taskService;
    TaskManager taskManager;


    @GetMapping("/updatetask")
    public String updateTask() {
        return "updatetask";
    }

    @PostMapping("/add")
    public String addTaskAction(Model model,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "id", defaultValue =  "") Integer id,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "Deadline") Date Deadline,
                          @RequestParam(name = "priorityLevel")String priorityLevel){
        Task task = new Task(id, name, description, Deadline,priorityLevel);
        taskService.addTask(task);
        return "redirect:dashboard";
    }
    @PostMapping("/AddOnce")
    public String addTask(Model model,
                                 @Valid Task task,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addtask" ;
        }
        taskService.addTask(task);
        return "redirect:dashboard";
    }
    @GetMapping("/addtask")
    public String addtask(Model model) {
        model.addAttribute("task", new Task());
        return "addtask";
    }



    /*
     @PostMapping("/ajouter")
    public String ajouterProduitAction(Model model,
                                 @RequestParam(name = "title") String title,
                                 @RequestParam(name = "id", defaultValue =  "") Integer id,
                                 @RequestParam(name = "designation") String designation,
                                 @RequestParam(name = "prix") double prix) {
        Produit produit = new Produit(id, title, designation, prix);
        produitManager.addProduit(produit);
        return "redirect:indexpage";
    }

    @PostMapping("/ajouterOnce")
    public String ajouterProduit(Model model,
                                 @Valid Produit produit,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "ajouterproduit" ;
        }
        produitManager.addProduit(produit);
        return "redirect:indexpage";
    }


    @GetMapping("/ajouterproduit")
    public String ajouterproduit(Model model) {
        model.addAttribute("produit", new Produit());
        return "ajouterproduit";
    }


     */

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

