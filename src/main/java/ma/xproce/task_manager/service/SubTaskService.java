package ma.xproce.task_manager.service;



import ma.xproce.task_manager.dao.entites.SubTask;
import ma.xproce.task_manager.dao.repositories.SubTaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }
    public SubTask createSubTask(SubTask subTask) {
        subTask.setCreationDate(new Date());
        subTask.setLastUpdateDate(new Date());
        return subTaskRepository.save(subTask);
    }

    public SubTask getSubTaskById(Long subTaskId) {
        return subTaskRepository.findById(subTaskId).orElse(null);
    }

    public List<SubTask> getAllSubTasks() {
        return subTaskRepository.findAll();
    }

    public void deleteSubTask(Long subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }

    public SubTask updateSubTask(Long subTaskId, SubTask updatedSubTask) {
            SubTask existingSubTask = subTaskRepository.findById(subTaskId).orElse(null);
            if (existingSubTask != null) {
                existingSubTask.setName(updatedSubTask.getName());
                existingSubTask.setDescription(updatedSubTask.getDescription());
                existingSubTask.setPriorityLevel(updatedSubTask.getPriorityLevel());
                existingSubTask.setCompleted(updatedSubTask.isCompleted());
                existingSubTask.setCompletionDate(updatedSubTask.getCompletionDate());
                existingSubTask.setLastUpdateDate(new Date());
                return subTaskRepository.save(existingSubTask);
            }
            return null; // SubTask not found
    }
}


