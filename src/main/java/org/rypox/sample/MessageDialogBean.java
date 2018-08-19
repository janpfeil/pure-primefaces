/**
 *
 */
package org.rypox.sample;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

/**
 * @author jan.pfeil
 *
 */
@Named
@SessionScoped
public class MessageDialogBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private final String messageDialogId = "messageDialogId";
  private FacesMessage message = new FacesMessage();
  private String header = "test";

  public void showMessage(final String pHeader, final FacesMessage pMessage) {
    if (pMessage != null) {
      setHeader(pHeader);
      this.message = pMessage;
      show();
    }
  }

  public void showWarn(final String pHeader, final String pSummary, final String pDetail) {
    setHeader(pHeader);
    this.message = new FacesMessage(FacesMessage.SEVERITY_WARN, pSummary, pDetail);
    show();
  }

  public void showInfo(final String pHeader, final String pSummary, final String pDetail) {
    setHeader(pHeader);
    this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, pSummary, pDetail);
    show();
  }

  public void showError(final String pHeader, final String pSummary, final String pDetail) {
    setHeader(pHeader);
    this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR, pSummary, pDetail);
    show();
  }

  public void updateDialog() {
    final RequestContext context = RequestContext.getCurrentInstance();
    context.update("messageForm:messageDialogHeader");
  }

  private void show() {
    updateDialog();
    final RequestContext context = RequestContext.getCurrentInstance();
    context.execute("PF('varDialog').show();");
    FacesContext.getCurrentInstance().addMessage(this.messageDialogId, this.message);
  }

  public String getMessageDialogId() {
    return this.messageDialogId;
  }

  public void setHeader(final String pHeader) {
    this.header = pHeader;
  }

  public String getHeader() {
    return this.header;
  }

  public FacesMessage getLastMessage() {
    return this.message;
  }

}