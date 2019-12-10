/**
 *
 */
package org.rypox.sample.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
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
    this.products = new ArrayList<>();

    for (int index = 0; index < 1000; index++) {
      final Product product = new Product("" + index, "p #" + index);
      this.products.add(product);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.primefaces.model.LazyDataModel#load(int, int, java.util.List, java.util.Map)
   */
  @Override
  public List<Product> load(final int first, final int pageSize, final List<SortMeta> multiSortMeta,
      final List<FilterMeta> filters) {
    // TODO Auto-generated method stub
    // return super.load(first, pageSize, multiSortMeta, filters);

    final List<Product> productList = new ArrayList<>();
    for (int index = 0; index < pageSize; index++) {
      final Product product = this.products.get(first + index);
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
  public List<Product> load(final int first, final int pageSize, final String sortField, final SortOrder sortOrder,
      final List<FilterMeta> filterMeta) {
    List<Product> productList = null;
    if (!filterMeta.isEmpty()) {
      // final String id = (String) filter.get("productId");
      final String id = (String) filterMeta.get(0).getFilterValue();
      productList = this.products.stream().filter(p -> p.getProductId().contains(id)).collect(Collectors.toList());
      setRowCount(productList.size());
    }
    // return super.load(first, pageSize, sortField, sortOrder, filters);
    productList = productList == null ? new ArrayList<>() : productList;
    setRowCount(this.products.size());
    for (int index = first; index < Math.min(first + pageSize, this.products.size()); index++) {
      final Product product = this.products.get(index);
      productList.add(product);
    }
    return productList;
  }

}
