package ma.xproce.task_manager.service;

import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.dao.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    @Autowired
    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public List<TaskList> getTaskListsByUser(User user) {
        return taskListRepository.findByUser(user);
    }
    public TaskList getTaskListById(Long taskListId) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        return taskListOptional.orElse(null);
    }
    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }



    public TaskList addTaskToList(Long listId, Task task) {
        Optional<TaskList> optionalTaskList = taskListRepository.findById(listId);
        if (optionalTaskList.isPresent()) {
            TaskList taskList = optionalTaskList.get();
            taskList.getTasks().add(task);
            return taskListRepository.save(taskList);
        } else {
            throw new IllegalArgumentException("Task list with ID " + listId + " not found");
        }
    }


    public void deleteList(Long listId) {
        taskListRepository.deleteById(listId);
    }




}
