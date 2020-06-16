/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import QLCF.LoginCoffe;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author HP
 */
public class Employee extends javax.swing.JFrame {

    DefaultTableModel tbn = new DefaultTableModel();
   

    /**
     * Creates new form QUANLYNHANVIEN
     */
    public Employee() {
        initComponents();
        loadData();
        this.setVisible(false);
        this.setTitle("Quản Lý Nhân Viên");
        find("");

    }

    public void loadData() {
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
            tbn.setColumnIdentifiers(column);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                tableMain.setModel(tbn);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        tableMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableMain.getSelectedRow() >= 0) {
                    tfCodeEmp1.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 0) + "");
                    tfFullName.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 1) + "");
                    tfPassword.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 2) + "");
                    tfGender.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 3) + "");
                    tfBirthday.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 4) + "");
                    tfPhone.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 5) + "");
                    tfEmail.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 6) + "");
                    tfCMNN.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 7) + "");
                    tfAddress.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 8) + "");
                    tfImg.setText(tableMain.getValueAt(tableMain.getSelectedRow(), 9) + "");
                }
            }
        });
    }

    private boolean checkNull() {
        if (tfCodeEmp1.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập mã nhân viên!");
            return false;
        } else if (tfFullName.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập họ tên nhân viên!");
            return false;
        } else if (!tfPhone.getText().matches("0\\d\\d\\d\\d\\d\\d\\d\\d\\d")) {
           tftrangthai.setText("Số điện thoại không hợp lệ!");
            return false;
        } else if (tfAddress.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập địa chỉ!");
            return false;
        } else if (tfGender.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập giới tính !");
            return false;
        } else if (tfImg.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập tên hình ảnh!");
            return false;
        } else if (tfBirthday.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập  ngày sinh!");
            return false;
        } else if (!tfCMNN.getText().matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) {
            tftrangthai.setText("Cmnd không hợp lệ !");
            return false;
        } else if (!tfEmail.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            tftrangthai.setText("Email Chưa hợp lệ!");
            return false;
        } else if (tfPassword.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập password!");
            return false;
       } 
        return true;

    }

    private void find(String FullName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from Employee where FullName like ?");

            timkiem.getText();
            ps.setString(1, "%" + FullName + "%");
            while (tbn.getRowCount() > 0) {
                tbn.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeEmp = rs.getString("CodeEmp");
                String Name = rs.getString("FullName");
                String Password = rs.getString("Password");
                String Gender = rs.getString("Gender");
                String Birthday = rs.getString("Birthday");
                String Phone = rs.getString("Phone");
                String Email = rs.getString("Email");
                String CMND = rs.getString("CMND");
                String Address = rs.getString("Address");
                String Img = rs.getString("Img");

                Vector row = new Vector();
                row.add(CodeEmp);
                row.add(Name);
                row.add(Password);
                row.add(Gender);
                row.add(Birthday);
                row.add(Phone);
                row.add(Email);
                row.add(CMND);
                row.add(Address);
                row.add(Img);
                tbn.addRow(row);
            }
            tbn.fireTableDataChanged();
            ps.executeUpdate();
            tbn.setRowCount(0);
            loadData();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        tfCodeEmp1 = new javax.swing.JTextField();
        tfPassword = new javax.swing.JTextField();
        tfFullName = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfCMNN = new javax.swing.JTextField();
        tfImg = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfBirthday = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        tftrangthai = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        InsertEmp = new javax.swing.JButton();
        UpdateEmp = new javax.swing.JButton();
        Cander = new javax.swing.JButton();
        SearchEmp = new javax.swing.JButton();
        timkiem = new javax.swing.JTextField();
        tfGender = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Quản lý Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Thông Tin");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tfCodeEmp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodeEmp1ActionPerformed(evt);
            }
        });

        tfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPasswordActionPerformed(evt);
            }
        });

        tfFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFullNameActionPerformed(evt);
            }
        });

        tfPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPhoneActionPerformed(evt);
            }
        });

        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        tfCMNN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCMNNActionPerformed(evt);
            }
        });

        tfImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImgActionPerformed(evt);
            }
        });

        tfAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAddressActionPerformed(evt);
            }
        });

        jLabel3.setText("CodeEmd");

        jLabel4.setText("Password");

        jLabel5.setText("FullName");

        jLabel6.setText("Birthday");

        jLabel8.setText("Phone");

        jLabel9.setText("Email");

        jLabel10.setText("CMNN");

        jLabel11.setText("Img");

        jLabel12.setText("Address");

        tableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodeEmd", "FullName", "Password", "Gender", "Birthday", "Phone", "Email", "CMNN", "Address", "Img"
            }
        ));
        jScrollPane1.setViewportView(tableMain);

        jLabel14.setText("Gender");

        tftrangthai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tftrangthai.setForeground(new java.awt.Color(255, 0, 0));
        tftrangthai.setText("Trạng Thái");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        InsertEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user (1)(1).png"))); // NOI18N
        InsertEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertEmpActionPerformed(evt);
            }
        });

        UpdateEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user(2)(1).png"))); // NOI18N
        UpdateEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateEmpActionPerformed(evt);
            }
        });

        Cander.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\TheCoffee\\src\\IMAGE\\x-button.png")); // NOI18N
        Cander.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanderActionPerformed(evt);
            }
        });

        SearchEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/search (1).png"))); // NOI18N
        SearchEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchEmpActionPerformed(evt);
            }
        });

        timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCodeEmp1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCMNN, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(tfEmail)
                                    .addComponent(tfPhone))
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(Cander)
                                .addGap(190, 190, 190)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timkiem))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(458, 458, 458))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(tftrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(InsertEmp)
                                        .addGap(44, 44, 44)
                                        .addComponent(UpdateEmp)
                                        .addGap(55, 55, 55)
                                        .addComponent(jButton1))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfImg, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(175, 175, 175)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(423, 423, 423))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfCodeEmp1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(tfFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(tfCMNN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(tfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(tfBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(tfImg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(51, 51, 51)))
                .addComponent(tftrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(Cander)
                    .addComponent(InsertEmp)
                    .addComponent(UpdateEmp)
                    .addComponent(SearchEmp)
                    .addComponent(timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 130, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfCodeEmp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodeEmp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodeEmp1ActionPerformed

    private void tfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPasswordActionPerformed

    private void tfFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFullNameActionPerformed

    private void tfPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPhoneActionPerformed

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfCMNNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCMNNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCMNNActionPerformed

    private void tfImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfImgActionPerformed

    private void tfAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAddressActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MainCoffe home = new MainCoffe();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timkiemActionPerformed

    private void InsertEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertEmpActionPerformed
        if (checkNull()) {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                PreparedStatement ps = conn.prepareStatement("Insert into Employee values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, tfCodeEmp1.getText());
                ps.setString(2, tfFullName.getText());
                ps.setString(3, tfPassword.getText());
                ps.setString(4, tfGender.getText());
                ps.setString(5, tfBirthday.getText());
                ps.setString(6, tfPhone.getText());
                ps.setString(7, tfEmail.getText());
                ps.setString(8, tfCMNN.getText());
                ps.setString(9, tfAddress.getText());
                ps.setString(10,tfImg.getText());
                int chk = ps.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm Nhân Viên Thành Công");
                    loadData();
                    tbn.setRowCount(0);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());

            }

        }
    }//GEN-LAST:event_InsertEmpActionPerformed

    private void UpdateEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateEmpActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Update Employee set FullName=?,Password=?,Gender=?,Birthday=?,Phone=?,Email=?,CMND=?,Address=?,Img=? where CodeEmp =? ");
            ps.setString(10, tfCodeEmp1.getText());
            ps.setString(1, tfFullName.getText());
            ps.setString(2, tfPassword.getText());
            ps.setString(3, tfGender.getText());
            ps.setString(4, tfBirthday.getText());
            ps.setString(5, tfPhone.getText());
            ps.setString(6, tfEmail.getText());
            ps.setString(7, tfCMNN.getText());
            ps.setString(8, tfAddress.getText());
            ps.setString(9, tfImg.getText());
            ps.executeUpdate();
            tbn.setRowCount(0);
            loadData();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_UpdateEmpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Delete Employee where CodeEmp =?");
            ps.setString(1, tableMain.getValueAt(tableMain.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete this Employee", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                tbn.setRowCount(0);
                loadData();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CanderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanderActionPerformed
        tfCodeEmp1.setText(null);
        tfFullName.setText(null);
        tfPassword.setText(null);
        tfAddress.setText(null);
        tfBirthday.setText(null);
        tfCMNN.setText(null);
        tfImg.setText(null);
        tfEmail.setText(null);
        tfGender.setText(null);
        tfPhone.setText(null);
    }//GEN-LAST:event_CanderActionPerformed

    private void SearchEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchEmpActionPerformed
        find(timkiem.getText());
        if (timkiem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
        }

    }//GEN-LAST:event_SearchEmpActionPerformed

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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cander;
    private javax.swing.JButton InsertEmp;
    private javax.swing.JButton SearchEmp;
    private javax.swing.JButton UpdateEmp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableMain;
    private javax.swing.JTextField tfAddress;
    private datechooser.beans.DateChooserCombo tfBirthday;
    private javax.swing.JTextField tfCMNN;
    private javax.swing.JTextField tfCodeEmp1;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFullName;
    private javax.swing.JTextField tfGender;
    private javax.swing.JTextField tfImg;
    private javax.swing.JTextField tfPassword;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JLabel tftrangthai;
    private javax.swing.JTextField timkiem;
    // End of variables declaration//GEN-END:variables

}
