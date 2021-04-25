package am.itspace.taskmaster.service.impl;

import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.Project;
import am.itspace.taskmaster.repository.ProjectRepository;
import am.itspace.taskmaster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> projects() {
        List<Project> all = projectRepository.findAll();
        return all;
    }

    @Override
    public Project getById(int id) throws ResourceNotFoundException {
        return  projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project by " + id + " does not exists"));
    }

    @Override
    public void delete(int id) {
     projectRepository.deleteById(id);
    }

    @Override
    public Project findByUserId(int id) throws ResourceNotFoundException {
        Project project = projectRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("Project by " + id + " user id does not exist"));
        return project;
    }
}
