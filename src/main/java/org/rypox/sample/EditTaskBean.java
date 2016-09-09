package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.rypox.sample.domain.Task;

@ManagedBean
@RequestScoped
public class EditTaskBean implements Serializable {
  @ManagedProperty(value = "#{taskList}")
  private TaskList taskList;

  private Task task;

  @PostConstruct
  public void init() {
    this.task = new Task();
  }

  public String onSave() {
    if (null == this.task.getId()) {
      this.taskList.addTask(this.task);
    } else {
      this.taskList.update(this.task);
    }
    return "table.jsf?faces-redirect=true";
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public TaskList getTaskList() {
    return taskList;
  }

  public void setTaskList(TaskList taskList) {
    this.taskList = taskList;
  }
}
