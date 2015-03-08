/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MENUGROUPDEPARMENT")
public class MenuGroupDeparment
{
  private Long idmenu;
  private Long idgroup;
  private Long iddepartment;

  @Id
  @Column(name="IDMENU")
  public Long getIdMenu()
  {
    return this.idmenu;
  }
  public void setIdMenu(Long idmenu) {
    this.idmenu = idmenu;
  }

  @Column(name="IDDEPARMENT")
  public Long getIdDeparment() {
    return this.iddepartment;
  }
  public void setIdDeparment(Long iddepartment) {
    this.iddepartment = iddepartment;
  }

  @Column(name="IDGROUP")
  public Long getIdGroup()
  {
    return this.idgroup;
  }
  public void setIdGroup(Long idgroup) {
    this.idgroup = idgroup;
  }
}