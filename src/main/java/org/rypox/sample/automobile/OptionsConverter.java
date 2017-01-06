package org.rypox.sample.automobile;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("optionsConverter")
public class OptionsConverter extends AbstractConverter {
  private static final Logger LOG = LoggerFactory.getLogger(OptionsConverter.class);

  @Override
  public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
    LOG.trace("getAsString: {}", value);
    final String id = ((OptionDsp) value).getId();
    super.getIdMap(context, component.getId()).put(id, value);
    return id;
  }

}
