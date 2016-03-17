/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trcformatter.utilities.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import trcformatter.model.QueryInfoBean;
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
public class FileUtilities {

  public static boolean nowQry = false;

  public static BufferedReader getReader(String path) throws FileNotFoundException, MalformedURLException, IOException {
    //Variable definition
    BufferedReader reader;
    File fil = new File(path);
        
    reader = new BufferedReader(new InputStreamReader(fil.toURI().toURL().openStream()));

    return reader;
  }

  public static String processLine(String line, BufferedReader reader, QueryInfoBean qryInfo) throws IOException {
    //Variable definition
    StatBean            stat;
    ParseBean           parse;
    ExecBean            exec;
    FetchBean           fetch;
    CloseBean           close;
    XctendBean          xctend;
    ParsingInCursorBean parsingInCursor;

    String qry = "";
    String dataBlock;
    String data[];

    int order;

    if (nowQry) {
      while (!line.contains("END OF STMT")) {
        qry += line + " ";
        line = reader.readLine();
      }
      reader.readLine();
      nowQry = false;
      return qry;
    } else if (line.contains(" ")) {
      dataBlock = line.substring(0, line.indexOf(" "));
      data      = line.substring(line.indexOf(":") + 1).split(",");

      order = StaticData.order;

      if (dataBlock.equals("PARSING")) {
        parsingInCursor = new ParsingInCursorBean(line.substring(line.indexOf("#"), line.indexOf("len") - 1),
                                                  data[0].substring(data[0].indexOf("len"), data[0].indexOf("dep") - 1),
                                                  data[0].substring(data[0].indexOf("dep"), data[0].indexOf("uid") - 1),
                                                  data[0].substring(data[0].indexOf("uid"), data[0].indexOf("oct") - 1),
                                                  data[0].substring(data[0].indexOf("oct"), data[0].indexOf("lid") - 1),
                                                  data[0].substring(data[0].indexOf("lid"), data[0].indexOf("tim") - 1),
                                                  data[0].substring(data[0].indexOf("tim"), data[0].indexOf("hv") - 1),
                                                  data[0].substring(data[0].indexOf("hv"), data[0].indexOf("ad") - 1),
                                                  data[0].substring(data[0].indexOf("ad"), data[0].indexOf("sqlid") - 1),
                                                  data[0].substring(data[0].indexOf("sqlid")));
        qryInfo.setParsingInCursor(parsingInCursor);
        nowQry = true;
      } else if (dataBlock.equals("PARSE")) {
        parse = new ParseBean(line.substring(line.indexOf("#"), line.indexOf(":")),
                              data[0], data[1], data[2], data[3], data[4],
                              data[5], data[6], data[7], data[8], data[9],
                              data[10]);
        qryInfo.setParse(parse);
      } else if (dataBlock.equals("EXEC")) {
        exec = new ExecBean(order, line.substring(line.indexOf("#"), line.indexOf(":")),
                            data[0], data[1], data[2], data[3], data[4],
                            data[5], data[6], data[7], data[8], data[9],
                            data[10]);
        StaticData.order++;
        qryInfo.setExec(exec);
      } else if (dataBlock.equals("STAT")) {
        stat = new StatBean(order, line.substring(line.indexOf("#"), line.indexOf("id") - 1),
                            data[0].substring(data[0].indexOf("id"), data[0].indexOf("cnt") - 1),
                            data[0].substring(data[0].indexOf("cnt"), data[0].indexOf("pid") - 1),
                            data[0].substring(data[0].indexOf("pid"), data[0].indexOf("pos") - 1),
                            data[0].substring(data[0].indexOf("pos"), data[0].indexOf("obj") - 1),
                            data[0].substring(data[0].indexOf("obj"), data[0].indexOf("op") - 1),
                            data[0].substring(data[0].indexOf("op")));
        StaticData.order++;
        qryInfo.setStat(stat);
      } else if (dataBlock.equals("WAIT")) {
        qryInfo.setWait(processWait(data[0], line, qryInfo));
        StaticData.order++;
      } else if (dataBlock.equals("FETCH")) {
        fetch = new FetchBean(order, line.substring(line.indexOf("#"), line.indexOf(":")),
                                                    data[0], data[1], data[2], data[3], data[4],
                                                    data[5], data[6], data[7], data[8], data[9],
                                                    data[10]);
        StaticData.order++;
        qryInfo.setFetch(fetch);
      } else if (dataBlock.equals("CLOSE")) {
        close = new CloseBean(order, line.substring(line.indexOf("#"), line.indexOf(":")),
                              data[0], data[1], data[2], data[3], data[4]);
        StaticData.order++;
        qryInfo.setClose(close);
      } else if (dataBlock.equals("XCTEND")) {

        xctend = new XctendBean(order,
                                data[0].substring(data[0].indexOf(" ") + 1),
                                data[1].trim(),
                                data[2].trim());
        StaticData.order++;
        qryInfo.setRlbk(xctend.getRlbk());
        qryInfo.setRd_only(xctend.getRd_only());
        qryInfo.setXctend(xctend);
      }
    }

    return null;
  }

