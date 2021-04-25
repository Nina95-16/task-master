package am.itspace.taskmaster.service;

import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    void saveTask(Task task);

    List<Task> allTasks();

    Task getById(int id) throws ResourceNotFoundException;

    void delete(int id);
    List<Task> byStatus(String status);
}
