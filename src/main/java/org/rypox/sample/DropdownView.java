package org.rypox.sample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */

@Named
@ViewScoped
public class DropdownView implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(DropdownView.class);

  private final Map<String, Map<String, String>> data = new HashMap<>();
  private boolean checked;
  private String country;
  private String city;
  private Map<String, String> countries;
  private Map<String, String> cities;

  @PostConstruct
  public void init() {
    LOG.debug("fill countries map");

    this.countries = new HashMap<>();
    this.countries.put("us", "USA");
    this.countries.put("de", "Germany");
    this.countries.put("bz", "Brazil");

    Map<String, String> map = new HashMap<>();
    map.put("New York", "New York");
    map.put("San Francisco", "San Francisco");
    map.put("Denver", "Denver");
    this.data.put("us", map);

    map = new HashMap<>();
    map.put("Berlin", "Berlin");
    map.put("Munich", "Munich");
    map.put("Frankfurt", "Frankfurt");
    this.data.put("de", map);

    map = new HashMap<>();
    map.put("Sao Paolo", "Sao Paolo");
    map.put("Rio de Janerio", "Rio de Janerio");
    map.put("Salvador", "Salvador");
    this.data.put("bz", map);
  }

  public Map<String, Map<String, String>> getData() {
    return this.data;
  }

  public boolean isChecked() {
    return this.checked;
  }

  public void setChecked(final boolean checked) {
    this.checked = checked;
  }

  public String getCountry() {
    LOG.debug("{}", this.country);
    return this.country;
  }

  public void setCountry(final String country) {
    LOG.debug("{}", country);
    this.country = country;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public Map<String, String> getCountries() {
    return this.countries;
  }

  public Map<String, String> getCities() {
    return this.cities;
  }

  public void onCountryChange() {
    LOG.debug("change country");

    if ((this.country != null) && !this.country.equals("")) {
      this.cities = this.data.get(this.country);
    } else {
      this.cities = new HashMap<>();
    }
  }

  public void onChecked() {
    LOG.debug("clicked checked");
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("checked:" + this.checked));
  }

  public void displayLocation() {
    FacesMessage msg;
    if ((this.country != null)) {
      msg = new FacesMessage("Selected country " + this.country);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    if (this.city != null) {
      msg = new FacesMessage("Selected city " + this.city);
      FacesContext.getCurrentInstance().addMessage(null, msg);
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Country is .City is not selected.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
}