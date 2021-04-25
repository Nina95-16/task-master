package am.itspace.taskmaster.service;

import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    void saveProject(Project project);
    List<Project> projects();

    Project getById(int id) throws ResourceNotFoundException;

    void delete(int id);
    Project findByUserId(int id) throws ResourceNotFoundException;
}
