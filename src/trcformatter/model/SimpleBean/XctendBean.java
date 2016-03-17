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
public class XctendBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int order;
  private String rlbk;
  private String rd_only;
  private String tim;

  public XctendBean(int order, String rlbk, String rd_only, String tim) {
    this.order   = order;
    this.rlbk    = rlbk;
    this.rd_only = rd_only;
    this.tim     = tim;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public String getRlbk() {
    return rlbk;
  }

  public void setRlbk(String rlbk) {
    this.rlbk = rlbk;
  }

  public String getRd_only() {
    return rd_only;
  }

  public void setRd_only(String rd_only) {
    this.rd_only = rd_only;
  }

  public String getTim() {
    return tim;
  }

  public void setTim(String tim) {
    this.tim = tim;
  }
  
  public String getLine() {
    return "XCTEND "
           + rlbk    + " "
           + rd_only + " "
           + tim     + System.getProperty("line.separator");
  }
}
