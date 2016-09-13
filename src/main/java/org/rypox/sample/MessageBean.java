package org.rypox.sample;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MessageBean {

  private String label;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void onSave() {
    System.out.println("onSave:" + this.message);
    this.label = message;
    this.message = message + " changed";
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
    Object n = null;
    n.getClass();
  }
}
