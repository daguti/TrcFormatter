/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.Persistance.DAO;

import java.util.ArrayList;
import javax.swing.JFrame;
import trcformatter.model.QueryInfoBean;

/**
 *
 * @author ESa10969
 */
public interface QueriesItfzDAO {
  public void storeData(ArrayList<QueryInfoBean> qrsList, JFrame qryTable);
  public void getRecordDates();
  public void getStoredData(String fecha, JFrame loading);
  public void delete(String fecha, JFrame load);
}
