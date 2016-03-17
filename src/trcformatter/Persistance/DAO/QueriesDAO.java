/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.Persistance.DAO;

import java.awt.Cursor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import trcformatter.Persistance.VerifyTable;
import trcformatter.model.QueryInfoBean;
import trcformatter.model.staticData.StaticData;
import trcformatter.utilities.DB.DbUtilities;

/**
 *
 * @author ESa10969
 */
public class QueriesDAO implements QueriesItfzDAO, Runnable {
  private final JFrame frame;
  private final String fecha;
  private final ArrayList<QueryInfoBean> qrsList;
  private final String oper;
  
  private Connection getConnection() {
    //Variable definition
    String url = "jdbc:derby:TrcFormatterPersistence;create=true;user=root;password=root";
    Connection con = null;
    
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
      con = DriverManager.getConnection(url);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }  catch (SQLException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return con;
  }

  @Override
  public void storeData(ArrayList<QueryInfoBean> qrsList, JFrame qryTable) {
    //Variable definition
    PreparedStatement st = null;
    String qry;
    InputStream is;
    byte[] byteArray;
    Connection con = null; 
    
    try {
      //Verify if table exists
      con = getConnection();
      VerifyTable.verifyTable(con);
      
      byteArray = DbUtilities.objectToBlob(qrsList);
      
      qry = "INSERT INTO " + StaticData.tableNam + " (FECHA, ALL_QUERIES) VALUES (?,?)";
      st = getConnection().prepareStatement(qry);
      is = new ByteArrayInputStream(byteArray);
      st.setString(1, StaticData.headerDate.substring(0, StaticData.headerDate.length() - 4));
      st.setBinaryStream(2, is, byteArray.length);
                                          
      st.execute();
      
      JOptionPane.showMessageDialog(qryTable,
      "Datos Guardados Correctamente",
      "An info message",
      JOptionPane.INFORMATION_MESSAGE);
      
    } catch (SQLException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(qryTable,
      "Ha ocurrido un error guardando los datos. \n" + ex.getMessage(),
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    } catch (IOException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(qryTable,
      "Ha ocurrido un error guardando los datos. \n" + ex.getMessage(),
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    } finally {
      try {
        qryTable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if(st != null) st.close();
        if(con != null) con.close();
      } catch (SQLException ex) {
        Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  @Override
  public void getStoredData(String fecha, JFrame loading) {
    //Variable definition
    Connection con;
    Statement st;
    ResultSet rs;
    String qry = "SELECT ALL_QUERIES FROM " + StaticData.tableNam + " WHERE FECHA = '" + fecha + "'";
    try {
      con = getConnection();
      st = con.createStatement();
      rs = st.executeQuery(qry);
      
      if(rs.next()) {
        Blob bl = rs.getBlob(1);
        StaticData.qryInfoLst = DbUtilities.blobToObject(bl.getBytes(1, (int)bl.length()));
      }
      
    } catch(SQLException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(loading,
      "Ha ocurrido un error cargando los datos. \n" + ex.getMessage(),
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void getRecordDates() {
    //Variable definition
    Connection con = null;
    Statement st = null;
    ResultSet rs;
    String qry = "SELECT FECHA FROM " + StaticData.tableNam;
    ArrayList<String> recDteLst = new ArrayList<String>();

    try {
      con = getConnection();
      st = con.createStatement();
      //st.execute("DROP TABLE " + StaticData.tableNam);
      VerifyTable.verifyTable(con);
      rs = st.executeQuery(qry);
      
      while(rs.next()) {
        recDteLst.add(rs.getString("FECHA"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if(st != null) st.close();
        if(con != null) con.close();
      } catch (SQLException ex) {
        Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    StaticData.fechas = recDteLst;
  }

  @Override
  public void delete(String fecha, JFrame load) {
    //Variable definition
    Connection con;
    Statement st;
    String qry = "DELETE FROM " + StaticData.tableNam + " WHERE FECHA = '" + fecha + "'";

    try {
      con = getConnection();
      st = con.createStatement();
      st.execute(qry);
      
    } catch (SQLException ex) {
      Logger.getLogger(QueriesDAO.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(load,
      "Ha ocurrido un error borrando los datos. \n" + ex.getMessage(),
      "An error message",
      JOptionPane.ERROR_MESSAGE);
    }  
  }

  @Override
  public void run() {
    if(oper.equals("INSERT")) {
      storeData(qrsList, frame);
    } else if(oper.equals("DELETE")) {
      delete(fecha, frame);
    } else if(oper.equals("FECHAS")) {
      getRecordDates();
    } else if(oper.equals("SELECT")) {
      getStoredData(fecha, frame);
    }
  }
  
  public QueriesDAO(String oper, ArrayList<QueryInfoBean> qrsList, JFrame frame, String fecha) {
    this.oper    = oper;
    this.qrsList = qrsList;
    this.frame   = frame;
    this.fecha   = fecha;
  }
}
