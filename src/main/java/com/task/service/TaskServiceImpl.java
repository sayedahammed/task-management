package com.task.service;

import com.task.entity.Task;
import com.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setDueDate(taskDetails.getDueDate());
                    task.setStatus(taskDetails.getStatus());
                    return taskRepository.save(task);
                });
    }

    @Override
    public Optional<Void> deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return null;
                });
    }
}
