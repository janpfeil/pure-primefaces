package org.rypox.sample;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author jan.pfeil
 *
 */
@Named
@ViewScoped
public class SettingsBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private int profileId;
  @Inject
  private UserSettings userSettings;

  public void init() {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null, new FacesMessage("SettingsBean init()" + this.userSettings.getProfileName()));
  }

  public void onSave() {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    facesContext.addMessage(null, new FacesMessage("SettingsBean save() Profilname:" + this.userSettings.getProfileName() + " id:"
        + this.profileId + " Farbe:" + this.userSettings.getColor()));
  }

  public int getProfileId() {
    return this.profileId;
  }

  public void setProfileId(final int profileId) {
    this.profileId = profileId;
  }
}
