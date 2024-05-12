package ma.xproce.task_manager.dao.repositories;

import ma.xproce.task_manager.dao.entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
   // public Task findByTask(String name);
//    public List<Task> findByCompletedTrue();
//    public List<Task> findByCompletedFalse();

}
