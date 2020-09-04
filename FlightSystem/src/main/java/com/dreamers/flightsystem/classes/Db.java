/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamers.flightsystem.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orbius
 */
public class Db {

    public Connection connectDB() {
        Connection c = null;

        try {
            c = Pool.getInstance().getConnection();

            if (c != null) {
                System.out.println("You are connected to the database.");
            } else {
                System.out.println("You are not connected.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    public int insertUser(User user) {
        // INSERT, UPDATE OR DELETE CAN BE -1 OR 0
        int ok = -1;

        try {
            // Get a connection from the Connection Pool
            Connection c = Pool.getInstance().getConnection();

            String query = "INSERT INTO users(name, last_name, country, age, phone_number)" + " values(?, ?, ?, ?, ?)";
            // Preparing the statement.
            PreparedStatement pstmt = c.prepareStatement(query);

            // Insert values in Db
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getCountry());
            pstmt.setInt(4, user.getAge());
            pstmt.setInt(5, user.getPhone());
            

            ok = pstmt.executeUpdate(); // 0 if returns nothing and row if returns something

            c.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return ok;

    }
    
    public boolean userExists(int id){
        boolean exist = true;
        int db_id = 0;
        ResultSet rs;
        
        try{
            // Connect to database
            Connection c = Pool.getInstance().getConnection();
            String query = "SELECT id FROM users where id = ?";
            
            // Prepare the statement
            PreparedStatement pstmt = c.prepareStatement(query);
            
            // Put the user input as parameter for query
            pstmt.setInt(1, id);
            
            // Execute the query and save it in a result set variable type
            rs = pstmt.executeQuery();
            
            // While there is data, search id and save it in a variable
            while(rs.next()){
                db_id = rs.getInt(1);
            }
            
            // Check if user exists
            if(db_id == id){
                exist = true;
                c.close();
            }else{
                exist = false;
                c.close();
            }        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return exist;
        
    }
    
    public int deleteUser(int id){
        int deleted = -1;
     
        try{
            // Connect to database
            Connection c = Pool.getInstance().getConnection();
            // Create query
            String query = "DELETE FROM users WHERE id = ?";
            // Prepare the query
            PreparedStatement pstmt = c.prepareStatement(query);
            // User's input in query
            pstmt.setInt(1, id);
            // Execute the statement
            deleted = pstmt.executeUpdate();
            // Close connection
            c.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return deleted;
    }
    
    public User getUserDB(int id){
        User user = null;
        String name = "", last_name = "", country = "";
        int phone = 0, age = 0, db_id = 0;
        Connection c;
        ResultSet rs;
        
        try{
            // Connect to DB
            c = Pool.getInstance().getConnection();
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                db_id = rs.getInt(1);
                name = rs.getString(2);
                last_name = rs.getString(3);
                country = rs.getString(4);
                age = rs.getInt(5);
                phone = rs.getInt(6);
                
                user = new User(db_id, name, last_name, country, age, phone);
            }
            
            
            
            c.close();
            
            return user;
                   
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return user;
        
    }

    public void closeDB(Connection db) {
        


    }

}
