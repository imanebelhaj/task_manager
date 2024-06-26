package ma.xproce.task_manager.service;


import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.repositories.TaskListRepository;
import ma.xproce.task_manager.dao.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService implements TaskManager {

    private final TaskRepository taskRepository;
    private TaskListRepository taskListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task save(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> getTasksByTaskListId(Long taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }




    @Override
    public Task addTask(Task task, Long taskListId) {
        task.setCreationDate(new Date());
        task.setLastUpdateDate(null); // Assuming last update date should be null initially
        task.setCompleted(false);
        task.setDeadlinePassed(false);
        task.setCompletionDate(null);
        TaskList taskList = taskListRepository.findById(taskListId).orElse(null);
        if (taskList != null) {
            task.setTaskList(taskList);
            return taskRepository.save(task);
        } else {
            // Handle the case where the TaskList is not found or available
            // For example, you can log an error or throw an exception
            return null;
        }
    }



    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    public Task updateTaskCompletion(Long taskId, boolean completed) {
        Task taskToUpdate = taskRepository.findById(taskId).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setCompleted(completed);
            taskToUpdate.setCompletionDate(completed ? new Date() : null); // Set completion date if completed
            taskToUpdate.setLastUpdateDate(new Date()); // Update last update date
            return taskRepository.save(taskToUpdate);
        }
        return null; // Task not found
    }
    @Override
    public boolean isDeadlinePassed(Date deadline) {
        if (deadline == null) {
            return false;
        }
        Date currentDate = new Date();
        return deadline.before(currentDate);
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }




//    public List<Task> findAllCompletedTask() {
//        return taskRepository.findByCompletedTrue();
//    }
//
//    public List<Task> findAllInCompleteTask() {
//        return taskRepository.findByCompletedFalse();
//    }
}
