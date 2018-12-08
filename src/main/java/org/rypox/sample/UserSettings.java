package org.rypox.sample;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@Named
@SessionScoped
public class UserSettings implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(UserSettings.class);
  private String profileName;
  private String color;

  /**
   *
   */
  public UserSettings() {
    // avoid business logic in JEE beans, use @PostConstruct
  }

  @PostConstruct
  private void init() {
    LOG.debug("new instance of UserSettings {}", this);
    this.profileName = "default";
  }

  public String getProfileName() {
    return this.profileName;
  }

  public void setProfileName(final String profileName) {
    this.profileName = profileName;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(final String color) {
    this.color = color;
  }

}
