    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIS.rmi.client;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;



/**
 * This class constitute the questions
 * It will appear the questions and answers
 * Answers of the questions will be stored in the database
 * 
 * @author Withushan
 */

public class QuestionsGUI extends javax.swing.JFrame {
    /**
     * Creates new form Questions
     */
    public QuestionsGUI() {
        initComponents();
        //Methods calling
        databaseConnection();
        AutomaticIncrementUserId();
        DisplayQuestions();
    }
    
    //global variables used in this class
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    ResultSet rst1;
    int Count = 1;
    int User_Id;
    String p;
    
    
    /**
     * This method without a parameter
     * This method is used to connect the database to this class
     */
    public void databaseConnection()
    {
        try{
            /* The suspect that this block of statement can throw 
             * exception so we handled it by placing these statements
             * inside try and handled the exception in catch block
             */
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/cis","root","");
        } catch (ClassNotFoundException ex)
        {
            
            /* This block will only execute if any ClassNotFound exception 
             * occurs in try block
            */
            Logger.getLogger(QuestionsGUI.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (SQLException ex)
        {
            
            /* This block will only execute if any SQL exception 
             * occurs in try block
            */
            Logger.getLogger(QuestionsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    /**
     * This method without a parameter
     * This method is used to appear the id of the student
     */
    public void AutomaticIncrementUserId()
    {
        try{
            /* The suspect that this block of statement can throw 
             * exception so we handled it by placing these statements
             * inside try and handled the exception in catch block
             */
            
            Statement state = con.createStatement();
            rst = state.executeQuery("SELECT MAX(User_Id) FROM answers");
            rst.next();
            rst.getString("MAX(User_Id)");
            
            if(rst.getString("MAX(User_Id)") == null)
            {
                jLabelUser_Id.setText("1");
            }
            else
            {
               User_Id = Integer.parseInt(rst.getString("MAX(User_Id)")) + 1;
               p = Integer.toString(User_Id);
               jLabelUser_Id.setText(p);
            }
            
        } catch (SQLException ex) {
            /* This block will only execute if any SQL exception 
             * occurs in try block
            */
            Logger.getLogger(QuestionsGUI.class.getName()).log(Level.SEVERE, null, ex);         
        }
    }
    
    /**
     * This method without a parameter
     * This method is used for each question appear in the display from database
    */
    
    public void DisplayQuestions()
    {
        try{
            String sql = "SELECT * FROM question WHERE QuestionNo=" + Count;
            Statement state = null;
            state = con.createStatement();
            rst = state.executeQuery(sql);
            rst.next();
            jLabelQuestion.setText(rst.getString(2));
            jRadioButton1.setText(rst.getString(3));
            jRadioButton2.setText(rst.getString(4));
            jRadioButton3.setText(rst.getString(5));
            jRadioButton4.setText(rst.getString(6));
            jRadioButton5.setText(rst.getString(7));
            
            Statement state1 = con.createStatement();
            rst1 = state1.executeQuery("SELECT MAX(QuestionNo) from question");
            rst1.next();
            int x = Integer.parseInt(rst1.getString("MAX(QuestionNo)"));
            String maxqno = Integer.toString(x);
            jLabelNo.setText(rst.getString(1)+" of "+maxqno);
            
            
            
        } catch (SQLException ex) {
            /* This block will only execute if any SQL exception 
             * occurs in try block
            */
            Logger.getLogger(QuestionsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method without a parameter
     * This method is used to get the answers from each question and stored in the database
     */
    
    public void getResponses()
    {
        try{
            String answer  = null;
            
            int qno = Integer.parseInt(rst.getString(1));
            String y = Integer.toString(qno);
            jLabelNo.setText(y);
            jLabelQuestion.setText(rst.getString(2));
            jRadioButton1.setText(rst.getString(3));
            jRadioButton2.setText(rst.getString(4));
            jRadioButton3.setText(rst.getString(5));
            jRadioButton4.setText(rst.getString(6));
            jRadioButton5.setText(rst.getString(7));
            
            if(jRadioButton1.isSelected())
            {
                answer = rst.getString(3);
            }
            
            if(jRadioButton2.isSelected())
            {
                answer = rst.getString(4);
            }
            
            if(jRadioButton3.isSelected())
            {
                answer = rst.getString(5);
            }
            
            if(jRadioButton4.isSelected())
            {
                answer = rst.getString(6);
            }
            if(jRadioButton5.isSelected())
            {
                answer = rst.getString(7);
            }

            
            String p = jLabelUser_Id.getText();
            int b = Integer.parseInt(p);
            
            String sql = "INSERT INTO answers(User_Id, Question_No, answers) VALUES (?,?,?)";
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, b);
            pst.setInt(2, qno);
            pst.setString(3, answer);
            pst.executeUpdate();
            
            
            
        } catch (Exception ex) {
            System.out.println("An error occured: "+ex.toString()); 
	    ex.printStackTrace();
           
        }
        buttonGroup1.clearSelection();
    }
   
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelUser_Id = new javax.swing.JLabel();
        jLabelNo = new javax.swing.JLabel();
        jLabelQuestion = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jBtnNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Welcome");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/student.png"))); // NOI18N

        jLabel_minimize.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        jLabel_close.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("ID No:");

        jLabelUser_Id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelUser_Id.setText("ID_No");

        jLabelNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelNo.setText("No");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(323, 323, 323)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUser_Id)
                        .addGap(217, 217, 217)
                        .addComponent(jLabelNo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_minimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_close)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_minimize)
                            .addComponent(jLabel_close))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabelUser_Id)
                            .addComponent(jLabelNo))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabelQuestion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQuestion.setText("Questions Demo");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setText("Option1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setText("Option2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton3.setText("Option3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton4.setText("Option4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton5.setText("Option5");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jBtnNext.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBtnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Next1.png"))); // NOI18N
        jBtnNext.setText("Next");
        jBtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabelQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addGap(37, 37, 37)
                .addComponent(jRadioButton2)
                .addGap(35, 35, 35)
                .addComponent(jRadioButton3)
                .addGap(33, 33, 33)
                .addComponent(jRadioButton4)
                .addGap(33, 33, 33)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jBtnNext)
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // If you select option1 the next button will be enabled
        jBtnNext.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jBtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNextActionPerformed
        // Get answers method is calling for save the answers in the database
         getResponses();
        Count++;
        if(Count <=10)
        {
            DisplayQuestions();
        }
        if(Count == 10)
        {
            jBtnNext.setText("Finish");
        }
        if (Count >10)
        {
            this.setVisible(false);
            ThankYouGUI thankyou = new ThankYouGUI();
            thankyou.setVisible(true);
        }
    }//GEN-LAST:event_jBtnNextActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // If you select option3 the next button will be enabled
        jBtnNext.setEnabled(true);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // If you select option2 the next button will be enabled
        jBtnNext.setEnabled(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // If you select option2 the next button will be enabled
        jBtnNext.setEnabled(true);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // If you select option5 the next button will be enabled
        jBtnNext.setEnabled(true);
    }//GEN-LAST:event_jRadioButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(QuestionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuestionsGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelNo;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelUser_Id;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    // End of variables declaration//GEN-END:variables
}
