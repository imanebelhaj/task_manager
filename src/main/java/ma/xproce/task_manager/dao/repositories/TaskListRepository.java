package ma.xproce.task_manager.dao.repositories;

import ma.xproce.task_manager.dao.entites.TaskList;
import ma.xproce.task_manager.dao.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByUser(User user);
}
