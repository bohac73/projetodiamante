package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByUser(User user);
}