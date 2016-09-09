package org.rypox.sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.NamedEvent;

import org.rypox.sample.domain.Task;

@ManagedBean
@SessionScoped
public class TaskList implements Serializable {
  private static final long serialVersionUID = 3872411784218998192L;
  private Map<Long, Task> tasks;
  private Long lastId;

  @PostConstruct
  public void init() {
    this.tasks = new HashMap<>();
    this.lastId = 0L;
    this.addTask(new Task("sample todo", "mach ich morgen"));
  }

  public Collection<Task> getTasks() {
    return tasks.values();
  }

  public String onAdd() {
    return "add_item.jsf?faces-redirect=true";
  }

  public String onEdit(Task task) {
    return "edit_item.jsf?faces-redirect=true&task_id=";
  }

  public String onDelete(Task task) {
    this.tasks.remove(task);
    return null;
  }

  public Task getTask(Long id) {
    return this.tasks.get(id);
  }

  public void addTask(Task task) {
    Long key = generate();
    task.setId(key);
    this.tasks.put(key, task);
  }

  private Long generate() {
    return this.lastId++;
  }

  public void update(Task task) {
    this.tasks.put(task.getId(), task);
  }

}
