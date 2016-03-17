/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trcformatter.utilities.filter;

import javax.swing.table.DefaultTableModel;
import trcformatter.model.QueryInfoBean;
import trcformatter.model.SimpleBean.StatBean;
import trcformatter.model.staticData.StaticData;

/**
 *
 * @author ESA10969
 */
public class QueryFilter {

  public static void allDepFilter(DefaultTableModel model, String SQLid, String rlbk, String rdOnly,
                                  String qryTable, boolean tableFull) {
    //Variable definition
    String idDta;
    String infRlbk;
    String infRdOnly;
    String secQry;
    int toWhere;
    
    rlbk   = rlbk.equals("") ? "" : "rlbk=" + rlbk;
    rdOnly = rdOnly.equals("") ? "" : "rd_only=" + rdOnly;

    if (SQLid != null && !SQLid.equals("")) {
      for (QueryInfoBean qryInfo : StaticData.qryInfoLst) {
        idDta     = qryInfo.getSqlId();
        idDta     = idDta.substring(idDta.indexOf("'") + 1, idDta.lastIndexOf("'"));
        infRlbk   = rlbk.equals("") ? "" : qryInfo.getRlbk();
        infRdOnly = rdOnly.equals("") ? "" : qryInfo.getRd_only();
        secQry    = qryInfo.getQuery();
        toWhere   = secQry.indexOf("WHERE");
        
        if(toWhere != -1) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1, secQry.indexOf("WHERE"));
        } else if (!secQry.equals("")) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1);
        }
        
        if (idDta.equals(SQLid) && rlbk.equals(infRlbk) && rdOnly.equals(infRdOnly)
            && secQry.contains(qryTable)) {
          if(tableFull) {
            for(StatBean stat : qryInfo.getStat()) {
              if(stat.getOp().contains("TABLE ACCESS FULL")) {
                model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
                break;
              }
            }
          } else {
            model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
          }
        }
      }
    } else {
      for (QueryInfoBean qryInfo : StaticData.qryInfoLst) {
        idDta     = qryInfo.getSqlId();
        idDta     = idDta.substring(idDta.indexOf("'") + 1, idDta.lastIndexOf("'"));
        infRlbk   = rlbk.equals("") ? "" : qryInfo.getRlbk();
        infRdOnly = rdOnly.equals("") ? "" : qryInfo.getRd_only();
        secQry    = qryTable.equals("") ? "" : qryInfo.getQuery();
        toWhere   = secQry.indexOf("WHERE");
        
        if(toWhere != -1) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1, secQry.indexOf("WHERE"));
        } else if (!secQry.equals("")) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1);
        }
        
        if (rlbk.equals(infRlbk) && rdOnly.equals(infRdOnly) && secQry.contains(qryTable)) {
          if(tableFull) {
            for(StatBean stat : qryInfo.getStat()) {
              if(stat.getOp().contains("TABLE ACCESS FULL")) {
                model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
                break;
              }
            }
          } else {
            model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
          }
        }
      }
    }
  }

  public static void byDepFilter(DefaultTableModel model, String depVal, String SQLid, String rlbk, 
                                 String rdOnly, String qryTable, boolean tableFull) {
    //Variable definition
    String idDta;
    String dep;
    String infRlbk;
    String infRdOnly;
    String secQry;
    int toWhere;
    
    rlbk   = rlbk.equals("") ? "" : "rlbk=" + rlbk;
    rdOnly = rdOnly.equals("") ? "" : "rd_only=" + rdOnly;

    if (SQLid != null && !SQLid.equals("")) {
      for (QueryInfoBean qryInfo : StaticData.qryInfoLst) {
        idDta     = qryInfo.getSqlId();
        idDta     = idDta.substring(idDta.indexOf("'") + 1, idDta.lastIndexOf("'"));
        dep       = qryInfo.getDep();
        infRlbk   = rlbk.equals("") ? "" : qryInfo.getRlbk();
        infRdOnly = rdOnly.equals("") ? "" : qryInfo.getRd_only();
        secQry    = qryTable.equals("") ? "" : qryInfo.getQuery();
        toWhere   = secQry.indexOf("WHERE");
        
        if(toWhere != -1) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1, secQry.indexOf("WHERE"));
        } else if (!secQry.equals("")) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1);
        }
        
        if (idDta.equals(SQLid) && dep.substring(dep.indexOf("=") + 1).trim().equals(depVal)
            && rlbk.equals(infRlbk) && rdOnly.equals(infRdOnly) && secQry.contains(qryTable)) {
          if(tableFull) {
            for(StatBean stat : qryInfo.getStat()) {
              if(stat.getOp().contains("TABLE ACCESS FULL")) {
                model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
                break;
              }
            }
          } else {
            model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
          }
        }
      }
    } else {
      for (QueryInfoBean qryInfo : StaticData.qryInfoLst) {
        dep       = qryInfo.getDep();
        idDta     = qryInfo.getSqlId();
        idDta     = idDta.substring(idDta.indexOf("'") + 1, idDta.lastIndexOf("'"));
        infRlbk   = rlbk.equals("") ? "" : qryInfo.getRlbk();
        infRdOnly = rdOnly.equals("") ? "" : qryInfo.getRd_only();
        secQry    = qryTable.equals("") ? "" : qryInfo.getQuery();
        toWhere   = secQry.indexOf("WHERE");
        
        if(toWhere != -1) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1, secQry.indexOf("WHERE"));
        } else if (!secQry.equals("")) {
          secQry    = qryTable.equals("") ? "" : secQry.substring(secQry.indexOf("FROM") + 1);
        }
        
        if (dep.substring(dep.indexOf("=") + 1).trim().equals(depVal)
            && rlbk.equals(infRlbk) && rdOnly.equals(infRdOnly) && secQry.contains(qryTable)) {
          if(tableFull) {
            for(StatBean stat : qryInfo.getStat()) {
              if(stat.getOp().contains("TABLE ACCESS FULL")) {
                model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
                break;
              }
            }
          } else {
            model.addRow(new Object[]{qryInfo.getDep(), qryInfo.getQuery(), qryInfo.getTime(), idDta});
          }
        }
      }
    }
  }
}
