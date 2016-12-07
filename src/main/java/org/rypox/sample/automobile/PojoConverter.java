/**
 *
 */
package org.rypox.sample.automobile;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 * @author jan.pfeil
 *
 */
@FacesConverter("pojoConverter")
public class PojoConverter extends AbstractConverter {

  /*
   * (non-Javadoc)
   *
   * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent,
   * java.lang.Object)
   */
  @Override
  public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
    String id;
    if (null == value) {
      id = "";
    } else {
      id = ((Brand) value).getId().toString();
    }
    super.getIdMap(context, component.getId()).put(id, value);
    // LOG.debug("getAsString:{}->{}", value, id);
    return id;
  }

}
