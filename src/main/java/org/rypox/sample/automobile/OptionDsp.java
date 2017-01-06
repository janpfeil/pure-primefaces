/**
 *
 */
package org.rypox.sample.automobile;

import java.io.Serializable;

/**
 * @author jan.pfeil
 *
 */
public class OptionDsp implements Serializable {
  private static final long serialVersionUID = -6640618728291470271L;
  private String id, label;

  public OptionDsp(final String id, final String label) {
    super();
    this.id = id;
    this.label = label;
  }

  public String getId() {
    return this.id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "OptionDsp [id=" + this.id + ", label=" + this.label + "]";
  }

}
