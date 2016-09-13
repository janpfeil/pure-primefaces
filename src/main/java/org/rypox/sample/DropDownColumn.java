/**
 * 
 */
package org.rypox.sample;

import java.util.HashMap;
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
  public DropDownColumn(String header, String propertyName) {
    super(header, propertyName);
    super.setType("DD");
  }

  /**
   * @param string
   * @param string2
   * @param options2
   */
  public DropDownColumn(String header, String propertyName, HashMap<String, String> options) {
    this(header, propertyName);
    this.options = options;
  }

  public Map<String, String> getOptions() {
    return options;
  }

  public void setOptions(Map<String, String> options) {
    this.options = options;
  }

}
