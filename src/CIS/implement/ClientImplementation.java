 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIS.implement;

import java.rmi.server.UnicastRemoteObject;
import CIS.Interface.Interface;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Withushan
 */

public class ClientImplementation extends UnicastRemoteObject implements Interface {
    
    
    public ClientImplementation() throws RemoteException {
        super();
        
        
    }
    
    private static final long serialVersionUID = -3763231206310559L;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    
    boolean r, r1, r2, r3, r4;
    
    //Connection of database
    public void databaseConnection() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis", "root", "");
            System.out.println("Connected Suceessfully");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public boolean signIn (String username, String password) throws RemoteException  {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
            
            
            String sql = "SELECT * from admin where username = ? and password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rst = pst.executeQuery();
           
             if(rst.next())
            {
                r = true;
            }
            else
            {
                r = false;
            }
            
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            
           r = false;
        }
        
        return r;
    }
    
    public boolean LogIn (String username, String password) throws RemoteException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
            
            String sql = "select * from user where username = ? and password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            r1 = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            
            r1= false;
        }
        
        return r1;
   
    } 
     public boolean InsertQuestion(String question_no,String question,String option_1,String option_2,String option_3,String option_4,String option_5) throws RemoteException
    {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
            
            String sql = "INSERT INTO question(QuestionNo, Question, Option1, Option2, Option3, Option4, Option5) VALUES (?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, question_no);
            pst.setString(2, question);
            pst.setString(3, option_1);
            pst.setString(4, option_2);
            pst.setString(5, option_3);
            pst.setString(6, option_4);
            pst.setString(7, option_5);
            pst.executeUpdate();
            r2 = true;
            
        } catch (ClassNotFoundException ex) {
                
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            
            r2 = false;
            
        }
        
        return r2;
    }
     
     
     public boolean UpdateQuestion(String question_no,String question,String option_1,String option_2,String option_3,String option_4,String option_5) throws RemoteException
    {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
            
            String sql = "UPDATE question SET question=?,option1=?,option2=?,option3=?,option4=?,option5=? WHERE questionNo=?";
            
            pst = con.prepareStatement(sql);
            
            pst.setString(1,question_no);
            pst.setString(2,question);
            pst.setString(3,option_1);
            pst.setString(4, option_2);
            pst.setString(5, option_3);
            pst.setString(6, option_4);
            pst.setString(7,option_5);
            pst.executeUpdate();
            r3 = true;
            
            
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            
            r3 = false;
            
        }
        
        return r3;
    }
    
     
     public boolean DeleteQuestion(String question_no)throws RemoteException{
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
            
            String sql = "DELETE FROM question WHERE questionNo=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, question_no);
            pst.executeUpdate();
            
            r4 = true;
            
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            
           r4 = false;
           
        }
        
        return r4;
    }
}
