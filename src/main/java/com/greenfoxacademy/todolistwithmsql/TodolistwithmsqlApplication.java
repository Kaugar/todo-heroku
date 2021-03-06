package com.greenfoxacademy.todolistwithmsql;

import com.greenfoxacademy.todolistwithmsql.models.Assignee;
import com.greenfoxacademy.todolistwithmsql.models.Todo;
import com.greenfoxacademy.todolistwithmsql.repository.AssigneeRepo;
import com.greenfoxacademy.todolistwithmsql.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistwithmsqlApplication implements CommandLineRunner {
  @Autowired
  TodoInterface todoInterface;
  @Autowired
  AssigneeRepo assigneeRepo;

  public static void main(String[] args) {
    SpringApplication.run(TodolistwithmsqlApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    assigneeRepo.save(new Assignee("Hello", "bello"));
  }
}
