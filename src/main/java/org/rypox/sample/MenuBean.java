package org.rypox.sample;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 * @author jan.pfeil
 *
 */
@Named
@SessionScoped
public class MenuBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private MenuModel model;

  public MenuBean() {
    this.model = new DefaultMenuModel();

    // First submenu
    final DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
    DefaultMenuItem item = new DefaultMenuItem("External Link primefaces.org");
    item.setUrl("http://www.primefaces.org");
    item.setIcon("pi pi-home");
    firstSubmenu.addElement(item);
    this.model.addElement(firstSubmenu);
    // Second submenu
    final DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
    item = new DefaultMenuItem("Save");
    item.setIcon("pi pi-save");
    item.setCommand("#{menuBean.save}");
    item.setUpdate("");
    secondSubmenu.addElement(item);
    item = new DefaultMenuItem("Delete");
    item.setIcon("pi pi-trash");
    item.setCommand("#{menuBean.delete}");
    item.setAjax(false);
    secondSubmenu.addElement(item);
    item = new DefaultMenuItem("Redirect");
    item.setIcon("pi pi-search");
    item.setCommand("#{menuBean.redirect}");
    secondSubmenu.addElement(item);
    this.model.addElement(secondSubmenu);
  }

  public MenuModel getModel() {
    return this.model;
  }

}
