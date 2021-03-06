package com.greenfoxacademy.todolistwithmsql.repository;


import com.greenfoxacademy.todolistwithmsql.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoInterface extends CrudRepository<Todo,Long> {

  List<Todo> findByDone (boolean done);
  List<Todo> findAllByOrderByIdAsc();


  List<Todo> findByTitle(String text);

  List<Todo> findByTitleStartsWith (String text);

  List<Todo> findByTitleIgnoreCaseContaining (String text);

}
