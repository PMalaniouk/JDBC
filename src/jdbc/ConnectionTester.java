/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JDBC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author whb108
 * @version 0.1
 * @since Feb 17, 2020 3:32:47 PM
 *
 */

/**              MODIFICATION LOG

   Feb 17, 2020 Initial file creation at 3:32:47 PM

*/


public class ConnectionTester 
{
   public Connection myC;
   public DatabaseConnection myDC;
   
   public static final String strDatabaseLocation = "C:\\Users\\Peter\\Documents\\NetBeansProjects\\JDBC\\Books.db3";
   
   public void connectToSQLite() throws ClassNotFoundException, SQLException
   {
      myDC = new SqliteDatabaseConnection();
      myDC.connectAndSave("", "", "", strDatabaseLocation);      
   }// connectToSQLite
   
   public String getDBMetaData()
   {
      StringBuilder sbTemp = new StringBuilder();    
          
      sbTemp.append("Connected to " + myDC.getMyConnection() + "\n");
     
       try {
           sbTemp.append(myDC.getConnectionMetaData());
       } 
       catch (SQLException ex) 
       {
           sbTemp.append("Error getting database metadata:  " + ex.getLocalizedMessage());
           Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
       }
      
      return sbTemp.toString();
   }//getDBMetaData
    public static void main(String[] args) 
    {
        
       ConnectionTester myCT = new ConnectionTester();
       try 
       {
       myCT.connectToSQLite();
        System.out.println(myCT.getDBMetaData());
       //   myCT.myDC = new MySQLDatabaseConnection();

          
          // myCT.myC = myCT.myDC.connectToDB("", "", "", "127.0.0.1");
        
           
           //ResultSet rsAllAuthor = myCT.myDC.getAllAuthor();
           //ResultSet rsCopyright = myCT.myDC.getAllCopyright();
           //ResultSet rsPriceUpdate = myCT.myDC.getAllJavaPrice();
           ResultSet rsNewInfo = myCT.myDC.getAllNewInfo();
           //System.out.println(myCT.myDC.getResultSetMetaData(rsAllAuthor));
           //System.out.println(myCT.myDC.getResultSetMetaData(rsCopyright));
           //System.out.println(myCT.myDC.getResultSetMetaData(rsPriceUpdate));
           System.out.println(myCT.myDC.getResultSetMetaData(rsNewInfo));
           
           myCT.myDC.closeConnection();
       } 
       catch (ClassNotFoundException ex) {
           Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}// class ConnectionTester 