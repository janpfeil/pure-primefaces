/**
 *
 */
package org.rypox.sample.automobile;

import java.io.Serializable;

/**
 * @author jan.pfeil
 *
 */
public class Brand implements Serializable {
  private static final long serialVersionUID = -2669832810745216948L;
  private Integer id;
  private String name;
  private String country;

  public Brand(final int id, final String name, final String country) {
    super();
    this.id = id;
    this.name = name;
    this.country = country;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Brand [id=" + this.id + ", name=" + this.name + ", country=" + this.country + "]";
  }
}
