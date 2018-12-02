package org.rypox.sample.shop;

import java.io.Serializable;

/**
 * @author jan.pfeil
 *
 */
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;

  public Product(final String productId, final String name) {
    super();
    this.productId = productId;
    this.name = name;
  }

  private final String name;
  private final String productId;

  public String getName() {
    return this.name;
  }

  public String getProductId() {
    return this.productId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.productId == null) ? 0 : this.productId.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Product other = (Product) obj;
    if (this.productId == null) {
      if (other.productId != null) {
        return false;
      }
    } else if (!this.productId.equals(other.productId)) {
      return false;
    }
    return true;
  }
}
