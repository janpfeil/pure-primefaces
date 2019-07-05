/**
 * 
 */
package org.rypox.sample.lazy;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@Named
@ViewScoped
public class ProductsView implements Serializable{
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(ProductsView.class);
  private ProductsModel model;

  @PostConstruct
  private void init() {
    LOG.info("init ProductsView");

    this.model = new ProductsModel();
    this.model.init();
  }

  public ProductsModel getModel() {
    return model;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "products lazy view model";
  }

}
