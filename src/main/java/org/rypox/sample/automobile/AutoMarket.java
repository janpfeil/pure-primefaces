/**
 * 
 */
package org.rypox.sample.automobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author jan.pfeil
 *
 */
@ManagedBean
@SessionScoped
public class AutoMarket implements Serializable {
  private static final long serialVersionUID = -1129678836958520677L;
  private Map<Integer, Brand> brands;
  private Brand selectedBrand;
  private String selectedBrandId;
  private Object selectedObject;

  public AutoMarket() {
    super();
  }

  @PostConstruct
  public void init() {
    this.brands = new HashMap<>();
    this.brands.put(1, new Brand(1, "Mercedes-Benz", "Germany"));
    this.brands.put(2, new Brand(2, "BMW", "Germany"));
    this.brands.put(3, new Brand(3, "Audi", "Germany"));
    this.brands.put(4, new Brand(4, "Porsche", "Germany"));
    this.brands.put(5, new Brand(5, "VW", "Germany"));
    this.brands.put(6, new Brand(6, "Toyota", "Japan"));
    this.brands.put(7, new Brand(7, "Peugeot", "Frankreich"));
    this.brands.put(8, new Brand(8, "Crysler", "USA"));
    this.selectedBrand = this.brands.get(1);
  }

  public List<Brand> getBrandsList() {
    return new ArrayList<>(brands.values());
  }

  public Map<Integer, Brand> getBrands() {
    return brands;
  }

  public void setBrands(Map<Integer, Brand> brands) {
    this.brands = brands;
  }

  public Brand getSelectedBrand() {
    return selectedBrand;
  }

  public void setSelectedBrand(Brand selectedBrand) {
    this.selectedBrand = selectedBrand;
  }


  public String getSelectedBrandId() {
    return selectedBrandId;
  }

  public void setSelectedBrandId(String selectedBrandId) {
    this.selectedBrandId = selectedBrandId;
  }

  public Object getSelectedObject() {
    return selectedObject;
  }

  public void setSelectedObject(Object selectedObject) {
    this.selectedObject = selectedObject;
  }

  public Collection<Brand> completeBrand(String query) {
    Collection<Brand> brands = new ArrayList<>();
    if (!query.isEmpty()) {
      for (Brand brand : this.brands.values()) {
        boolean success = brand.getName().toLowerCase().contains(query.toLowerCase());
        if (success) {
          brands.add(brand);
        } else {
          success = brand.getCountry().toLowerCase().contains(query.toLowerCase());
          if (success) {
            brands.add(brand);
          }
        }
      }
    } else {
      brands.addAll(this.getBrandsList());
    }
    return brands;
  }
  
  public Collection<String[]> completeBrand2(String query) {
    ArrayList<String[]> brands = new ArrayList<>();
    if (!query.isEmpty()) {
      for (Brand brand : this.brands.values()) {
        boolean success = brand.getName().toLowerCase().contains(query.toLowerCase());
        if (success) {
          brands.add(new String[] {""+brand.getId(),brand.getName(),brand.getCountry()});
        } else {
          success = brand.getCountry().toLowerCase().contains(query.toLowerCase());
          if (success) {
            brands.add(new String[] {""+brand.getId(),brand.getName(),brand.getCountry()});
          }
        }
      }
    } else {
      //brands.addAll(this.getBrandsList());
    }
    return brands;
  }
}
