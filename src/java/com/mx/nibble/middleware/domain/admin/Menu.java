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

/**
 *
 * @author victor
 */
@Entity
@Table(name="MENU")
public class Menu
{
  private Long id;
  private Long iddepartment;
  private String description;
  private String url;
  private Integer level;
  private String image;
  private Integer submenu;
  private Integer width;

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

  @Column(name="IDDEPARMENT")
  public Long getIdDeparment() {
    return this.iddepartment;
  }
  public void setIdDeparment(Long iddepartment) {
    this.iddepartment = iddepartment;
  }

  @Column(name="DESCRIPTION")
  public String getDescription() {
    return this.description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name="URL")
  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  @Column(name="MENU_LEVEL")
  public Integer getMenuLevel() {
    return this.level;
  }
  public void setMenuLevel(Integer level) {
    this.level = level;
  }

  @Column(name="IMAGE")
  public String getImage() {
    return this.image;
  }
  public void setimage(String image) {
    this.image = image;
  }

  @Column(name="SUBMENU")
  public Integer getSubmenu() {
    return this.submenu;
  }
  public void setSubmenu(Integer submenu) {
    this.submenu = submenu;
  }

  @Column(name="WIDTH")
  public Integer getWidth() {
    return this.width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }
}