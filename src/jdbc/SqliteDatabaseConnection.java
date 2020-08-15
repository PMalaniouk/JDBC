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
 * @since Feb 17, 2020 3:14:59 PM
 *
 */

/**              MODIFICATION LOG

   Feb 17, 2020 Initial file creation at 3:14:59 PM

*/


public class SqliteDatabaseConnection extends DatabaseConnection 
{

    @Override
    public Connection connectToDB(String strDriverName, 
                                  String strUserName, 
                                  String strPassword,
                                  String strDatabaseLocation)
       throws ClassNotFoundException, SQLException        
    {
       Connection cReturnedConnection = null;
//       Class.forName(strDriverName);
       
       String strConnection = "jdbc:sqlite:" + strDatabaseLocation;
       cReturnedConnection = DriverManager.getConnection(strConnection);
       
       return cReturnedConnection; 
    }// connectToDB

}// class SqliteDatabaseConnection 