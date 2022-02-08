/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIS.rmi.client;

import CIS.Interface.Interface;
import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Withushan
 */
public class AdminQuestionPanel extends javax.swing.JFrame {

    /**
     * Creates new form AdminQuestionPanel
     */
    
    //These are the varriables
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    int Count;
    String question_no;
    
    
    public AdminQuestionPanel() {
        initComponents();
        // Methods calling
        databaseConnection();
        DisplayQuestionNo();
        DisplayTableDetails();
        
    }

    
    public void databaseConnection()
    {
        try {
            //This is a comment will load the mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //This is a comment to setup the connection of the database
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(AdminQuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(AdminQuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /****
     * This method represents the question number that will be added next to the question set
     */
    public void DisplayQuestionNo()
    {
        try {
            
            //Get a result set of question no from question table database
            Statement state = con.createStatement();
            rst = state.executeQuery("SELECT MAX(QuestionNo) from question");
            rst.next();
            //If condition for the code displaying the question number
            rst.getString("MAX(QuestionNo)");
            
            if(rst.getString("MAX(QuestionNo)") == null)
            {
                jTextQuestionNo.setText("1");
            }
            else
            {
                Count = Integer.parseInt(rst.getString("MAX(QuestionNo)")) + 1;
                question_no = Integer.toString(Count);
                jTextQuestionNo.setText(question_no);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void DisplayTableDetails()
    {
        //Declaring varriable column
        int column;
        
        try {
            //Get a result set containing all the data from the question table
            pst = con.prepareStatement("Select * from question");
            rst = pst.executeQuery();
            
            ResultSetMetaData rsm = rst.getMetaData();
            
            column = rsm.getColumnCount();
            DefaultTableModel tblModel = (DefaultTableModel)QuestionTable.getModel();
            tblModel.setRowCount(0);
            
            while(rst.next())
            {
                Vector t = new Vector();
                
                for(int i = 0; i<=column; i++)
                {
                    t.add(rst.getString("QuestionNo"));
                    t.add(rst.getString("Question"));
                    t.add(rst.getString("Option1"));
                    t.add(rst.getString("Option2"));
                    t.add(rst.getString("Option3"));
                    t.add(rst.getString("Option4"));
                    t.add(rst.getString("Option5"));
                    
                    
                }       
                tblModel.addRow(t);
                QuestionTable.getColumnModel().getColumn(0).setPreferredWidth(10);
                QuestionTable.getColumnModel().getColumn(1).setPreferredWidth(300);
                QuestionTable.getColumnModel().getColumn(2).setPreferredWidth(40);
                QuestionTable.getColumnModel().getColumn(3).setPreferredWidth(40);
                QuestionTable.getColumnModel().getColumn(4).setPreferredWidth(40);
                QuestionTable.getColumnModel().getColumn(5).setPreferredWidth(40);
                QuestionTable.getColumnModel().getColumn(6).setPreferredWidth(40);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//    
// 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextQuestionNo = new javax.swing.JTextField();
        jTextQuestion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextOption1 = new javax.swing.JTextField();
        jTextOption2 = new javax.swing.JTextField();
        jTextOption3 = new javax.swing.JTextField();
        jTextOption4 = new javax.swing.JTextField();
        jTextOption5 = new javax.swing.JTextField();
        jButtonInsert = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        QuestionTable = new javax.swing.JTable();
        jButtonback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Admin_Question.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Question Panel");

        jLabel_minimize.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_minimize.setText("-");
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseExited(evt);
            }
        });

        jLabel_close.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_close.setText("X");
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_minimize)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_close)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_close)
                            .addComponent(jLabel_minimize))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Question No:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Question:");

        jTextQuestionNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextQuestionNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextQuestionNoActionPerformed(evt);
            }
        });

        jTextQuestion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Option 1:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Option 2:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Option 3:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Option 4:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Option 5:");

