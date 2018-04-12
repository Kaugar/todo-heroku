package com.greenfoxacademy.todolistwithmsql.controllers;


import com.greenfoxacademy.todolistwithmsql.models.Todo;
import com.greenfoxacademy.todolistwithmsql.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  TodoInterface todoInterface;

  @GetMapping(value = "/todo")
  public String todo(Model model, @RequestParam (name = "isActive", required = false) Boolean isDone ){
    if(isDone == null){
      model.addAttribute("todos", todoInterface.findAllByOrderByIdAsc());
    } else {
      model.addAttribute("todos", todoInterface.findByDone(!isDone));
    }
    return "todoslist";
  }
  @PostMapping (value = "/todo")
  public String search (Model model, @ModelAttribute(name = "text") String text){
    model.addAttribute("todos", todoInterface.findByTitleIgnoreCaseContaining(text));
    return "todoslist";
  }

  @GetMapping (value = "/addnewtask")
  public String addTask (){
  return "addtask";
  }
  @PostMapping (value = "/addnewtask")
  public String addNewTask (Model model, @ModelAttribute(name = "titleOfTask") String titleOfTask){
    model.addAttribute("todos", todoInterface.save(new Todo(titleOfTask)));
    return "redirect:/todo";
  }
  @GetMapping (value = "/{id}/delete")
  public String deleteTask (@PathVariable(name = "id") Long id){
    todoInterface.deleteById(id);
    return "redirect:/todo";
  }

  @GetMapping (value = "/{id}/edittask")
  public String editTaskPage (Model model, @PathVariable(name = "id") Long id){
    model.addAttribute("todo", todoInterface.findById(id).get());
    return "edittask";
  }

  @PostMapping (value = "/edittask/{id}")
  public String editTask (@ModelAttribute(name = "newTitle") String newTitleOfTask, boolean urgent, boolean done,  @PathVariable(name = "id") Long id){
    todoInterface.findById(id).get().setTitle(newTitleOfTask);
    todoInterface.findById(id).get().setDone(done);
    todoInterface.findById(id).get().setUrgent(urgent);
    todoInterface.save(todoInterface.findById(id).get());
    return "redirect:/todo";
  }

  @GetMapping(value ={ "/", "/list"})
  @ResponseBody
  public String list (Model model){
    return "This is my first todo";
  }
}
