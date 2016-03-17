/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.threads.dataLoad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import trcformatter.GUI.QueriesTable;
import trcformatter.Persistance.DAO.QueriesDAO;
import trcformatter.model.QueryInfoBean;
import trcformatter.model.staticData.StaticData;
import trcformatter.utilities.file.FileUtilities;

/**
 *
 * @author ESA10969
 */
public class FileLoader implements Runnable {
  
  private final String path;
  private final JFrame frame;
  private final String fecha;
  
  public FileLoader(String path, JFrame frame, String fecha) {
    this.path  = path;
    this.frame = frame;
    this.fecha = fecha;
  }
  
  public void loadFile() {
    //Variable definition
    ArrayList<QueryInfoBean> qryInfoLst = new ArrayList<QueryInfoBean>();
    QueryInfoBean qryInfo = null;
    BufferedReader reader = null;
    String line;
    String qry;
    boolean block = false;
    try {
      //retrieve reader
      reader = FileUtilities.getReader(path);
      
      line = reader.readLine();
      
      while (line != null) {
        if (line.contains("***") && StaticData.headerDate == null) {
          StaticData.headerDate = line.substring(4);
        }
        if (line.contains("CLOSE") && StaticData.iniMilis == null) {
          StaticData.iniMilis = line.substring(line.indexOf("tim=") + 4);
        }
        if (line.equals("=====================")) {
          if (qryInfo != null) {
            qryInfo.setMaxNum(StaticData.order);
            qryInfoLst.add(qryInfo);
          }
          qryInfo = new QueryInfoBean();
          StaticData.order = 0;
          block = true;
        } else if (block) {
          if(FileUtilities.nowQry) {
            qry = FileUtilities.processLine(line, reader, qryInfo);
          } else {
            qry = FileUtilities.processLine(line, null, qryInfo);
          }
          if (qry != null) {
            qryInfo.setQuery(qry);
          }
        }
        line = reader.readLine();
      }
      qryInfo.setMaxNum(StaticData.order);
      qryInfoLst.add(qryInfo);
      
      StaticData.qryInfoLst = qryInfoLst;
      
      JOptionPane.showMessageDialog(frame,
      "Fichero Cargado Correctamente",
      "An info message",
      JOptionPane.INFORMATION_MESSAGE);
      
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(frame,
      "No se encuentra el fichero, asegurese de que la ruta es correcta",
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(frame,
      "Ha ocurrido un error mientras se accedía al fichero. Compruebe los permisos del mismo",
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    } finally {
      if(reader != null) try {
        reader.close();
      } catch (IOException ex) {
        Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void loadFromDB(String fecha) {
    //Variable definition
    Thread th = new Thread(new QueriesDAO("SELECT", null, frame, fecha));
    
    th.start();
    try {
      th.join();
    } catch (InterruptedException ex) {
      Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void run() {
      if(fecha == null)loadFile();
      else loadFromDB(fecha);
      /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new QueriesTable().setVisible(true);
      }
    });
    frame.dispose();
  }
}
