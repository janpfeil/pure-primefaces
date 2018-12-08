/**
 *
 */
package org.rypox.sample;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author jan.pfeil
 *
 */

@Named
@RequestScoped
public class DropdownView implements Serializable {
  private static final long serialVersionUID = 1L;
  private final Map<String, Map<String, String>> data = new HashMap<>();
  private String country;
  private String city;
  private Map<String, String> countries;
  private Map<String, String> cities;

  @PostConstruct
  public void init() {
    this.countries = new HashMap<>();
    this.countries.put("USA", "USA");
    this.countries.put("Germany", "Germany");
    this.countries.put("Brazil", "Brazil");

    Map<String, String> map = new HashMap<>();
    map.put("New York", "New York");
    map.put("San Francisco", "San Francisco");
    map.put("Denver", "Denver");
    this.data.put("USA", map);

    map = new HashMap<>();
    map.put("Berlin", "Berlin");
    map.put("Munich", "Munich");
    map.put("Frankfurt", "Frankfurt");
    this.data.put("Germany", map);

    map = new HashMap<>();
    map.put("Sao Paolo", "Sao Paolo");
    map.put("Rio de Janerio", "Rio de Janerio");
    map.put("Salvador", "Salvador");
    this.data.put("Brazil", map);
  }

  public Map<String, Map<String, String>> getData() {
    return this.data;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(final String country) {
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
    if ((this.country != null) && !this.country.equals("")) {
      this.cities = this.data.get(this.country);
    } else {
      this.cities = new HashMap<>();
    }
  }

  public void displayLocation() {
    FacesMessage msg;
    if ((this.city != null) && (this.country != null)) {
      msg = new FacesMessage("Selected", this.city + " of " + this.country);
    } else {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
    }

    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}