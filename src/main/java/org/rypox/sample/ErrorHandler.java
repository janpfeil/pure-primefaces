/**
 *
 */
package org.rypox.sample;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author jan.pfeil
 *
 */
@Named
@RequestScoped
public class ErrorHandler {

  public String getStatusCode() {
    final String val = String
        .valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.status_code"));
    return val;
  }

  public String getMessage() {
    final String val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
        .get("javax.servlet.error.message");
    return val;
  }

  public String getExceptionType() {
    final String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.exception")
        .toString();
    return val;
  }

  public String getException() {
    final String val = ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
        .get("javax.servlet.error.exception")).toString();
    return val;
  }

  public String getRequestURI() {
    return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.request_uri");
  }

  public String getServletName() {
    return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.servlet_name");
  }

}