package com.task;

import com.task.entity.Task;
import com.task.repository.TaskRepository;
import com.task.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void createTask_ShouldSaveTask() {
        Task task = new Task();
        task.setTitle("New Task");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        assertEquals("New Task", createdTask.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void getTaskById_ShouldReturnTask() {
        Task task = new Task();
        task.setTaskId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById(1L);
        assertTrue(foundTask.isPresent());
        assertEquals(1L, foundTask.get().getTaskId());
    }

    @Test
    void deleteTask_ShouldCallRepositoryDelete() {
        Task task = new Task();
        task.setTaskId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
