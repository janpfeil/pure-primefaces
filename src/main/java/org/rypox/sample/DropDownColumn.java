/**
 *
 */
package org.rypox.sample;

import java.util.Map;

/**
 * @author jan.pfeil
 *
 */
public class DropDownColumn extends ColumnModel {

  private static final long serialVersionUID = 515045195643874409L;
  private Map<String, String> options;

  /**
   * @param header
   * @param propertyName
   */
  public DropDownColumn(final String header, final String propertyName, final boolean readonly) {
    super(header, propertyName, readonly);
    super.setType("DD");
  }

  /**
   * @param string
   * @param string2
   * @param options2
   */
  public DropDownColumn(final String header, final String propertyName, final boolean readonly, final Map<String, String> options) {
    this(header, propertyName, readonly);
    this.options = options;
  }

  public Map<String, String> getOptions() {
    return this.options;
  }

  public void setOptions(final Map<String, String> options) {
    this.options = options;
  }

}
