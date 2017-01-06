package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean implements Serializable {

  private String label;
  private String message;

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public void onSave() {
    System.out.println("onSave:" + this.message);
    this.label = this.message;
    this.message = this.message + " changed";
  }

  @PostConstruct
  public void init() {
    System.out.println("MessageBean.init()");
  }

  @PreDestroy
  public void end() {
    System.out.println("MessageBean.end()");
  }

  public void onNpe() {
    final Object n = null;
    n.getClass();
  }
}
