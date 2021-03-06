/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class employee_salary extends javax.swing.JFrame {
DefaultTableModel ten = new DefaultTableModel();
    /**
     * Creates new form employee_salary
     */
    public employee_salary() {
        initComponents();
        this.setLocation(240,80);
        setSize(800, 560);
        this.setVisible(false);
        this.setTitle("Lương Nhân Viên");
        loadtennv();
    }
public void loadtennv() {
        try {

            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            int number;
            Vector row, column;
            column = new Vector();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from Employee");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            ten.setColumnIdentifiers(column);
            while (rs.next()) {
                tênnv.addItem(rs.getString("FullName"));
                manv.addItem(rs.getString("CodeEmp"));
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                ten.addRow(row);
               

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

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
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        cbbLuong = new javax.swing.JComboBox();
        txtThuong = new javax.swing.JTextField();
        btnLuong = new javax.swing.JButton();
        txtTienLuong = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tênnv = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        manv = new javax.swing.JComboBox<>();
        inluong = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        HIENTHI = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 255));
        jLabel17.setText("Số ngày làm việc : ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 255));
        jLabel19.setText("Mức lương : ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("Tiền thưởng thêm : ");

        txtNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayActionPerformed(evt);
            }
        });

        cbbLuong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cbbLuong.setForeground(new java.awt.Color(255, 0, 0));
        cbbLuong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100,000", "200,000", "300,000", "400,000" }));
        cbbLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLuongActionPerformed(evt);
            }
        });

        txtThuong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtThuongCaretUpdate(evt);
            }
        });
        txtThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThuongActionPerformed(evt);
            }
        });

        btnLuong.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnLuong.setForeground(new java.awt.Color(0, 0, 255));
        btnLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/monney.png"))); // NOI18N
        btnLuong.setText("Tính Lương");
        btnLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuongActionPerformed(evt);
            }
        });

        txtTienLuong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtTienLuong.setForeground(new java.awt.Color(0, 0, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Tên Nhân Viên :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Mã Nhân Viên :");

        inluong.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        inluong.setForeground(new java.awt.Color(0, 0, 255));
        inluong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/printf.png"))); // NOI18N
        inluong.setText("In");
        inluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inluongActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 0, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/x-button.png"))); // NOI18N
        jButton19.setText("Reset");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgay)
                                    .addComponent(tênnv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(manv, 0, 118, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(txtThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(110, 110, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(txtTienLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tênnv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtThuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(txtTienLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inluong, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        HIENTHI.setColumns(20);
        HIENTHI.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        HIENTHI.setRows(5);
        jScrollPane3.setViewportView(HIENTHI);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Lương Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayActionPerformed

    private void cbbLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLuongActionPerformed

    private void txtThuongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtThuongCaretUpdate

    }//GEN-LAST:event_txtThuongCaretUpdate

    private void btnLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuongActionPerformed
        while (true) {
            if (txtNgay.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Số ngày làm việc không được bỏ trống ! ");
                return;
            } else if (!txtNgay.getText().trim().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Ngày làm việc phải là số ! ");
                return;
            } else if (Double.parseDouble(txtNgay.getText().trim()) > 31) {
                JOptionPane.showMessageDialog(null, "Số ngày làm việc phải nhỏ hơn 31");
                return;
            } else {
                break;
            }
        }
        double ngay = Double.parseDouble(txtNgay.getText().trim());
        String a = (String) cbbLuong.getSelectedItem();
        double LuongCB = Double.parseDouble(a.replaceAll(",", ""));
        double TienLuong;

        if (txtThuong.getText().trim().equals("")) {
            TienLuong = ngay * LuongCB;
        } else {
            while (true) {
                if (!txtThuong.getText().trim().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Tiền thưởng phải là số ! ");
                    return;
                } else if (Double.parseDouble(txtThuong.getText().trim()) > 500000) {
                    JOptionPane.showMessageDialog(null, "Tiền thưởng phải nhỏ hơn 500.000 ");
                    return;
                } else {
                    break;
                }
            }
            double Thuong = Double.parseDouble(txtThuong.getText().trim());
            TienLuong = (ngay * LuongCB) + Thuong;
        }
        txtTienLuong.setText(String.valueOf(nft.format(TienLuong)) + " VNĐ");
    }//GEN-LAST:event_btnLuongActionPerformed
NumberFormat nft = new DecimalFormat("#,###");
    private void txtThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThuongActionPerformed

    private void inluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inluongActionPerformed
       HIENTHI.setText("**********************************\n");
        HIENTHI.setText( HIENTHI.getText()+"*       Tiền Lương Nhân Viên     *\n");
        HIENTHI.setText( HIENTHI.getText()+"**********************************\n");
        Date obj = new Date();
         String date = obj.toString();
            HIENTHI.setText(  HIENTHI.getText()+"\n"+date+"\n\n");
           HIENTHI.setText(  HIENTHI.getText()+"Tên nhân viên:"+tênnv.getSelectedItem()+"\n\n");
          HIENTHI.setText(  HIENTHI.getText()+"Mã Nhân Viên:"+manv.getSelectedItem()+"\n\n");
           HIENTHI.setText(  HIENTHI.getText()+"Số Ngày Làm Việc:"+txtNgay.getText()+"\n\n");
          HIENTHI.setText(  HIENTHI.getText()+"Lương cơ bản:"+cbbLuong.getSelectedItem()+"\n\n");
           HIENTHI.setText(  HIENTHI.getText()+"Tiền Thưởng:"+txtThuong.getText()+"\n\n\n");
          HIENTHI.setText(  HIENTHI.getText()+"Tổng Lương:"+txtTienLuong.getText()+"\n\n\n");
           try {
            HIENTHI.print();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_inluongActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainCoffe home = new MainCoffe();
        this.setVisible(false);
        home.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        txtNgay.setText("");
        txtThuong.setText("");
        txtTienLuong.setText("");
         HIENTHI.setText("");
    }//GEN-LAST:event_jButton19ActionPerformed

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
            java.util.logging.Logger.getLogger(employee_salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(employee_salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(employee_salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(employee_salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new employee_salary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea HIENTHI;
    private javax.swing.JButton btnLuong;
    private javax.swing.JComboBox cbbLuong;
    private javax.swing.JButton inluong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> manv;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtThuong;
    private javax.swing.JLabel txtTienLuong;
    private javax.swing.JComboBox<String> tênnv;
    // End of variables declaration//GEN-END:variables
}
