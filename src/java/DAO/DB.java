/*
 * Purpose: Establish DB connection
 * Description: This file stores the DB information and establish the connection
 * Date:14 July 2013
 * Author: Huimin
 * 
 */
package DAO;

import java.sql.*;

public class DB {

    private static Connection conn;
    private static Statement statement;
    private static DB db;
    private static final String HOST ="jdbc:mysql://localhost/db";
    private static final String USER ="root";
    private static final String PASSWORD ="123456";
    
    //server password
//    private static final String HOST ="jdbc:mysql://localhost/huimin";
//    private static final String USER ="huimin";
//    private static final String PASSWORD ="c2i@12345";
//    
    protected DB(){
        //conn = dbConnect(HOST,USER,PASSWORD);
        try{
            if (db!= null)
                dbClose();
            
            conn = dbConnect(HOST,USER,PASSWORD);
            statement = conn.createStatement();
            
        }
        catch(Exception e){
            System.out.println("Error : " + e.toString());
        }
    }
    
    private static Connection dbConnect(String db_Host, String db_User, String db_Password)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_Host, db_User,db_Password);
            System.out.println("Connected to DB");
            return conn; 
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static DB getInstance(){
        if (db == null)
            db = new DB();
       
        return db;
    }
    
    public Statement getStatement(){
        return statement;
    }
    
    public void dbClose(){
        try{
            if (conn!=null)
                conn.close();
            if(statement!=null)
                statement.close();
            db = null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

