package org.rypox.sample.automobile;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("stringArrayConverter")
public class StringArrayConverter extends AbstractConverter {
  private static final Logger LOG = LoggerFactory.getLogger(StringArrayConverter.class);

  @Override
  public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
    LOG.trace("getAsString: {}", value);
    final String id;
    if (value instanceof String[]) {
      id = ((String[]) value)[0];
      super.getIdMap(context, component.getId()).put(id, value);
    } else {
      throw new ConverterException(new FacesMessage(value + " is not a valid String[]"));
    }
    return id;
  }

}
