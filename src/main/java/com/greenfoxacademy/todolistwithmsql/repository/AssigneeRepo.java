package com.greenfoxacademy.todolistwithmsql.repository;

import com.greenfoxacademy.todolistwithmsql.models.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepo extends CrudRepository<Assignee, Long> {

}
