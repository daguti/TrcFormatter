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
public class WaitBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int order;
  private String exeId;
  private String nam;
  private String ela;
  private String file;
  private String block;
  private String blocks;
  private String obj;
  private String tim;

  public WaitBean(int order, String exeId, String nam, String ela, String file, String block, 
                  String blocks, String obj, String tim) {
    this.order  = order;
    this.exeId  = exeId;
    this.nam    = nam;
    this.ela    = ela;
    this.file   = file;
    this.block  = block;
    this.blocks = blocks;
    this.obj    = obj;
    this.tim    = tim;
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

  public String getNam() {
    return nam;
  }

  public void setNam(String nam) {
    this.nam = nam;
  }

  public String getEla() {
    return ela;
  }

  public void setEla(String ela) {
    this.ela = ela;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public String getBlock() {
    return block;
  }

  public void setBlock(String block) {
    this.block = block;
  }

  public String getBlocks() {
    return blocks;
  }

  public void setBlocks(String blocks) {
    this.blocks = blocks;
  }

  public String getObj() {
    return obj;
  }

  public void setObj(String obj) {
    this.obj = obj;
  }

  public String getTim() {
    return tim;
  }

  public void setTim(String tim) {
    this.tim = tim;
  }
  
  public String getLine() {
    return "WAIT "
           + exeId  + " "
           + nam    + " "
           + ela    + " "
           + file   + " "
           + block  + " "
           + blocks + " "
           + obj    + " "
           + tim    + System.getProperty("line.separator");
  }
  
}
