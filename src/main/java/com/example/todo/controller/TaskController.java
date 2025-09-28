package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.example.todo.repository.*;
import com.example.todo.entity.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
  private final TaskRepository taskRepo;
  private final UserRepository userRepo;

  public TaskController(TaskRepository taskRepo, UserRepository userRepo) {
    this.taskRepo = taskRepo;
    this.userRepo = userRepo;
  }

  @PostMapping
  public String add(@RequestParam String title, @AuthenticationPrincipal OAuth2User principal) {
    String email = principal.getAttribute("email");
    User user = userRepo.findByEmail(email).orElseThrow();
    Task t = new Task();
    t.setTitle(title);
    t.setUser(user);
    taskRepo.save(t);
    return "redirect:/";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    taskRepo.deleteById(id);
    return "redirect:/";
  }
}