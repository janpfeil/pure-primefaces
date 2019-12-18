package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class MessageBean implements Serializable {
  private static final Logger LOG = LoggerFactory.getLogger(MessageBean.class);

  private String label;
  private String message;
  private boolean toggle;

  public boolean isToggle() {
    LOG.debug("toggle:{}", this.toggle);
    return this.toggle;
  }

  public void setToggle(final boolean toggle) {
    LOG.debug("toggle:{}", this.toggle);
    this.toggle = toggle;
  }

  public String getMessage() {
    LOG.debug("message:{}", this.message);
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

  public String onMenu() {
    System.out.println("onMenu:");
    return "/menu/menu_page_1";
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
