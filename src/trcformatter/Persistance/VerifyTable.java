/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.Persistance;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import trcformatter.model.staticData.StaticData;

/**
 *
 * @author ESa10969
 */
public class VerifyTable {
  
  public static void verifyTable(Connection con) {
    //Variable definition
    DatabaseMetaData dbmeta;
    ResultSet rs;
    
    try {
      dbmeta = con.getMetaData();
      rs = dbmeta.getTables(null, null, null, new String[]{"TABLE"});
      
      while(rs.next()) {
        if(rs.getString("TABLE_NAME").toLowerCase().equals(StaticData.tableNam.toLowerCase())) {
          StaticData.tableExists = true;
          break;
        }
      }
      if(!StaticData.tableExists) {
        createTable(con);
      }
    } catch (SQLException ex) {
      Logger.getLogger(VerifyTable.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private static void createTable(Connection con) {
    //Variable definition
    Statement st;
    String qry = "CREATE TABLE " + StaticData.tableNam + " (FECHA VARCHAR(20) PRIMARY KEY NOT NULL, "
                                                       + "ALL_QUERIES BLOB)";
    System.out.println(qry);
    
    try {
      st = con.createStatement();
      //st.execute("DROP TABLE " + StaticData.tableNam);
      st.executeUpdate(qry);
    } catch (SQLException ex) {
      Logger.getLogger(VerifyTable.class.getName()).log(Level.SEVERE, null, ex);
    }

    
  }
}
