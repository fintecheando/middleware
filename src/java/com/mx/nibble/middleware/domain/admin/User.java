/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User
{
  private Long id;
  private String name;
  private String alias;
  private String description;
  private String password;
  private Long idgroup;
  private Boolean active;
  private String preferences;
  private Long numticks;

  @Id
  @GeneratedValue
  @Column(name="ID")
  public Long getId()
  {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  @Column(name="IDGROUP")
  public Long getIdGroup() {
    return this.idgroup;
  }
  public void setIdGroup(Long idgroup) {
    this.idgroup = idgroup;
  }

  @Column(name="NUMTICKS")
  public Long getNumTicks() {
    return this.numticks;
  }
  public void setNumTicks(Long numticks) {
    this.numticks = numticks;
  }

  @Column(name="NAME")
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Column(name="ALIAS")
  public String getAlias() {
    return this.alias;
  }
  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Column(name="DESCRIPTION")
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name="PASSWORD")
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name="PREFERENCES")
  public String getPreferences() {
    return this.preferences;
  }
  public void setPreferences(String preferences) {
    this.preferences = preferences;
  }

  @Column(name="ACTIVE")
  public Boolean getActive() {
    return this.active;
  }
  public void setActive(Boolean active) {
    this.active = active;
  }
}