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
  private Map<Long, Task> persistedTasks;
  private List<Task> tasks;
  private Long lastId;
  private List<ColumnModel> columns;

  @PostConstruct
  public void init() {
    this.tasks = new ArrayList<>();
    this.persistedTasks = new HashMap<>();
    this.lastId = 0L;
    this.addTask(new Task("todo gestern", "machte ich gestern"));
    this.addTask(new Task("todo heute", "mach ich morgen"));
    this.addTask(new Task("todo morgen", "mach ich übermorgen"));
    this.columns = new ArrayList<>();
    this.columns.add(new ColumnModel("ID", "id"));
    this.columns.add(new ColumnModel("Subject", "subject"));
    this.columns.add(new ColumnModel("Description", "description"));
    // key/value select options
    HashMap<String, String> options = new HashMap<>();
    options.put("centos", "CentOS");
    options.put("fedora", "Fedora");
    options.put("debian", "Debian");
    this.columns.add(new DropDownColumn("OS", "opsys", options));
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public String onAdd() {
    return "add_item.jsf?faces-redirect=true";
  }

  public String onEdit(Task task) {
    return "edit_item.jsf?faces-redirect=true&task_id=";
  }

  public String onDelete(Task task) {
    this.persistedTasks.remove(task);
    return null;
  }

  public Task getTask(Long id) {
    return this.persistedTasks.get(id);
  }

  public void addTask(Task task) {
    Long key = generate();
    task.setId(key);
    this.persistedTasks.put(key, task);
    this.tasks.add(task);
  }

  private Long generate() {
    return this.lastId++;
  }

  public void update(Task task) {
    this.persistedTasks.put(task.getId(), task);
    // TODO update task in tasks
  }

  public List<ColumnModel> getColumns() {
    return columns;
  }

}
