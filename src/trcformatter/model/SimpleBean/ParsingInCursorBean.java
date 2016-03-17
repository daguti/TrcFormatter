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
public class ParsingInCursorBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
    private String exeId;
    private String len;
    private String dep;
    private String uid;
    private String oct;
    private String lid;
    private String tim;
    private String hv;
    private String ad;
    private String sqlId;

    public ParsingInCursorBean() {}
    
    public ParsingInCursorBean(String exeId, String len, String dep, String uid, String oct, String lid, String tim, String hv, String ad, String sqlId) {
        this.exeId = exeId;
        this.len   = len;
        this.dep   = dep;
        this.uid   = uid;
        this.oct   = oct;
        this.lid   = lid;
        this.tim   = tim;
        this.hv    = hv;
        this.ad    = ad;
        this.sqlId = sqlId;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOct() {
        return oct;
    }

    public void setOct(String oct) {
        this.oct = oct;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getHv() {
        return hv;
    }

    public void setHv(String hv) {
        this.hv = hv;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }
    
    public String getLine() {
        return "PARSING IN CURSOR " 
                + exeId + " " 
                + len   + " "  
                + dep   + " "
                + uid   + " "
                + oct   + " "
                + lid   + " "
                + tim   + " "
                + hv    + " "
                + ad    + " "
                + sqlId + System.getProperty("line.separator");
    }
}
