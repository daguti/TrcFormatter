/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trcformatter.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import trcformatter.model.SimpleBean.CloseBean;
import trcformatter.model.SimpleBean.ExecBean;
import trcformatter.model.SimpleBean.FetchBean;
import trcformatter.model.SimpleBean.ParseBean;
import trcformatter.model.SimpleBean.ParsingInCursorBean;
import trcformatter.model.SimpleBean.StatBean;
import trcformatter.model.SimpleBean.WaitBean;
import trcformatter.model.SimpleBean.XctendBean;
import trcformatter.model.staticData.StaticData;

/**
 *
 * @author ESA10969
 */
public class QueryInfoBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private ParsingInCursorBean parsingInCursor;
  private ParseBean parse;
  private final ArrayList<ExecBean>  exec;
  private final ArrayList<WaitBean>  wait;
  private final ArrayList<FetchBean> fetch;
  private final ArrayList<StatBean>  stat;
  private final ArrayList<CloseBean> close;
  private final ArrayList<XctendBean> xctend;
  private String query;
  private String time;
  private String rlbk;
  private String rd_only;
  private int maxNum;

  //WAIT types counters
  private int bufferExterminate       = 0;
  private int dbFileSequentialRead    = 0;
  private int logFileSynq             = 0;
  private int logSharedPool           = 0;
  private int sqlNetMessageToClient   = 0;
  private int sqlNetMessageFromClient = 0;
  private int diskFileOperationIO     = 0;
  private int latchSharedPool         = 0;
  private int logFileSwitchCompletion = 0;
    
  public QueryInfoBean() {
    wait   = new ArrayList<WaitBean>();
    exec   = new ArrayList<ExecBean>();
    fetch  = new ArrayList<FetchBean>();
    stat   = new ArrayList<StatBean>();
    close  = new ArrayList<CloseBean>();
    xctend = new ArrayList<XctendBean>();
  }

  public int getBufferExterminate() {
    return bufferExterminate;
  }

  public void setBufferExterminate() {
    this.bufferExterminate++;
  }

  public int getDbFileSequentialRead() {
    return dbFileSequentialRead;
  }

  public void setDbFileSequentialRead() {
    this.dbFileSequentialRead++;
  }

  public int getLogFileSynq() {
    return logFileSynq;
  }

  public void setLogFileSynq() {
    this.logFileSynq++;
  }

  public int getLogSharedPool() {
    return logSharedPool;
  }

  public void setLogSharedPool() {
    this.logSharedPool++;
  }

  public int getSqlNetMessageToClient() {
    return sqlNetMessageToClient;
  }

  public void setSqlNetMessageToClient() {
    this.sqlNetMessageToClient++;
  }

  public int getSqlNetMessageFromClient() {
    return sqlNetMessageFromClient;
  }

  public void setSqlNetMessageFromClient() {
    this.sqlNetMessageFromClient++;
  }

  public int getDiskFileOperationIO() {
    return diskFileOperationIO;
  }

  public void setDiskFileOperationIO() {
    this.diskFileOperationIO++;
  }

  public int getLatchSharedPool() {
    return latchSharedPool;
  }

  public void setLatchSharedPool() {
    this.latchSharedPool++;
  }

  public int getLogFileSwitchCompletion() {
    return logFileSwitchCompletion;
  }

  public void setLogFileSwitchCompletion() {
    this.logFileSwitchCompletion++;
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

  public int getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(int maxNum) {
    this.maxNum = maxNum;
  }

  public ParsingInCursorBean getParsingInCursor() {
    return parsingInCursor;
  }

  public void setParsingInCursor(ParsingInCursorBean parsingInCursor) {
    this.parsingInCursor = parsingInCursor;
    setTime();
  }

  public ParseBean getParse() {
    return parse;
  }

  public void setParse(ParseBean parse) {
    this.parse = parse;
  }

  public ArrayList<ExecBean> getExec() {
    return exec;
  }

  public void setExec(ExecBean exec) {
    this.exec.add(exec);
  }
  
  public ArrayList<XctendBean> getXctend() {
    return xctend;
  }

  public void setXctend(XctendBean xctend) {
    this.xctend.add(xctend);
  }

  public ArrayList<FetchBean> getFetch() {
    return fetch;
  }

  public void setFetch(FetchBean fetch) {
    this.fetch.add(fetch);
  }
  
  public ArrayList<WaitBean> getWait() {
    return wait;
  }

  public void setWait(WaitBean wait) {
    this.wait.add(wait);
  }

  public ArrayList<StatBean> getStat() {
    return stat;
  }

  public void setStat(StatBean stat) {
    this.stat.add(stat);
  }

  public ArrayList<CloseBean> getClose() {
    return close;
  }

  public void setClose(CloseBean close) {
    this.close.add(close);
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getTime() {
    return time;
  }

  public void setTime() {
    Double exeIni = (Double.valueOf(parsingInCursor.getTim().substring(parsingInCursor.getTim().indexOf("=") + 1))
        - Double.valueOf(StaticData.iniMilis)) / 1000;
    String exeIniStr = Double.toString(exeIni);
    Calendar cal = Calendar.getInstance();
    Date iniToDate;

    try {
      iniToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(StaticData.headerDate);
      cal.setTime(iniToDate);
      cal.add(Calendar.SECOND, Integer.valueOf(exeIniStr.substring(0, exeIniStr.indexOf("."))));
      cal.add(Calendar.MILLISECOND, Integer.valueOf(exeIniStr.substring(exeIniStr.indexOf(".") + 1)));
      time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(cal.getTime());
    } catch (ParseException ex) {
      Logger.getLogger(QueryInfoBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getDep() {
    return parsingInCursor.getDep();
  }

  public String getSqlId() {
    return parsingInCursor.getSqlId();
  }
}
