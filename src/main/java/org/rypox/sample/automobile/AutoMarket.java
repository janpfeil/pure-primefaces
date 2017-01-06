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
    return new ArrayList<>(this.brands.values());
  }

  public Map<Integer, Brand> getBrands() {
    return this.brands;
  }

  public void setBrands(final Map<Integer, Brand> brands) {
    this.brands = brands;
  }

  public Brand getSelectedBrand() {
    return this.selectedBrand;
  }

  public void setSelectedBrand(final Brand selectedBrand) {
    this.selectedBrand = selectedBrand;
  }

  public String getSelectedBrandId() {
    return this.selectedBrandId;
  }

  public void setSelectedBrandId(final String selectedBrandId) {
    this.selectedBrandId = selectedBrandId;
  }

  public Object getSelectedObject() {
    return this.selectedObject;
  }

  public void setSelectedObject(final Object selectedObject) {
    this.selectedObject = selectedObject;
  }

  public Collection<Brand> completeBrand(final String query) {
    final Collection<Brand> brands = new ArrayList<>();
    if (!query.isEmpty()) {
      for (final Brand brand : this.brands.values()) {
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
      brands.addAll(getBrandsList());
    }
    return brands;
  }

  public Collection<String[]> completeBrand2(final String query) {
    final ArrayList<String[]> brands = new ArrayList<>();
    if (!query.isEmpty()) {
      for (final Brand brand : this.brands.values()) {
        boolean success = brand.getName().toLowerCase().contains(query.toLowerCase());
        if (success) {
          brands.add(new String[] { "" + brand.getId(), brand.getName(), brand.getCountry() });
        } else {
          success = brand.getCountry().toLowerCase().contains(query.toLowerCase());
          if (success) {
            brands.add(new String[] { "" + brand.getId(), brand.getName(), brand.getCountry() });
          }
        }
      }
    } else {
      // brands.addAll(this.getBrandsList());
    }
    return brands;
  }
}
