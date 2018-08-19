package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.rypox.sample.domain.Task;

@Named
@RequestScoped
public class EditTaskBean implements Serializable {
  private static final long serialVersionUID = -6388076025560020146L;

  @Inject
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
    // TODO navigate back to caller page
    return "table_dyncolumn.jsf?faces-redirect=true";
  }

  public Task getTask() {
    return this.task;
  }

  public void setTask(final Task task) {
    this.task = task;
  }

  public TaskList getTaskList() {
    return this.taskList;
  }

  public void setTaskList(final TaskList taskList) {
    this.taskList = taskList;
  }
}
