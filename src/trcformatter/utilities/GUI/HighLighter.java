/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.utilities.GUI;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
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
public class HighLighter {
  
  public static void highLightText(Highlighter hl, String blockNam, String text, JComboBox waitCombo) {
    //Variable definition
    int index;
    
    if(blockNam.equals("CLOSE") && StaticData.detInfo.getClose().size() > 0) {
      for(CloseBean close : StaticData.detInfo.getClose()) {
        index = text.indexOf(close.getLine());
        highLightText(hl, index, index + close.getLine().length());
      }
    } else if(blockNam.equals("EXEC") && StaticData.detInfo.getExec().size() > 0) {
      for(ExecBean exec : StaticData.detInfo.getExec()) {
        index = text.indexOf(exec.getLine());
        highLightText(hl, index, index + exec.getLine().length());
      }
    } else if(blockNam.equals("QUERY") && StaticData.detInfo.getQuery() != null) {
      index = text.indexOf(StaticData.detInfo.getQuery());
      highLightText(hl, index, index + StaticData.detInfo.getQuery().length());
    } else if(blockNam.equals("FETCH") && StaticData.detInfo.getFetch().size() > 0) {
      for(FetchBean fetch : StaticData.detInfo.getFetch()) {
        index = text.indexOf(fetch.getLine());
        highLightText(hl, index, index + fetch.getLine().length());
      }
    } else if(blockNam.equals("PARSE") && StaticData.detInfo.getParse() != null) {
      index = text.indexOf(StaticData.detInfo.getParse().getLine());
      highLightText(hl,index, index + StaticData.detInfo.getParse().getLine().length());
    } else if(blockNam.equals("PARSING IN CURSOR") && StaticData.detInfo.getParsingInCursor() != null) {
      index = text.indexOf(StaticData.detInfo.getParsingInCursor().getLine());
      highLightText(hl,index, index + StaticData.detInfo.getParsingInCursor().getLine().length());
    } else if(blockNam.equals("STAT") && StaticData.detInfo.getStat().size() > 0) {
      for(StatBean stat : StaticData.detInfo.getStat()) {
        index = text.indexOf(stat.getLine());
        highLightText(hl, index, index + stat.getLine().length());
      }
    } else if(blockNam.equals("WAIT") && StaticData.detInfo.getWait().size() > 0) {
      String waitType = waitCombo.getSelectedItem().toString();
      for(WaitBean wait : StaticData.detInfo.getWait()) {
        if(!waitType.equals("") && wait.getNam().equals("nam='" + waitType + "'")) {
          index = text.indexOf(wait.getLine());
          highLightText(hl, index, index + wait.getLine().length());
        } else if(waitType.equals("")) {
          index = text.indexOf(wait.getLine());
          highLightText(hl,index, index + wait.getLine().length());
        }
      }
    } else if(blockNam.equals("XCTEND") && StaticData.detInfo.getXctend().size() > 0) {
      for(XctendBean xctend : StaticData.detInfo.getXctend()) {
        index = text.indexOf(xctend.getLine());
        highLightText(hl,index, index + xctend.getLine().length());
      }
    }
  }
  private static void highLightText(Highlighter hl, int ini, int end) {
    try {
      hl.addHighlight(ini, end, new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
    } catch (BadLocationException ex) {
      Logger.getLogger(HighLighter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
