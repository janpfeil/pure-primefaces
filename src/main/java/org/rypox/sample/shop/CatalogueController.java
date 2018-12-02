package org.rypox.sample.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@Named("catalogue")
@ViewScoped
public class CatalogueController implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger LOG = LoggerFactory.getLogger(CatalogueController.class);

  private List<Product> products;

  @Inject
  private Cart cart;

  @PostConstruct
  private void init() {
    LOG.debug("initialize catalogue ... {}", hashCode());

    this.products = new ArrayList<>();

    final Product product = new Product("12", "Vacuum Cleaner");
    this.products.add(product);

    final Product product2 = new Product("23", "Refrigerator");
    this.products.add(product2);
  }

  public String buy(final Product product) {
    LOG.debug("buy {}", product.getProductId());
    this.cart.add(product);
    return null;
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public void setProducts(final List<Product> products) {
    this.products = products;
  }
}
