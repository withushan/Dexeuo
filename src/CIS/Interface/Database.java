/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIS.Interface;

import CIS.implement.ClientImplementation;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;


/**
 *
 * @author Withushan
 */
public class Database extends UnicastRemoteObject implements Interface {
    
    boolean a,b,c,d,e;
    
    public Database() throws RemoteException {
        super();
        
    }
    
    @Override 
    public boolean signIn (String username, String password) throws RemoteException {
        try{
            ClientImplementation impl = new ClientImplementation ();
            impl.databaseConnection();
            
            if(signIn(username, password))
            {
                a = true;
                
            }
            else
            {
                a = false;
            }
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, ""+ex);
        }
        return a;
    }
    
     
    @Override
     public boolean InsertQuestion(String question_no, String question, String option_1, String option_2, String option_3, String option_4, String option_5) throws RemoteException {
        
        ClientImplementation impl = new ClientImplementation();
        impl.databaseConnection();
        
        try{
            if(InsertQuestion(question_no, question, option_1, option_2, option_3, option_4, option_5))
            {
                b = true;
            }
            else{
                b = false;
            }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        return b;
        }
     
     @Override
    public boolean UpdateQuestion(String question_no, String question, String option_1, String option_2, String option_3, String option_4, String option_5) throws RemoteException {
        
        ClientImplementation impl = new ClientImplementation();
        impl.databaseConnection();
        
        try{
            if(UpdateQuestion(question_no, question, option_1, option_2, option_3, option_4, option_5))
            {
                c = true;
            }
            else
            {
                c = false;
            }
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return c;
    }
    @Override
    public boolean DeleteQuestion(String question_no) throws RemoteException {
        
        ClientImplementation impl = new ClientImplementation();
        impl.databaseConnection();
        
        try{
            if(DeleteQuestion(question_no))
            {
                d = true;
            }
            else
            {
                d = false;
            }
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return d;
    }
    
    @Override
     public boolean LogIn (String username, String password) throws RemoteException {
        try{
            ClientImplementation impl = new ClientImplementation ();
            impl.databaseConnection();
            
            if(LogIn(username, password))
            {
                e = true;
                
            }
            else
            {
                e = false;
            }
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, ""+ex);
        }
        return e;
    }
}
