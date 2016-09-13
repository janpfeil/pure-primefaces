package org.rypox.sample.domain;

public class Task {
  private Long id;
  private String subject;
  private String description;
  private String opsys;

  public Task() {
    super();
  }

  public Task(String subject, String description) {
    this();
    this.subject = subject;
    this.description = description;
    this.opsys = "fedora";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getOpsys() {
    return opsys;
  }

  public void setOpsys(String opsys) {
    this.opsys = opsys;
  }
}
