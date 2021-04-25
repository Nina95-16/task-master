package am.itspace.taskmaster.repository;

import am.itspace.taskmaster.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Optional<Project> findByUserId(int id);
}
