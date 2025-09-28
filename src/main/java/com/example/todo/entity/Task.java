package com.example.todo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tasks")
public class Task {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private boolean done = false;
  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public boolean isDone() { return done; }
  public void setDone(boolean done) { this.done = done; }
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
}