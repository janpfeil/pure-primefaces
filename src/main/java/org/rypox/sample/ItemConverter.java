package org.rypox.sample;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import org.rypox.sample.domain.Task;

@Named
@RequestScoped // Can also be @ApplicationScoped if the Converter is entirely stateless.
public class ItemConverter implements Converter {
  @Inject
  private TaskList taskList;

  @Override
  public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
    if ((value == null) || value.isEmpty()) {
      return null;
    }

    try {
      return this.taskList.getTask(Long.valueOf(value));
    } catch (final NumberFormatException e) {
      throw new ConverterException(new FacesMessage(value + " is not a valid User ID", e.getMessage()));
    }
  }

  @Override
  public String getAsString(final FacesContext context, final UIComponent component, final Object modelValue) {
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
    return this.taskList;
  }

  public void setTaskList(final TaskList taskList) {
    this.taskList = taskList;
  }

}
