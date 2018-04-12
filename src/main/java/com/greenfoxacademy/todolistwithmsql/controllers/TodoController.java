package com.greenfoxacademy.todolistwithmsql.controllers;


import com.greenfoxacademy.todolistwithmsql.models.Assignee;
import com.greenfoxacademy.todolistwithmsql.models.Todo;
import com.greenfoxacademy.todolistwithmsql.repository.AssigneeRepo;
import com.greenfoxacademy.todolistwithmsql.repository.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  TodoInterface todoInterface;
  @Autowired
  AssigneeRepo assigneeRepo;

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

  @GetMapping (value = "/assignees")
  public String listAssignees (Model model){
      model.addAttribute("assignees", assigneeRepo.findAll());
    return "assigneeslist";
  }

  @PostMapping (value = "/assignees")
  public String addAssignees (Model model, @ModelAttribute(name = "name") String name, @ModelAttribute(name = "email") String email){
    model.addAttribute("assignees", assigneeRepo.save(new Assignee(name, email)));
    return "redirect:/assignees";
  }

  @GetMapping("/editassigne/{id}")
  public String edit(@PathVariable(name = "id") Long id, Model model) {
    model.addAttribute("assignee", assigneeRepo.findById(id).get());
    return "editassignee";
  }

  @PostMapping("{id}/editassigne/")
  public String update(@ModelAttribute(name = "name") String name, @ModelAttribute(name = "email") String email, @PathVariable(name = "id") Long id) {
    assigneeRepo.findById(id).get().setName(name);
    assigneeRepo.findById(id).get().setEmail(email);
    assigneeRepo.save(assigneeRepo.findById(id).get());
    return "redirect:/assignees";
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
