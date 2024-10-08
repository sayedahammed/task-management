package com.task.service;

import com.task.dto.TaskRequestDTO;
import com.task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task createTask(TaskRequestDTO taskRequestDTO);
    Optional<Task> updateTask(Long id, Task taskDetails);
    Optional<Void> deleteTask(Long id);
}
