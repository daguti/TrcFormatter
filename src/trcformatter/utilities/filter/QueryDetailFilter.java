/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trcformatter.utilities.filter;

import trcformatter.model.SimpleBean.CloseBean;
import trcformatter.model.SimpleBean.ExecBean;
import trcformatter.model.SimpleBean.FetchBean;
import trcformatter.model.SimpleBean.StatBean;
import trcformatter.model.SimpleBean.WaitBean;
import trcformatter.model.SimpleBean.XctendBean;
import trcformatter.model.staticData.StaticData;

/**
 *
 * @author ESA10969
 */
public class QueryDetailFilter {

  public static String getQueryDetail() {
    // Variable definition
    String text;
    boolean takeLine;

    text = StaticData.detInfo.getParsingInCursor().getLine();
    text += StaticData.detInfo.getQuery() + System.getProperty("line.separator")
        + "END OF STMT" + System.getProperty("line.separator");

    if (StaticData.detInfo.getParse() != null) {
      text += StaticData.detInfo.getParse().getLine();
    }
    for (int i = 0; i <= StaticData.detInfo.getMaxNum(); i++) {
      takeLine = false;
      if (StaticData.detInfo.getExec() != null && !takeLine) {
        for (ExecBean exec : StaticData.detInfo.getExec()) {
          if (exec.getOrder() == i) {
            text    += exec.getLine();
            takeLine = true;
            break;
          }
        }
      }
      if (StaticData.detInfo.getWait() != null && !takeLine) {
        for (WaitBean wait : StaticData.detInfo.getWait()) {
          if (wait.getOrder() == i) {
            text    += wait.getLine();
            takeLine = true;
            break;
          }
        }
      }
      if (StaticData.detInfo.getFetch() != null && !takeLine) {
        for (FetchBean fet : StaticData.detInfo.getFetch()) {
          if (fet.getOrder() == i) {
            text    += fet.getLine();
            takeLine = true;
            break;
          }
        }
      }
      if (StaticData.detInfo.getStat() != null && !takeLine) {
        for (StatBean stat : StaticData.detInfo.getStat()) {
          if (stat.getOrder() == i) {
            text    += stat.getLine();
            takeLine = true;
            break;
          }
        }
      }
      if (StaticData.detInfo.getXctend() != null && !takeLine) {
        for (XctendBean xctend : StaticData.detInfo.getXctend()) {
          if (xctend.getOrder() == i) {
            text    += xctend.getLine();
            takeLine = true;
            break;
          }
        }
      }
      if (StaticData.detInfo.getClose() != null && !takeLine) {
        for (CloseBean close : StaticData.detInfo.getClose()) {
          if (close.getOrder() == i) {
            text += close.getLine();
            break;
          }
        }
      }
    }
    return text;
  }
}
