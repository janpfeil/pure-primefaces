/**
 *
 */
package org.rypox.sample.dyna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import org.rypox.sample.automobile.Brand;
import org.rypox.sample.automobile.OptionDsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@ManagedBean
@SessionScoped
public class DynaFormAction implements Serializable {
  private static final long serialVersionUID = -93840956385696767L;
  private static final Logger LOG = LoggerFactory.getLogger(DynaFormAction.class);
  private Map<Integer, Brand> brands;
  private Map<Integer, OptionDsp> countries;
  private Brand selectedBrand;

  private String selectedBrandId;
  private Object selectedObject;
  private DynaFormModel model;

  public DynaFormAction() {
    super();
  }

  @PostConstruct
  private void init() {
    LOG.debug("init");
    buildBrands();
    buildCountries();
    this.model = buildDynaFormModel();
  }

  /**
   * baue DynaFormModel aus konfigurierten View-Komponenten
   *
   * @param componentMap
   * @return
   */
  private DynaFormModel buildDynaFormModel() {
    LOG.debug("DynaFormAction.buildDynaFormModel()");
    final DynaFormModel viewModel = new DynaFormModel();
    // das Primefaces view model
    {
      // AutoComplete Brand
      final DynaFormRow row = viewModel.createRegularRow();
      final DynaFormLabel formLabel = row.addLabel("Label", 1, 1);
      final AutoCompleteControl comp = new AutoCompleteControl("Marke", new Brand(-1, "leer", ""), true);
      final DynaFormControl control = row.addControl(comp, "autoComplete", 1, 1);
      formLabel.setForControl(control);
    }
    // AutoComplete Country with value as OptionDsp type
    {
      final DynaFormRow row = viewModel.createRegularRow();
      final DynaFormLabel formLabel = row.addLabel("CountryLabel", 1, 1);
      final AutoCompleteControl comp = new AutoCompleteControl("Country", new OptionDsp("", "leer"), true);
      final DynaFormControl control = row.addControl(comp, "autoCompleteCountry", 1, 1);
      formLabel.setForControl(control);
    }
    // AutoComplete Country with value as String[] type
    {
      final DynaFormRow row = viewModel.createRegularRow();
      final DynaFormLabel formLabel = row.addLabel("CountryLabel2", 1, 1);
      final AutoCompleteControl comp = new AutoCompleteControl("Country2", new String[] { "", "leer" }, true);
      final DynaFormControl control = row.addControl(comp, "autoCompleteCountry2", 1, 1);
      formLabel.setForControl(control);
    }
    // AutoComplete Country with value as String type
    // Funktioniert nicht, da value in AutoCompleteControl vom Typ String ist und Converter.getAsObject nicht aufgerufen wird.
    {
      final DynaFormRow row = viewModel.createRegularRow();
      final DynaFormLabel formLabel = row.addLabel("CountryLabel3", 1, 1);
      final AutoCompleteControl comp = new AutoCompleteControl("Country3", new String[] { "", "leer" }, true);
      final DynaFormControl control = row.addControl(comp, "autoCompleteCountry3", 1, 1);
      formLabel.setForControl(control);
    }
    return viewModel;
  }

  public DynaFormModel getModel() {
    return this.model;
  }

  public void setModel(final DynaFormModel model) {
    this.model = model;
  }

  public void buildBrands() {
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

  public void buildCountries() {
    this.countries = new HashMap<>();
    this.countries.put(1, new OptionDsp("1", "Germany"));
    this.countries.put(2, new OptionDsp("2", "Japan"));
    this.countries.put(3, new OptionDsp("3", "Frankreich"));
    this.countries.put(4, new OptionDsp("4", "USA"));
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

  public Collection<OptionDsp> completeCountry(final String query) {
    final Collection<OptionDsp> options = new ArrayList<>();
    if (!query.isEmpty()) {
      for (final OptionDsp option : this.countries.values()) {
        boolean success = option.getLabel().toLowerCase().contains(query.toLowerCase());
        if (success) {
          options.add(option);
        } else {
          success = option.getId().toLowerCase().contains(query.toLowerCase());
          if (success) {
            options.add(option);
          }
        }
      }
    } else {
      options.addAll(this.countries.values());
    }
    return options;
  }

  public Collection<String[]> completeCountry2(final String query) {
    final ArrayList<String[]> options = new ArrayList<>();
    if (!query.isEmpty()) {
      for (final OptionDsp option : this.countries.values()) {
        final boolean success = option.getLabel().toLowerCase().contains(query.toLowerCase());
        if (success) {
          options.add(new String[] { "" + option.getId(), option.getLabel() });
        }
      }
    } else {
      // options.addAll(this.countries.values());
    }
    return options;
  }
}
