/**
 *
 */
package org.rypox.sample.shop;

import java.io.Serializable;

/**
 * @author jan.pfeil
 *
 */
public class Amount implements Serializable {
  private static final long serialVersionUID = 1L;
  private int count;
  private String unit;

  public Amount(final String unit) {
    super();
    this.unit = unit;
    this.count = 0;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(final int count) {
    this.count = count;
  }

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(final String unit) {
    this.unit = unit;
  }

}
