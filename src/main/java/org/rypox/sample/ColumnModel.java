package org.rypox.sample;

import java.io.Serializable;

public class ColumnModel implements Serializable {
  private static final long serialVersionUID = 8375516800521022146L;
  private String header;
  private String propertyName;
  private String type;

  public final static String[] COLUMN_TYPES = { "TX", "CB", "DD" };

  public ColumnModel(String header, String propertyName) {
    super();
    this.header = header;
    this.propertyName = propertyName;
    this.type = "TX";
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
