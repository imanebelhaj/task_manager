package ma.xproce.task_manager.service;

import ma.xproce.task_manager.dao.entites.Task;
import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {
    //addlist
    //deletelist
    //readlist or view list

    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
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
    public TaskList updateTaskList(Long taskListId, TaskList updatedTaskList) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isPresent()) {
            TaskList existingTaskList = taskListOptional.get();
            existingTaskList.setName(updatedTaskList.getName());
            existingTaskList.setDescription(updatedTaskList.getDescription());
            existingTaskList.setIconUrl(updatedTaskList.getIconUrl());
            existingTaskList.setLastUpdateDate(new Date());
            return taskListRepository.save(existingTaskList);
        }
        return null; // TaskList not found
    }

    public TaskList addList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public void deleteList(Long listId) {
        taskListRepository.deleteById(listId);
    }

    public Optional<TaskList> readList(Long listId) {
        return taskListRepository.findById(listId);
    }



    /*

    public void deleteTaskFromList(Long listId, Long taskId) {
        Optional<TaskList> optionalTaskList = taskListRepository.findById(listId);
        if (optionalTaskList.isPresent()) {
            TaskList taskList = optionalTaskList.get();
            taskList.getTasks().removeIf(task -> task.getId().equals(taskId));
            taskListRepository.save(taskList);
        } else {
            throw new IllegalArgumentException("Task list with ID " + listId + " not found");
        }
    }
    public TaskList updateTaskInList(Long listId, Task updatedTask) {
        Optional<TaskList> optionalTaskList = taskListRepository.findById(listId);
        if (optionalTaskList.isPresent()) {
            TaskList taskList = optionalTaskList.get();
            for (Task task : taskList.getTasks()) {
                if (task.getId().equals(updatedTask.getId())) {
                    // Update task details
                    task.setName(updatedTask.getName());
                    task.setCompleted(updatedTask.isCompleted());
                    // Additional fields can be updated here if needed
                    break;
                }
            }
            return taskListRepository.save(taskList);
        } else {
            throw new IllegalArgumentException("Task list with ID " + listId + " not found");
        }
    }


    public List<Task> viewTasksInList(Long listId) {
        Optional<TaskList> optionalTaskList = taskListRepository.findById(listId);
        if (optionalTaskList.isPresent()) {
            return optionalTaskList.get().getTasks();
        } else {
            throw new IllegalArgumentException("Task list with ID " + listId + " not found");
        }
    }

     */



}
