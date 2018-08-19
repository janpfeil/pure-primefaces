package org.rypox.sample;

import java.io.Serializable;

public class ColumnModel implements Serializable {
  private static final long serialVersionUID = 8375516800521022146L;
  private String header;
  private String propertyName;
  private String type;

  public final static String[] COLUMN_TYPES = { "TX", "CB", "DD" };

  public ColumnModel(final String header, final String propertyName) {
    super();
    this.header = header;
    this.propertyName = propertyName;
    this.type = "TX";
  }

  public String getHeader() {
    return this.header;
  }

  public void setHeader(final String header) {
    this.header = header;
  }

  public String getPropertyName() {
    return this.propertyName;
  }

  public void setPropertyName(final String propertyName) {
    this.propertyName = propertyName;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

}
