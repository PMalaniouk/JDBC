/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JDBC;

import java.sql.*;


/**
 *
 * @author whb108
 * @version 0.1
 * @since Feb 17, 2020 4:01:41 PM
 *
 */

/**              MODIFICATION LOG

   Feb 17, 2020 Initial file creation at 4:01:41 PM

*/


public class MySQLDatabaseConnection extends DatabaseConnection
{
   public static final String strDBDriver = "com.mysql.jdbc.Driver";
   public static final String strPort = "4300";
    @Override
    public Connection connectToDB(String strDriverName, 
            String strUserName, String strPassword, 
            String strDatabaseLocation) 
       throws ClassNotFoundException, SQLException 
    {
       Class.forName(strDBDriver);   
       String strConnection = "jdbc:mysql://" 
          + strDatabaseLocation + ":" + strPort;
       
       Connection cnTemp = 
          DriverManager.getConnection(strConnection, strUserName, strPassword);
       
       return cnTemp;
    } // connectToDB

}// class MySQLDatabaseConnection 