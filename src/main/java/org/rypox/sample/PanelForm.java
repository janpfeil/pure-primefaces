/**
 *
 */
package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author jan.pfeil
 *
 */
@Named
@SessionScoped
public class PanelForm implements Serializable {
  private static final long serialVersionUID = 1L;

  @Inject
  private MessageBean bean;

  private boolean panelCollapsed;

  @PostConstruct
  public void init() {
    System.out.println("MessageBean.init()");
    this.panelCollapsed = false;
  }

  @PreDestroy
  public void end() {
    System.out.println("MessageBean.end()");
  }

  public boolean isPanelCollapsed() {
    return this.panelCollapsed;
  }

  public void setPanelCollapsed(final boolean panelCollapsed) {
    this.panelCollapsed = panelCollapsed;
  }

  public void submit(final ActionEvent actionEvent) {
    this.bean.setMessage(this.bean.getMessage() + (System.currentTimeMillis() / 1000));
  }

  public MessageBean getBean() {
    return this.bean;
  }

  public void setBean(final MessageBean bean) {
    this.bean = bean;
  }
}
