package am.itspace.taskmaster.service.impl;

import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.Task;
import am.itspace.taskmaster.repository.TaskRepository;
import am.itspace.taskmaster.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public void saveTask(Task task){
        log.info("task {} ", task);
        taskRepository.save(task);
    }

    @Override
    public List<Task> allTasks() {
        List<Task> all = taskRepository.findAll();
        return all;
    }

    @Override
    public Task getById(int id) throws ResourceNotFoundException {
        Task byId = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with + " + id + " does not exists"));
        return byId;
    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> byStatus(String status) {
        List<Task> allByStatus = taskRepository.findAllByStatus(status);
        return allByStatus;
    }
}
