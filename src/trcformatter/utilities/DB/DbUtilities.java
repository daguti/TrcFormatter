/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.utilities.DB;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import trcformatter.model.QueryInfoBean;

/**
 *
 * @author ESa10969
 */
public class DbUtilities {
  
  public static byte[] objectToBlob(ArrayList<QueryInfoBean> obj) throws IOException {
    //Variable definition
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    ObjectOutputStream dout = new ObjectOutputStream(bout);
    
    dout.writeObject(obj);
    dout.close();
    
    byte[] asBytes = bout.toByteArray();
    
    return asBytes;
  }
  
  public static ArrayList<QueryInfoBean> blobToObject(byte[] byteArray) {
    //Variable definition
    ByteArrayInputStream baip = new ByteArrayInputStream(byteArray);
    ObjectInputStream ois;
    ArrayList<QueryInfoBean> qryList = null;

    try {
      ois = new ObjectInputStream(baip);
      qryList = (ArrayList<QueryInfoBean>) ois.readObject();
    } catch (IOException ex) {
      Logger.getLogger(DbUtilities.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(DbUtilities.class.getName()).log(Level.SEVERE, null, ex);
    }
    return qryList;
  }
}
