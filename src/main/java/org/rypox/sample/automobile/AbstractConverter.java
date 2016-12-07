package org.rypox.sample.automobile;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConverter implements Converter {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractConverter.class);

  @Override
  public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
    LOG.trace("getAsObject: {}", value);
    if (null == value) {
      return null;
    } else {
      return getIdMap(context, component.getId()).get(value);
    }
  }

  protected Map<String, Object> getIdMap(final FacesContext context, final String compId) {
    final Map<String, Object> viewMap = context.getViewRoot().getViewMap();
    @SuppressWarnings("unchecked")
    Map<String, Object> idMap = (Map<String, Object>) viewMap.get(compId);
    if (idMap == null) {
      idMap = new HashMap<String, Object>();
      viewMap.put(compId, idMap);
    }
    return idMap;
  }

}
