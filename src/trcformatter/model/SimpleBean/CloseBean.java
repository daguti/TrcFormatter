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
public class CloseBean implements Serializable {
  private static final long serialVersionUID = 1L;
    
  private int order;
  private String exeId;
  private String c;
  private String e;
  private String dep;
  private String type;
  private String tim;

  public CloseBean(int order, String exeId, String c, String e, String dep, String type, String tim) {
      this.order = order;
      this.exeId = exeId;
      this.c     = c;
      this.e     = e;
      this.dep   = dep;
      this.type  = type;
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

  public String getDep() {
      return dep;
  }

  public void setDep(String dep) {
      this.dep = dep;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public String getTim() {
      return tim;
  }

  public void setTim(String tim) {
      this.tim = tim;
  }

  public String getLine() {
      return "CLOSE "
              + exeId + " "
              + c     + " "
              + e     + " "
              + dep   + " "
              + type  + " "
              + tim   + System.getProperty("line.separator");
  }
}
