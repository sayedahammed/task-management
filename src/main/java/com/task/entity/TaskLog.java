package com.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "task_log")
@Getter
@Setter
public class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private String action;
    private String oldValue;
    private String newValue;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp changeTime;

}
