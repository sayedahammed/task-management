package com.task.repository;

import com.task.entity.Task;
import com.task.entity.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskLogRepository extends JpaRepository<TaskLog, Long> {
    List<TaskLog> findByTask(Task task);
}
