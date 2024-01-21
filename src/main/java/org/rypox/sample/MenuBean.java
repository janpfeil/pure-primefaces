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
 */
@Named
@SessionScoped
public class MenuBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private MenuModel model;

    public MenuBean() {
        this.model = new DefaultMenuModel();

        // First submenu
        final DefaultSubMenu.Builder submenuBuilder = DefaultSubMenu.builder();
        DefaultSubMenu firstSubmenu = submenuBuilder.label("Dynamic Submenu").build();
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("External Link primefaces.org")
                .build();
        item.setUrl("http://www.primefaces.org");
        item.setIcon("pi pi-home");
        firstSubmenu.getElements().add(item);
        this.model.getElements().add(firstSubmenu);
        // Second submenu
        final DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
                .label("Dynamic Actions")
                .build();
        item = DefaultMenuItem.builder().value("Save").build();
        item.setIcon("pi pi-save");
        item.setCommand("#{menuBean.save}");
        item.setUpdate("");
        secondSubmenu.getElements().add(item);
        item = DefaultMenuItem.builder().value("Delete").build();
        item.setIcon("pi pi-trash");
        item.setCommand("#{menuBean.delete}");
        item.setAjax(false);
        secondSubmenu.getElements().add(item);
        item = DefaultMenuItem.builder().value("Redirect").build();
        item.setIcon("pi pi-search");
        item.setCommand("#{menuBean.redirect}");
        secondSubmenu.getElements().add(item);
        this.model.getElements().add(secondSubmenu);
    }

    public MenuModel getModel() {
        return this.model;
    }

}
