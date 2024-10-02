package com.task.service;

import com.task.dto.TaskRequestDTO;
import com.task.entity.Category;
import com.task.entity.Task;
import com.task.entity.User;
import com.task.enums.TaskPriority;
import com.task.enums.TaskStatus;
import com.task.exception.ResourceNotFoundException;
import com.task.repository.CategoryRepository;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(TaskRequestDTO taskRequestDTO) {
        // Convert DTO to Entity
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDueDate(taskRequestDTO.getDueDate());
        task.setStatus(TaskStatus.valueOf(taskRequestDTO.getStatus()));
        task.setPriority(TaskPriority.valueOf(taskRequestDTO.getPriority()));

        // Fetching the assigned user, category, and creator by their IDs.
        User assignedTo = userRepository.findById(taskRequestDTO.getAssignedToId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + taskRequestDTO.getAssignedToId()));

        Category category = categoryRepository.findById(taskRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + taskRequestDTO.getCategoryId()));

        User createdBy = userRepository.findById(taskRequestDTO.getCreatedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + taskRequestDTO.getCreatedById()));

        // Setting the entities into the task object.
        task.setAssignedTo(assignedTo);
        task.setCategory(category);
        task.setCreatedBy(createdBy);

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
