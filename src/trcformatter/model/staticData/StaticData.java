/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trcformatter.model.staticData;

import java.util.ArrayList;
import trcformatter.model.QueryInfoBean;

/**
 *
 * @author ESA10969
 */
public class StaticData {    
    //List of retrieved queries
    public static ArrayList<QueryInfoBean> qryInfoLst;
    //Asked query detail info
    public static QueryInfoBean detInfo;
    //Retrieved start date
    public static String headerDate;
    public static String iniMilis;

    //Read line
    public static String line;
    
    public static int order;
    
    public static boolean tableExists = false;
    
    public static String tableNam = "TrcFormatterPersistance";
    
    public static String path;
    
    public static ArrayList<String> fechas;
}
