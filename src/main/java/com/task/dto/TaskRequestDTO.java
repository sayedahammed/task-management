package com.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;
    private String priority;
    private Long assignedToId;
    private Long categoryId;
    private Long createdById;
}
