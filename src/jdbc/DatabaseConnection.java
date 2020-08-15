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
 * @since Feb 17, 2020 3:09:49 PM
 *
 */

/**              MODIFICATION LOG

   Feb 17, 2020 Initial file creation at 3:09:49 PM

*/


public abstract class DatabaseConnection 
{

// SQL Statements    
public static final String strSQLGETAllAUTHOR = "Select count(isbn) as 'Paul Dietal' from authorisbn where authorid = 2";
public static final String strSQLGETALLCOPYRIGHT = "Select Copyright, Count(Copyright) from Titles Group by Copyright";    
public static final String strSQLGETJAVAPRICE = "Select * from Titles";
public static final String strSQLGETNEWINFO = "Insert into Titles(isbn, title, editionNumber, copyright, publisherID,imagefile) "
        + "                                     Values ('9780786838653', 'The Lightning Thief', '1', '2006', '1', 'jh.jpg','200.95')";
private Connection myConnection;



public abstract Connection connectToDB(String strDriverName,
           String strUserName, String strPassword,String strDatabaseLocation)
            throws ClassNotFoundException, SQLException; 


public void closeConnection()
{
    try {
        getMyConnection().close();
    } catch (SQLException ex) {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public Connection connectAndSave(String strDriverName,
           String strUserName, String strPassword,String strDatabaseLocation) throws ClassNotFoundException, SQLException
{
   setMyConnection(connectToDB(strDriverName,
                               strUserName, 
                               strPassword,
                               strDatabaseLocation));    
      return getMyConnection();
}// connectAndSave        

    
 /*   
public final ResultSet getAllAuthor()
{
   ResultSet rsReturn = null;
    try 
    {
        Connection cTemp = getMyConnection();
        if (cTemp != null)
        {
        Statement stmtGetAllAuthor = cTemp.createStatement();
        rsReturn = stmtGetAllAuthor.executeQuery(strSQLGETAllAUTHOR);
        
        
        }
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }

    return rsReturn;
        
}// getAllAuthors

public final ResultSet getAllCopyright()
{
   ResultSet rsReturn = null;
    try 
    {
        Connection cTemp = getMyConnection();
        if (cTemp != null)
        {
        Statement stmtGetAllCopyright = cTemp.createStatement();
        rsReturn = stmtGetAllCopyright.executeQuery(strSQLGETALLCOPYRIGHT);
        
        
        }
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }

    return rsReturn;
        
}// getAllcopyright count
*/

public final ResultSet getAllJavaPrice()
{
   ResultSet rsReturn = null;
    try 
    {
        Connection cTemp = getMyConnection();
        if (cTemp != null)
        {
        Statement stmtGetAllJavaPrice = cTemp.createStatement();
        rsReturn = stmtGetAllJavaPrice.executeQuery(strSQLGETJAVAPRICE);
        
        
        }
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }

    return rsReturn;
        
}// getpriceupdate
public final ResultSet getAllNewInfo()
{
   ResultSet rsReturn = null;
    try 
    {
        Connection cTemp = getMyConnection();
        if (cTemp != null)
        {
        Statement stmtGetAllCopyright = cTemp.createStatement();
        rsReturn = stmtGetAllCopyright.executeQuery(strSQLGETNEWINFO);
        
        
        }
    } 
    catch (SQLException ex) 
    {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }

    return rsReturn;
        
}













public String getConnectionMetaData() throws SQLException   
{
    Connection cnTemp = getMyConnection();
   StringBuilder sbOut = new StringBuilder();
   DatabaseMetaData dmdTemp = cnTemp.getMetaData();
    sbOut.append("Database MetaData:\n");
   sbOut.append("Product Name = " + dmdTemp.getDatabaseProductName() + "\n");
   sbOut.append("Product Version = " + dmdTemp.getDatabaseProductVersion());
   return sbOut.toString();
} // getConnectionMetaData

    /**
     * @return the myConnection
     */
    public Connection getMyConnection() {
        return myConnection;
    }

    /**
     * @param myConnection the myConnection to set
     */
    public void setMyConnection(Connection myConnection) {
        this.myConnection = myConnection;
    }
    
   
    public String getResultSetMetaData(ResultSet rsIn)
    {
       if (rsIn != null)
       {
        StringBuilder sbReturn = new StringBuilder();
        ResultSetMetaData rsMD;
        
    try 
    {
        rsMD = rsIn.getMetaData();
        if(rsMD != null)
        {
          
 sbReturn.append("The ResultSet has " +  rsMD.getColumnCount() + " columns:\n");        
 
 
       int intNumCols = rsMD.getColumnCount();
        
        for (int nLCV = 1; nLCV <= intNumCols; nLCV++)
        {
            // TODO: Add code to display column information
           sbReturn.append("Class Name - " + rsMD.getColumnClassName(nLCV));
           sbReturn.append("\n");
           sbReturn.append("Column Label - " + rsMD.getColumnLabel(nLCV));
           sbReturn.append("\n");           
           sbReturn.append("Column Name - " + rsMD.getColumnName(nLCV));
           sbReturn.append("\n");           
           sbReturn.append("Column Type - " + rsMD.getColumnType(nLCV));
           sbReturn.append("\n");           
           sbReturn.append("Column Type Name - " + rsMD.getColumnTypeName(nLCV));
           sbReturn.append("\n\n");
        } // for
        
        
        while(rsIn.next() == true)
        {
            for (int nLCV = 1; nLCV <= intNumCols; nLCV++)
        {
            switch(rsMD.getColumnType(nLCV))            {
                case 4: 
                   sbReturn.append(rsMD.getColumnLabel(nLCV) +
                            " = " + rsIn.getInt(nLCV));
                              sbReturn.append("\n");
                    break;
                
                case 12: 
                   sbReturn.append(rsMD.getColumnLabel(nLCV) +
                            " = " + rsIn.getString(nLCV));
                              sbReturn.append("\n");
                    break;
                     
                default:
                   sbReturn.append("Column " + nLCV + "type number is " + rsMD.getColumnType(nLCV));
                              sbReturn.append("\n"); 
                    
                
            } // switch
            
        } // for
        
           sbReturn.append("\n\n");
        } // while
 
        }// if rsMD is not null
    } catch (SQLException ex) 
    {
        sbReturn.append(ex.getLocalizedMessage());
        // Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
        
       
        return sbReturn.toString();
       }
       return "";
    }// getResultSetMetaData
}// class DatabaseConnection 