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
public class StatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private int order;
  private String exeId;
  private String id;
  private String cnt;
  private String pid;
  private String pos;
  private String obj;
  private String op;

  public StatBean(int order, String exeId, String id, String cnt, String pid, String pos, 
                  String obj, String op) {
    this.order = order;
    this.exeId = exeId;
    this.id    = id;
    this.cnt   = cnt;
    this.pid   = pid;
    this.pos   = pos;
    this.obj   = obj;
    this.op    = op;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCnt() {
    return cnt;
  }

  public void setCnt(String cnt) {
    this.cnt = cnt;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getPos() {
    return pos;
  }

  public void setPos(String pos) {
    this.pos = pos;
  }

  public String getObj() {
    return obj;
  }

  public void setObj(String obj) {
    this.obj = obj;
  }

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public String getLine() {
    return "STAT "
        + exeId + " "
        + id + " "
        + cnt + " "
        + pid + " "
        + pos + " "
        + obj + " "
        + op + System.getProperty("line.separator");
  }
}
