/**
 * 
 */
package org.rypox.sample.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.rypox.sample.shop.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
public class ProductsModel extends LazyDataModel<Product> {
  private static final Logger LOG = LoggerFactory.getLogger(ProductsModel.class);
  private List<Product> products;

  public void init() {
    // build list of products
    products = new ArrayList<>();

    for (int index = 0; index < 1000; index++) {
      Product product = new Product("" + index, "p #" + index);
      products.add(product);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.primefaces.model.LazyDataModel#load(int, int, java.util.List, java.util.Map)
   */
  @Override
  public List<Product> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
    // TODO Auto-generated method stub
    // return super.load(first, pageSize, multiSortMeta, filters);

    List<Product> productList = new ArrayList<>();
    for (int index = 0; index < pageSize; index++) {
      Product product = products.get(first + index);
      productList.add(product);
    }
    return productList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
   */
  @Override
  public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    List<Product> productList=null;
    if (!filters.isEmpty()) {
      String id = (String) filters.get("productId");
      productList = this.products.stream().filter(p -> p.getProductId().contains(id)).collect(Collectors.toList());
      this.setRowCount(productList.size());
    }
    // return super.load(first, pageSize, sortField, sortOrder, filters);
    productList =productList==null? new ArrayList<>():productList;
    this.setRowCount(products.size());
    for (int index = first; index < Math.min(first + pageSize, products.size()); index++) {
      Product product = products.get(index);
      productList.add(product);
    }
    return productList;
  }

}
