/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import QLCF.MainCoffe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.omg.PortableInterceptor.SUCCESSFUL;
import QLCF.Employee;

/**
 *
 * @author HP
 */
public class LoginCoffe extends javax.swing.JFrame {

    /**
     * Creates new form LoginCoffe
     */
    public LoginCoffe() {
        initComponents();
        this.setLocation(450, 150);
        this.setVisible(false);
        this.setTitle("Login");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jbtnExit = new javax.swing.JButton();
        jbtnLogin = new javax.swing.JButton();
        jtxtUserName = new javax.swing.JTextField();
        jbtnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Login Coffee");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(190, 20, 150, 40);
        jLabel2.getAccessibleContext().setAccessibleName("");

        jPanel1.add(jPassword);
        jPassword.setBounds(270, 150, 160, 30);

        jbtnExit.setForeground(new java.awt.Color(255, 0, 0));
        jbtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/exit.png"))); // NOI18N
        jbtnExit.setText("EXIT");
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnExit);
        jbtnExit.setBounds(370, 220, 100, 40);

        jbtnLogin.setForeground(new java.awt.Color(255, 51, 51));
        jbtnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/login.png"))); // NOI18N
        jbtnLogin.setText("Login");
        jbtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLogin);
        jbtnLogin.setBounds(90, 220, 100, 40);
        jPanel1.add(jtxtUserName);
        jtxtUserName.setBounds(270, 90, 160, 30);

        jbtnReset.setForeground(new java.awt.Color(255, 0, 0));
        jbtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/repass.jpg"))); // NOI18N
        jbtnReset.setText("Đăng ký");
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnReset);
        jbtnReset.setBounds(230, 220, 110, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("UserName:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(180, 90, 80, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("PassWord:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(180, 160, 70, 17);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/4495288_hinh-nen-tinh-yeu-de-thuong-kute-8.jpg"))); // NOI18N
        jLabel6.setText("jLabel1");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 510, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnExitActionPerformed

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
           Register Info = new Register();
           Info.setVisible(true);
           this.setVisible(false);       
    }//GEN-LAST:event_jbtnResetActionPerformed

    private void jbtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoginActionPerformed
        if (jtxtUserName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật UserName! ");
        } else if (jPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật Password! ");
        } else {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                String sql = "select * from Administrator where Username=? and Password=?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jtxtUserName.getText());
                ps.setString(2, jPassword.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Chúc mừng bạn đăng nhập thành công");
                    MainCoffe field = new MainCoffe();
                    field.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác!");
                }
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginCoffe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginCoffe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginCoffe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginCoffe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginCoffe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnLogin;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JTextField jtxtUserName;
    // End of variables declaration//GEN-END:variables
 private void systemExit() {
        WindowEvent wincloseing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    }
}
