/*
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * DBManager class
 */
public class DBManager {
    private Connection connection;
    private String host;
    private int port;
    private String instance;
    private String db;
    private String id;
    
    /**
     * Creates new DBManager
     * 
     * @throws ClassNotFoundException When JDBC has not registered
     */
    public DBManager() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        connection = null;
        host = null;
        port = 0;
        instance = null;
        db = null;
        id = null;
    }
    
    /**
     * Establish database connection
     * 
     * @param host Host address
     * @param port Port number
     * @param instance Instace name
     * @param database Database name
     * @param user User ID
     * @param pass User password
     * @throws SQLException If connection has not established
     */
    public void connect(String host, int port, String instance, String database, String user, String pass) throws SQLException {
        /* Set variables */
        this.instance = instance;
        this.host = host;
        this.port = port;
        db = database;
        id = user;
        
        /* Build url */
        String url = "jdbc:sqlserver://";
        url += host;
        url += ":" + String.valueOf(port);
        url += ";instanceName=" + instance;
        url += ";databaseName=" + database;
        url += ";integratedSecurity=false";
        url += ";applicationName=PriceUpdaer";
        
        /* Establish connection */
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    /**
     * Disconnect from database
     * 
     * @throws SQLException If cant not disconnect
     */
    public void disconnect() throws SQLException {
        if (connection != null)
            connection.close();
        
        connection = null;
    }
    
    /**
     * Disconnect from database
     * 
     * @return True if is connected, false otherwise
     * @throws SQLException If cant not disconnect
     */
    public boolean isConnected() throws SQLException {
        if (connection == null)
            return false;
        
        return !connection.isClosed();
    }
    
    /**
     * Execute query to data base and return table
     * 
     * @param query Query to executeQuery
     * @return ArticlesTable with result data
     * @throws SQLException If syntax or database connection error
     */
    public DefaultTableModel executeQuery(String query)throws SQLException {
        /* Statement and result objects */
        ResultSet result;
        Statement statement;
        
        /* No connected */
        if (connection == null || connection.isClosed()) {
            throw new SQLException();
        }
        
        
        /* Consult to database */
        statement = connection.createStatement();
        result = statement.executeQuery(query);
        
        
        /* Fill table */
        DefaultTableModel table = new ArticlesTable();
        int cols = result.getMetaData().getColumnCount();
        
        while (result.next()) {
            String[] row = new String[cols];
            for (int i = 0; i < cols; i++)
                row[i] = result.getString(i + 1);

            table.addRow(row);
        }
        
        /* Close statement and result */
        statement.close();
        result.close();
        
        return table;
    }
    
    /**
     * Execute query to data base and return table
     * 
     * @param query Query to executeQuery
     * @param table Table to be filled with the data
     * @throws SQLException If syntax or database connection error
     */
    public void executeQuery(String query, DefaultTableModel table)throws SQLException {
        /* Statement and result objects */
        ResultSet result;
        Statement statement;
        
        /* No connected */
        if (connection == null || connection.isClosed()) {
            throw new SQLException();
        }
        
        
        /* Consult to database */
        statement = connection.createStatement();
        result = statement.executeQuery(query);
        
        
        /* Fill table */
        int cols = result.getMetaData().getColumnCount();
        
        while (result.next()) {
            String[] row = new String[cols];
            for (int i = 0; i < cols; i++)
                row[i] = result.getString(i + 1);

            table.addRow(row);
        }
        
        /* Close statement and result */
        statement.close();
        result.close();
    }
    
    /**
     * Execute the given SQL statement
     * 
     * @param query SQL statement
     * @throws SQLException If syntax or database connection error
     */
    public void executeStatement(String query) throws SQLException {
        /* Statement and result objects */
        Statement statement;
        
        /* Consult to database */
        statement = connection.createStatement();
        statement.execute(query);
        
        /* Close statement and result */
        statement.close();
    }
    
    
    /* Getters */
    
    /**
     * Get host address
     * 
     * @return Host address
     */
    public String getHost() {
        return host;
    }
    
    /**
     * Get port number
     * 
     * @return Port number
     */
    public int getPort() {
        return port;
    }
    
    /**
     * Get instance name
     * 
     * @return Instance name
     */
    public String getInstance() {
        return instance;
    }
    
    /**
     * Get data base name
     * 
     * @return Data base name
     */
    public String getDB() {
        return db;
    }
    
    /**
     * Get id ID
     * 
     * @return User ID
     */
    public String getID() {
        return id;
    }
    
    
    
    /* Setters */
    
    /**
     * Set host address
     * 
     * @param host Host address
     */
    public void setHost(String host) {
        this.host = host;
    }
    
    /**
     * Set port number
     * 
     * @param port Port number
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * Get instance name
     * 
     * @param instance Instance name
     */
    public void setInstance(String instance) {
        this.instance = instance;
    }
    
    /**
     * Set data base name
     * 
     * @param database Data base name
     */
    public void setDB(String database) {
        this.db = database;
    }
    
    /**
     * Set id ID
     * 
     * @param user User ID
     */
    public void setID(String user) {
        id = user;
    }
}
