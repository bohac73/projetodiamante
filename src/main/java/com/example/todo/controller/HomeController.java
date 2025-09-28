package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.entity.User;
import java.util.List;

@Controller
public class HomeController {
  private final TaskRepository taskRepo;
  private final UserRepository userRepo;

  public HomeController(TaskRepository taskRepo, UserRepository userRepo) {
    this.taskRepo = taskRepo;
    this.userRepo = userRepo;
  }

  @GetMapping("/")
  public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
    if (principal == null) {
      model.addAttribute("tasks", List.of());
      return "index";
    }
    String email = principal.getAttribute("email");
    User user = userRepo.findByEmail(email).orElse(null);
    model.addAttribute("tasks", user == null ? List.of() : taskRepo.findByUser(user));
    model.addAttribute("userName", principal.getAttribute("login"));
    return "index";
  }
}