package ma.xproce.task_manager.service;

import ma.xproce.task_manager.dao.entites.Task;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface TaskManager {
    public Task addTask(Task task, Long taskListId);


    public void deleteTask(Long taskId);
    public Task updateTask(Task task);
    public Task updateTaskCompletion(Long taskId, boolean completed);
    boolean isDeadlinePassed(Date deadline);
    public Task findTaskById(Long id);
    public List<Task> getAllTasks();

}
