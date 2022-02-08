/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIS.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Withushan
 */
public interface Interface  extends Remote {
   
    public boolean signIn (String username, String password) throws RemoteException;
    public boolean LogIn (String username, String password) throws RemoteException ;
    public boolean InsertQuestion(String qno,String question,String opt1,String opt2,String opt3,String opt4,String opt5) throws RemoteException;
    public boolean UpdateQuestion(String qno,String question,String opt1,String opt2,String opt3,String opt4,String opt5) throws RemoteException;
    public boolean DeleteQuestion(String qno)throws RemoteException;
}
