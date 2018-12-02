package org.rypox.sample.shop;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@Named
@SessionScoped
public class Cart implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(Cart.class);

  private Map<Product, CartItem> contents;

  @PostConstruct
  private void init() {
    this.contents = new LinkedHashMap<>();

    final Product product = new Product("300", "sample");
    final CartItem cartItem = new CartItem(product);
    cartItem.add(1);
    this.contents.put(product, cartItem);
  }

  public void add(final Product product) {
    // CardItem cardItem = this.contents.get(product);
    // if (null == cardItem) {
    // cardItem = new CardItem(product);
    // this.contents.put(product, cardItem);
    // }

    final CartItem cardItem = this.contents.computeIfAbsent(product, CartItem::new);

    cardItem.add(1);
  }

  public Collection<CartItem> getCartItems() {
    LOG.debug("cart item count:{}", this.contents.size());
    return this.contents.values();
  }

}
