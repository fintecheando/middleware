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
@Table(name="USERGROUP")
public class UserGroup
{
  private Long id;
  private String name;
  private String description;
  private String color;
  private String menu;

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

  @Column(name="NAME")
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Column(name="DESCRIPTION")
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name="COLOR")
  public String getColor() {
    return this.color;
  }
  public void setColor(String color) {
    this.color = color;
  }

  @Column(name="MENU")
  public String getMenu() {
    return this.menu;
  }
  public void setMenu(String menu) {
    this.menu = menu;
  }
}