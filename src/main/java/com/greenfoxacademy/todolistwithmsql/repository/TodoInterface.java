package com.greenfoxacademy.todolistwithmsql.repository;


import com.greenfoxacademy.todolistwithmsql.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoInterface extends CrudRepository<Todo,Long> {

  List<Todo> findByDone (boolean done);
}
