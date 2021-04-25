package am.itspace.taskmaster.repository;

import am.itspace.taskmaster.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByStatus(String status);
}
