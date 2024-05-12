package ma.xproce.task_manager.dao.repositories;

import ma.xproce.task_manager.dao.entites.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