        jTextOption1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextOption2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextOption3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextOption4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextOption4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextOption4ActionPerformed(evt);
            }
        });

        jTextOption5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButtonInsert.setBackground(new java.awt.Color(204, 255, 204));
        jButtonInsert.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonInsert.setText("Insert");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(204, 255, 204));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(204, 255, 204));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonRefresh.setBackground(new java.awt.Color(204, 255, 204));
        jButtonRefresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        QuestionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        QuestionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuestionTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(QuestionTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButtonback.setBackground(new java.awt.Color(204, 255, 255));
        jButtonback.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonback.setText("Back");
        jButtonback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextOption4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                    .addComponent(jTextOption3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextOption2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextOption1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextQuestion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextOption5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextQuestionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButtonInsert)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonback)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextQuestionNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextOption2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextOption3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextOption4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextOption5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsert)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh)
                    .addComponent(jButtonback))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextOption4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextOption4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOption4ActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        
        //This boolean variable is declared and initialized to false
        boolean insert = false;
        //getting the values
        String No = jTextQuestionNo.getText();
        String Question = jTextQuestion.getText();
        String Option1 = jTextOption1.getText();
        String Option2 = jTextOption2.getText();
        String Option3 = jTextOption3.getText();
        String Option4 = jTextOption4.getText();
        String Option5 = jTextOption5.getText();
        
        try{
            //getting registry
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            Interface a = (Interface)reg.lookup("Server");
            
            insert = a.InsertQuestion(No, Question, Option1, Option2, Option3, Option4,Option5);
            
            if(insert == true)
            {
                //This Popup message will help to admin for inserting the data  
                int responseInsert = JOptionPane.showConfirmDialog(this, "Do you want to continue action ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // If yes, the data will be added to the database 
                if(responseInsert == JOptionPane.YES_OPTION){
                    
                   JOptionPane.showMessageDialog(this, "Data Inserted Successfully","Alert",JOptionPane.WARNING_MESSAGE);
                   DisplayTableDetails();
                   DisplayQuestionNo();
                }
                else{
                    //If no, the data won't be added to the database
                    JOptionPane.showMessageDialog(this, "Data Insertion Cancelled!");
                }
                jTextQuestionNo.setText("");
                jTextQuestion.setText("");
                jTextOption1.setText("");
                jTextOption2.setText("");
                jTextOption3.setText("");
                jTextOption4.setText("");
                jTextOption5.setText("");
            }
            else{
                JOptionPane.showMessageDialog(this, "Cannot Insert Data!","Failure",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error! "+ex,"Bug!",JOptionPane.ERROR_MESSAGE);
        }               
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        boolean update = false;
        //updating the values
        String No = jTextQuestionNo.getText();
        String Question = jTextQuestion.getText();
        String Option1 = jTextOption1.getText();
        String Option2 = jTextOption2.getText();
        String Option3 = jTextOption3.getText();
        String Option4 = jTextOption4.getText();
        String Option5 = jTextOption5.getText();
        
        try{
            
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            Interface a = (Interface)reg.lookup("Server");
            
            update = a.UpdateQuestion(No, Question, Option1, Option2, Option3, Option4, Option5);
            if(update == true)
            {
                int responseUpdate = JOptionPane.showConfirmDialog(this, "Do you want to continue action ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(responseUpdate == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(this, "Data Updated Successfully","Alert",JOptionPane.WARNING_MESSAGE);
                    DisplayTableDetails();
                    DisplayQuestionNo();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Data Updation Cancelled!");
                }
                jTextQuestionNo.setText("");
                jTextQuestion.setText("");
                jTextOption1.setText("");
                jTextOption2.setText("");
                jTextOption3.setText("");
                jTextOption4.setText("");
                jTextOption5.setText("");
                jButtonInsert.setEnabled(true);
                jTextQuestionNo.setEditable(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Cannot Update Data!","Failure",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error!, "+ex,"Bug!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        boolean delete = false;
        
        String no = jTextQuestionNo.getText();
        
        try{
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            Interface a = (Interface)reg.lookup("Server");
            
            delete = a.DeleteQuestion(no);
            
            if(delete == true)
            {
                int responseUpdate = JOptionPane.showConfirmDialog(this, "Do you want to continue action ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if(responseUpdate == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(this, "Data Updated Successfully","Alert",JOptionPane.WARNING_MESSAGE);
                    DisplayTableDetails();
                    DisplayQuestionNo();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Data Updation Cancelled!");
                }
                jTextQuestionNo.setText("");
                jTextQuestion.setText("");
                jTextOption1.setText("");
                jTextOption2.setText("");
                jTextOption3.setText("");
                jTextOption4.setText("");
                jTextOption5.setText("");
                jButtonInsert.setEnabled(true);
                jTextQuestionNo.setEditable(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error!, "+ex,"Bug!",JOptionPane.ERROR_MESSAGE);
        }                
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        // TODO add your handling code here:
        //refreshing the values
        jTextQuestionNo.setText("");
        jTextQuestion.setText("");
        jTextOption1.setText("");
        jTextOption2.setText("");
        jTextOption3.setText("");
        jTextOption4.setText("");
        jTextOption5.setText("");
        jButtonInsert.setEnabled(true);
        jTextQuestionNo.setEditable(true);
        jTextQuestionNo.requestFocus();
        
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        AdminPanelGUI adp1 = new AdminPanelGUI();
        adp1.setVisible(true);
    }//GEN-LAST:event_jButtonbackActionPerformed

    private void jTextQuestionNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextQuestionNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextQuestionNoActionPerformed

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1,1,1,1, Color.white);
        jLabel_minimize.setForeground(Color.white);
        jLabel_minimize.setBorder(label_border);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        jLabel_minimize.setBorder(label_border);
        jLabel_minimize.setForeground(Color.black);
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1,1,1,1, Color.white);
        jLabel_close.setForeground(Color.white);
        jLabel_close.setBorder(label_border);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        jLabel_minimize.setBorder(label_border);
        jLabel_minimize.setForeground(Color.black);
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void QuestionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuestionTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tblModelClicked = (DefaultTableModel) QuestionTable.getModel();

        jButtonInsert.setEnabled(false);
        jTextQuestionNo.setEditable(false);

        String table_No = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 0).toString();
        String table_Question = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 1).toString();
        String table_Option1 = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 2).toString();
        String table_Option2 = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 3).toString();
        String table_Option3 = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 4).toString();
        String table_Option4 = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 5).toString();
        String table_Option5 = tblModelClicked.getValueAt(QuestionTable.getSelectedRow(), 6).toString();

        jTextQuestionNo.setText(table_No);
        jTextQuestion.setText(table_Question);
        jTextOption1.setText(table_Option1);
        jTextOption2.setText(table_Option2);
        jTextOption3.setText(table_Option3);
        jTextOption4.setText(table_Option4);
        jTextOption5.setText(table_Option5);
    }//GEN-LAST:event_QuestionTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminQuestionPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable QuestionTable;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextOption1;
    private javax.swing.JTextField jTextOption2;
    private javax.swing.JTextField jTextOption3;
    private javax.swing.JTextField jTextOption4;
    private javax.swing.JTextField jTextOption5;
    private javax.swing.JTextField jTextQuestion;
    private javax.swing.JTextField jTextQuestionNo;
    // End of variables declaration//GEN-END:variables
}
