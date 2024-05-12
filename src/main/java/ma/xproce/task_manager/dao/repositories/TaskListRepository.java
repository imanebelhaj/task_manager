package ma.xproce.task_manager.dao.repositories;

import ma.xproce.task_manager.dao.entites.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
