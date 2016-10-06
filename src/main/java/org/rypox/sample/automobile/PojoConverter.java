/**
 * 
 */
package org.rypox.sample.automobile;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.pfeil
 *
 */
@FacesConverter("pojoConverter")
public class PojoConverter implements Converter {
  private Logger log = LoggerFactory.getLogger(PojoConverter.class);
  @Resource
  private AutoMarket market;

  /*
   * (non-Javadoc)
   * 
   * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent,
   * java.lang.String)
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Integer key = Integer.valueOf(value);
    Brand brand = market.getBrands().get(key);
    // log.debug("getAsObject:{}->{}", value, brand);
    return brand;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent,
   * java.lang.Object)
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    String id;
    if (null == value) {
      id = "";
    } else {
      id = ((Brand) value).getId().toString();
    }
    // log.debug("getAsString:{}->{}", value, id);
    return id;
  }

}
