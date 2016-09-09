package org.rypox.sample;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.rypox.sample.domain.Task;

@ManagedBean
@RequestScoped // Can also be @ApplicationScoped if the Converter is entirely stateless.
public class ItemConverter implements Converter {
  @ManagedProperty(value = "#{taskList}")
  private TaskList taskList;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }

    try {
      return taskList.getTask(Long.valueOf(value));
    } catch (NumberFormatException e) {
      throw new ConverterException(new FacesMessage(value + " is not a valid User ID", e.getMessage()));
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
    if (modelValue == null) {
      return "";
    }

    if (modelValue instanceof Task) {
      return String.valueOf(((Task) modelValue).getId());
    } else {
      throw new ConverterException(new FacesMessage(modelValue + " is not a valid Task entity"));
    }
  }

  public TaskList getTaskList() {
    return taskList;
  }

  public void setTaskList(TaskList taskList) {
    this.taskList = taskList;
  }

}
