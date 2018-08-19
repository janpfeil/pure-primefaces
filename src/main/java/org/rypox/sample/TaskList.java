package org.rypox.sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.rypox.sample.domain.Task;

@Named
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
    addTask(new Task("todo gestern", "machte ich gestern"));
    addTask(new Task("todo heute", "mach ich morgen"));
    addTask(new Task("todo morgen", "mach ich übermorgen"));
    this.columns = new ArrayList<>();
    this.columns.add(new ColumnModel("ID", "id"));
    this.columns.add(new ColumnModel("Subject", "subject"));
    this.columns.add(new ColumnModel("Description", "description"));
    this.columns.add(new DropDownColumn("OS", "opsys", getOptions()));
  }

  /**
   * key/value select options
   *
   * @return
   */
  public HashMap<String, String> getOptions() {
    final HashMap<String, String> options = new HashMap<>();
    options.put("centos", "CentOS");
    options.put("fedora", "Fedora");
    options.put("debian", "Debian");
    options.put("suse", "OpenSuse");
    return options;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public String onAdd() {
    return "add_item.jsf?faces-redirect=true";
  }

  public String onEdit(final Task task) {
    return "edit_item.jsf?faces-redirect=true&task_id=";
  }

  public String onDelete(final Task task) {
    this.persistedTasks.remove(task);
    this.tasks.remove(task);
    return null;
  }

  public Task getTask(final Long id) {
    return this.persistedTasks.get(id);
  }

  public void addTask(final Task task) {
    final Long key = generate();
    task.setId(key);
    this.persistedTasks.put(key, task);
    this.tasks.add(task);
  }

  private Long generate() {
    return this.lastId++;
  }

  public void update(final Task task) {
    this.persistedTasks.put(task.getId(), task);
    // TODO update task in tasks
  }

  public List<ColumnModel> getColumns() {
    return this.columns;
  }

}
