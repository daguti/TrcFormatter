/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trcformatter.model.SimpleBean;

import java.io.Serializable;

/**
 *
 * @author ESA10969
 */
public class ExecBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private int order;
  private String exeId;
  private String c;
  private String e;
  private String p;
  private String cr;
  private String cu;
  private String mis;
  private String r;
  private String dep;
  private String og;
  private String plh;
  private String tim;

  public ExecBean(int order, String exeId, String c, String e, String p, String cr, String cu, String mis, String r, String dep, String og, String plh, String tim) {
    this.order = order;
    this.exeId = exeId;
    this.c     = c;
    this.e     = e;
    this.p     = p;
    this.cr    = cr;
    this.cu    = cu;
    this.mis   = mis;
    this.r     = r;
    this.dep   = dep;
    this.og    = og;
    this.plh   = plh;
    this.tim   = tim;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
  
  public String getExeId() {
    return exeId;
  }

  public void setExeId(String exeId) {
    this.exeId = exeId;
  }

  public String getC() {
    return c;
  }

  public void setC(String c) {
    this.c = c;
  }

  public String getE() {
    return e;
  }

  public void setE(String e) {
    this.e = e;
  }

  public String getP() {
    return p;
  }

  public void setP(String p) {
    this.p = p;
  }

  public String getCr() {
    return cr;
  }

  public void setCr(String cr) {
    this.cr = cr;
  }

  public String getCu() {
    return cu;
  }

  public void setCu(String cu) {
    this.cu = cu;
  }

  public String getMis() {
    return mis;
  }

  public void setMis(String mis) {
    this.mis = mis;
  }

  public String getR() {
    return r;
  }

  public void setR(String r) {
    this.r = r;
  }

  public String getDep() {
    return dep;
  }

  public void setDep(String dep) {
    this.dep = dep;
  }

  public String getOg() {
    return og;
  }

  public void setOg(String og) {
    this.og = og;
  }

  public String getPlh() {
    return plh;
  }

  public void setPlh(String plh) {
    this.plh = plh;
  }

  public String getTim() {
    return tim;
  }

  public void setTim(String tim) {
    this.tim = tim;
  }

  public String getLine() {
    return "EXEC "
        + exeId + " "
        + c + " "
        + e + " "
        + p + " "
        + cr + " "
        + cu + " "
        + mis + " "
        + r + " "
        + dep + " "
        + og + " "
        + plh + " "
        + tim + System.getProperty("line.separator");
  }
}
