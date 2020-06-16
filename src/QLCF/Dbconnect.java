/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import QLCF.*;


/**
 *
 * @author HP
 */
public class Dbconnect {
    public Connection getConnection(){
    Connection conn = null;
        
   try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = TheCoffee;user=sa;password=12345");
           if(conn!=null){
               System.out.println("ket noi thanh cong");
           }
  }     catch (Exception ex) {
           System.out.println(ex.toString());
        }
   return conn;
    }
  
}

