package ma.xproce.task_manager.service;


import ma.xproce.task_manager.dao.entites.SubTask;
import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    //add task to list
    // delete task from list
    // edit task
    // update task completion status

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String name, String description, Date deadline, String priorityLevel, TaskList taskList, List<SubTask> subtasks) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setPriorityLevel(priorityLevel);
        task.setTaskList(taskList);
        task.setSubtasks(subtasks);
        task.setCreationDate(new Date()); // Set creation date
        task.setLastUpdateDate(new Date()); // Set last update date
        task.setDeadlinePassed(isDeadlinePassed(deadline));
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task editTask(Long taskId, String name, String description, Date deadline, String priorityLevel, TaskList taskList, List<SubTask> subtasks, boolean completed) {
        Task taskToUpdate = taskRepository.findById(taskId).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setName(name);
            taskToUpdate.setDescription(description);
            taskToUpdate.setDeadline(deadline);
            taskToUpdate.setPriorityLevel(priorityLevel);
            taskToUpdate.setTaskList(taskList);
            taskToUpdate.setSubtasks(subtasks);
            taskToUpdate.setCompleted(completed);
            taskToUpdate.setCompletionDate(completed ? new Date() : null);
            taskToUpdate.setLastUpdateDate(new Date()); // Update last update date
            taskToUpdate.setDeadlinePassed(isDeadlinePassed(deadline));
            return taskRepository.save(taskToUpdate);

        }
        return null; // Task not found
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

    private boolean isDeadlinePassed(Date deadline) {
        if (deadline == null) {
            return false;
        }
        Date currentDate = new Date();
        return deadline.before(currentDate);
    }

    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }
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
