package org.rypox.sample.so;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.column.Column;
import org.primefaces.component.contextmenu.ContextMenu;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DynamicMenuModel;
import org.rypox.sample.ColumnModel;
import org.rypox.sample.DropDownColumn;
import org.rypox.sample.domain.Task;

@ManagedBean
@SessionScoped
public class TaskListSo implements Serializable {
  private static final long serialVersionUID = 3872411784218998192L;
  private Map<Long, Task> persistedTasks;
  private List<Task> tasks;
  private Long lastId;
  private List<ColumnModel> columns;
  private Task selectItem;
  private boolean isStarted;
  private boolean isStopped;

  @PostConstruct
  public void init() {
    this.tasks = new ArrayList<>();
    this.persistedTasks = new HashMap<>();
    this.lastId = 0L;
    addTask(new Task("todo gestern", "machte ich gestern"));
    addTask(new Task("todo heute", "mach ich morgen"));
    addTask(new Task("todo morgen", "mach ich übermorgen"));
    this.columns = new ArrayList<>();
    this.columns.add(new ColumnModel("ID", "id"));
    this.columns.add(new ColumnModel("Subject", "subject"));
    this.columns.add(new ColumnModel("Description", "description"));
    this.columns.add(new DropDownColumn("OS", "opsys", getOptions()));

    this.isStarted = false;
    this.isStopped = true;
  }

  public UIComponent getBuildTable() {
    /**
     * test java config of jsf table component
     */
    final FacesContext fc = FacesContext.getCurrentInstance();
    final Application application = fc.getApplication();
    final ExpressionFactory ef = application.getExpressionFactory();

    final DataTable table = (DataTable) application.createComponent(DataTable.COMPONENT_TYPE);
    table.setValue(this.tasks);
    table.setSelectionMode("single");
    table.setVar("item");
    table.setRowKey("#{item.id}");
    table.setId("tableId");

    final Column column = (Column) application.createComponent(Column.COMPONENT_TYPE);
    column.setHeaderText("id");
    final List<UIColumn> columns = new ArrayList<>();
    columns.add(column);
    table.setColumns(columns);

    final MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{taskListSo.onSelect}", String.class, new Class[0]);
    final MethodExpression meArg = ef.createMethodExpression(fc.getELContext(), "#{taskListSo.onSelect}", String.class,
        new Class[] { SelectEvent.class });
    final AjaxBehavior ajaxBehavior = new AjaxBehavior();
    ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(me, meArg));
    table.addClientBehavior("rowSelect", ajaxBehavior);

    final ContextMenu ctxMenu = new ContextMenu();
    ctxMenu.setFor("tableId");

    final DynamicMenuModel ctxModel = new DynamicMenuModel();
    final DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue("Hello Menu Item");
    ctxModel.addElement(menuItem);

    ctxMenu.setModel(ctxModel);

    final UIComponent component = application.createComponent(Panel.COMPONENT_TYPE);

    // final UIComponent component = fc.getViewRoot().findComponent("ctxmenu");
    component.getChildren().add(ctxMenu);
    component.getChildren().add(table);

    // RequestContext.getCurrentInstance().update(TreeManagedBean.rightCenterForm);
    return component;
  }

  public void setBuildTable(final UIComponent component) {
    System.out.println("TaskListSo.setBuildTable()");
  }

  public void onSelect(final SelectEvent event) {
    System.out.println("TaskList.onSelect() + event:" + event);
  }

  // public void onSelect() {
  // System.out.println("TaskList.onSelect()");
  // }

  /**
   * key/value select options
   *
   * @return
   */
  public HashMap<String, String> getOptions() {
    final HashMap<String, String> options = new HashMap<>();
    options.put("centos", "CentOS");
    options.put("fedora", "Fedora");
    options.put("debian", "Debian");
    options.put("suse", "OpenSuse");
    return options;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public String onAdd() {
    return "add_item.jsf?faces-redirect=true";
  }

  public String onEdit(final Task task) {
    return "edit_item.jsf?faces-redirect=true&task_id=";
  }

  public String onDelete(final Task task) {
    this.persistedTasks.remove(task);
    this.tasks.remove(task);
    return null;
  }

  public Task getTask(final Long id) {
    return this.persistedTasks.get(id);
  }

  public void addTask(final Task task) {
    final Long key = generate();
    task.setId(key);
    this.persistedTasks.put(key, task);
    this.tasks.add(task);
  }

  private Long generate() {
    return this.lastId++;
  }

  public void update(final Task task) {
    this.persistedTasks.put(task.getId(), task);
    // TODO update task in tasks
  }

  public List<ColumnModel> getColumns() {
    return this.columns;
  }

  public void startEngine() {
    System.out.println("I am here and started");
    this.isStarted = true;
    this.isStopped = false;
  }

  public void stopEngine() {
    System.out.println("I am here and stopped");
    this.isStopped = true;
    this.isStarted = false;
  }

  public boolean engineIsStarted() {
    return this.isStopped;
  }

  public boolean engineIsStopped() {
    return this.isStarted;
  }
}
