/**
 *
 */
package org.rypox.sample.dyna;

/**
 * @author jan.pfeil
 *
 */
public class AutoCompleteControl {
  private String name;
  private Object value;
  private boolean required;

  public AutoCompleteControl(final String name, final Object value, final boolean required) {
    super();
    this.name = name;
    this.value = value;
    this.required = required;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Object getValue() {
    return this.value;
  }

  public void setValue(final Object value) {
    System.out.println("value set:" + value);
    this.value = value;
  }

  public boolean isRequired() {
    return this.required;
  }

  public void setRequired(final boolean required) {
    this.required = required;
  }

}