  private static WaitBean processWait(String data, String line, QueryInfoBean qryInfo) {
    //Variable definition
    int order = StaticData.order;
    WaitBean wait;
    
    if (data.contains("buffer exterminate")) {
      //file = file# && block = block# && blocks = buf_ptr
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("file#") - 1),
                          data.substring(data.indexOf("file#"), data.indexOf("block#") - 1),
                          data.substring(data.indexOf("block#"), data.indexOf("buf_ptr") - 1),
                          data.substring(data.indexOf("buf_ptr"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setBufferExterminate();
    } else if (data.contains("file#")) {
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("file#") - 1),
                          data.substring(data.indexOf("file#"), data.indexOf("block#") - 1),
                          data.substring(data.indexOf("block"), data.indexOf("blocks") - 1),
                          data.substring(data.indexOf("blocks"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setDbFileSequentialRead();
    } else if (data.contains("buffer#")) {
      //file = buffer# && block = sync scn && blocks = p3
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("buffer#") - 1),
                          data.substring(data.indexOf("buffer#"), data.indexOf("sync scn") - 1),
                          data.substring(data.indexOf("sync scn"), data.indexOf("p3") - 1),
                          data.substring(data.indexOf("p3"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setLogFileSynq();
    } else if (data.contains("FileOperation")) {
      //file = FileOperation && block = fileno && blocks = filetype
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("FileOperation") - 1),
                          data.substring(data.indexOf("FileOperation"), data.indexOf("fileno") - 1),
                          data.substring(data.indexOf("fileno"), data.indexOf("filetype") - 1),
                          data.substring(data.indexOf("filetype"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setDiskFileOperationIO();
    } else if (data.contains("address")) {
      //file = address && block = number && blocks = tries
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("address") - 1),
                          data.substring(data.indexOf("address"), data.indexOf("number") - 1),
                          data.substring(data.indexOf("number"), data.indexOf("tries") - 1),
                          data.substring(data.indexOf("tries"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setLogSharedPool();
    } else if (data.contains("p1") && data.contains("p2") && data.contains("p3")) {
      //file = p1 && block = p2 && blocks = p3
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("p1") - 1),
                          data.substring(data.indexOf("p1"), data.indexOf("p2") - 1),
                          data.substring(data.indexOf("p2"), data.indexOf("p3") - 1),
                          data.substring(data.indexOf("p3"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      qryInfo.setLogFileSwitchCompletion();
    } else {
      //file = driver id && block = bytes && blocks = p3
      wait = new WaitBean(order, line.substring(line.indexOf("#"), line.indexOf("nam") - 1),
                          data.substring(data.indexOf("nam"), data.indexOf("ela") - 1),
                          data.substring(data.indexOf("ela"), data.indexOf("driver id") - 1),
                          data.substring(data.indexOf("driver id"), data.indexOf("#bytes") - 1),
                          data.substring(data.indexOf("#bytes"), data.indexOf("p3") - 1),
                          data.substring(data.indexOf("p3"), data.indexOf("obj#") - 1),
                          data.substring(data.indexOf("obj#"), data.indexOf("tim") - 1),
                          data.substring(data.indexOf("tim")));
      if(wait.getNam().contains("SQL*Net message to client")) {
        qryInfo.setSqlNetMessageToClient();
      } else {
        qryInfo.setSqlNetMessageFromClient();
      }
    }
    return wait;
  }
}
