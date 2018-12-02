/**
 *
 */
package org.rypox.sample.shop;

import java.io.Serializable;

/**
 * @author jan.pfeil
 *
 */
public class CartItem implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Product product;
  private final Amount amount;
  // TODO private Price price;

  /**
   * @param product
   */
  public CartItem(final Product product) {
    this.product = product;
    this.amount = new Amount("St");
  }

  /**
   * @param count
   */
  public void add(final int count) {
    this.amount.setCount(this.amount.getCount() + count);
  }

  public Product getProduct() {
    return this.product;
  }

  public Amount getAmount() {
    return this.amount;
  }
}
