/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Customer extends javax.swing.JFrame {

    DefaultTableModel tbn = new DefaultTableModel();

    /**
     * Creates new form Customer
     */
    public Customer() {
        initComponents();
        this.setLocation(250, 110);
        setSize(1000, 600);
        this.setVisible(false);
        this.setTitle("Khác Hàng");
        loadData();
    }

    public void loadData() {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            int number;
            Vector row, column;
            column = new Vector();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from Customer");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            tbn.setColumnIdentifiers(column);
            while (rs.next()) {
                cbtype.addItem(rs.getString("CardType"));
                cbsex.addItem(rs.getString("Gender"));
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbn.addRow(row);
                table.setModel(tbn);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    txtmathe.setText(table.getValueAt(table.getSelectedRow(), 0) + "");
                    cbtype.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 1) + "");
                    txtten.setText(table.getValueAt(table.getSelectedRow(), 2) + "");
                    cbsex.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 3) + "");
                    dateChooserCombo1.setText(table.getValueAt(table.getSelectedRow(), 4) + "");
                    tbfmobile.setText(table.getValueAt(table.getSelectedRow(), 5) + "");
                    tbfemail.setText(table.getValueAt(table.getSelectedRow(), 6) + "");
                    tbfcmnd.setText(table.getValueAt(table.getSelectedRow(), 7) + "");
                    tbfdiachi.setText(table.getValueAt(table.getSelectedRow(), 8) + "");
                    txtdiem.setText(table.getValueAt(table.getSelectedRow(), 9) + "");

                }
            }
        });
    }

    private boolean checkNull() {
        if (txtmathe.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập mã khách hàng!");
            return false;
        } else if (cbtype.getSelectedItem().equals("")) {
            tftrangthai.setText("Bạn chưa nhập loại loại thẻ!");
            return false;
        } else if (txtten.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập họ tên khách hàng!");
            return false;
        } else if (!tbfmobile.getText().matches("0\\d\\d\\d\\d\\d\\d\\d\\d\\d")) {
            tftrangthai.setText("Số điện thoại không hợp lệ!");
            return false;
        } else if (tbfdiachi.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập địa chỉ!");
            return false;
        } else if (cbsex.getSelectedItem().equals("")) {
            tftrangthai.setText("Bạn chưa nhập loại giới tính!");
            return false;
        } else if (dateChooserCombo1.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập  ngày sinh!");
            return false;
        } else if (!tbfcmnd.getText().matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d")) {
            tftrangthai.setText("Cmnd không hợp lệ !");
            return false;
        } else if (!tbfemail.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            tftrangthai.setText("Email Chưa hợp lệ!");
            return false;
        } else if (txtdiem.getText().equals("")) {
            tftrangthai.setText("Bạn chưa nhập điểm tích lũy!");
            return false;
        }
        return true;

    }
     private void food(String FullName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from Customer where FullName like ?");
            TimkiemKH.getText();
            ps.setString(1, "%" + FullName + "%" );
            while (tbn.getRowCount() > 0) {
                tbn.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeCus = rs.getString("CodeCus");
                String CardType = rs.getString("CardType");
                String Name = rs.getString("FullName");
                String Gender = rs.getString("Gender");
                String Birthday = rs.getString("Birthday");
                String Phone = rs.getString("Phone");
                String Email = rs.getString("Email");
                String CMND = rs.getString("CMND");
                String Address = rs.getString("Address");
                String Point = rs.getString("Point");
                Vector row = new Vector();
                row.add(CodeCus);
                row.add(CardType);
                row.add(Name);
                row.add(Gender);
                row.add(Birthday);
                row.add(Phone);
                row.add(Email);
                row.add(CMND);
                row.add(Address);
                row.add(Point);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TimkiemKH = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtmathe = new javax.swing.JTextField();
        txtten = new javax.swing.JTextField();
        tbfmobile = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbtype = new javax.swing.JComboBox<>();
        tbfemail = new javax.swing.JTextField();
        tbfcmnd = new javax.swing.JTextField();
        tbfdiachi = new javax.swing.JTextField();
        txtdiem = new javax.swing.JTextField();
        cbsex = new javax.swing.JComboBox<>();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        btnNewButton = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btncapnhap = new javax.swing.JButton();
        bntadd = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        tftrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CodeCus", "CardType", "FullName", "Gender", "Birthday", "Phone", "Email", "CMND", "Address", "Point"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Nhập tên khách hàngi:");

        TimkiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimkiemKHActionPerformed(evt);
            }
        });

        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/search (1).png"))); // NOI18N
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã thẻ:");

        jLabel3.setText("Email:");

        jLabel4.setText("CMND:");

        jLabel5.setText("Họ và tên:");

        jLabel6.setText("Giới tính:");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Ngày sinh:");

        jLabel10.setText("Điểm Tích Lũy:");

        jLabel11.setText("Số điện thoại:");

        txtmathe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatheActionPerformed(evt);
            }
        });

        jLabel12.setText("Loại thẻ:");

        cbtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtypeActionPerformed(evt);
            }
        });

        tbfcmnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbfcmndActionPerformed(evt);
            }
        });

        txtdiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiemActionPerformed(evt);
            }
        });

        btnNewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/edit.png"))); // NOI18N
        btnNewButton.setText("reset");
        btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewButtonActionPerformed(evt);
            }
        });

        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btncapnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user(2)(1).png"))); // NOI18N
        btncapnhap.setText("Sửa");
        btncapnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhapActionPerformed(evt);
            }
        });

        bntadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user (1)(1).png"))); // NOI18N
        bntadd.setText("Thêm");
        bntadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntaddActionPerformed(evt);
            }
        });

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        tftrangthai.setForeground(new java.awt.Color(255, 0, 51));
        tftrangthai.setText("                            Trạng thái");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(104, 104, 104))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(Home)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TimkiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 65, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabel8)
                                                        .addGap(18, 18, 18)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(cbsex, 0, 151, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbtype, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(txtmathe, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbfemail, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(tbfdiachi)
                                    .addComponent(txtdiem)
                                    .addComponent(tbfcmnd)
                                    .addComponent(tbfmobile, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btncapnhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntadd, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(tftrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TimkiemKH, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Home))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tbfmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNewButton))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtmathe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tbfemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(btnxoa))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbtype, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbfcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(btncapnhap))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbsex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(tbfdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtdiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addGap(8, 8, 8))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(bntadd)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(tftrangthai)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmatheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmatheActionPerformed

    private void cbtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbtypeActionPerformed

    private void tbfcmndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbfcmndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbfcmndActionPerformed

    private void txtdiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiemActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        MainCoffe home = new MainCoffe();
        this.setVisible(false);
        home.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_HomeActionPerformed

    private void TimkiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimkiemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimkiemKHActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Delete Customer where CodeCus =?");
            ps.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete this Khách hàng", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                tbn.setRowCount(0);
                loadData();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }//GEN-LAST:event_btnxoaActionPerformed

    private void bntaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntaddActionPerformed
        if (checkNull()) {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                PreparedStatement ps = conn.prepareStatement("Insert into Customer values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, txtmathe.getText());
                ps.setString(2, cbtype.getSelectedItem().toString());
                ps.setString(3, txtten.getText());
                ps.setString(4, cbsex.getSelectedItem().toString());
                ps.setString(5, dateChooserCombo1.getText());
                ps.setString(6, tbfmobile.getText());
                ps.setString(7, tbfemail.getText());
                ps.setString(8, tbfcmnd.getText());
                ps.setString(9, tbfdiachi.getText());
                ps.setString(10, txtdiem.getText());
                int chk = ps.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
                    loadData();
                    tbn.setRowCount(0);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());

            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_bntaddActionPerformed

    private void btnNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewButtonActionPerformed
        txtmathe.setText(null);

        txtten.setText(null);
        tbfmobile.setText(null);
        dateChooserCombo1.setText(null);
        tbfmobile.setText(null);
        tbfemail.setText(null);
        tbfcmnd.setText(null);
        tbfdiachi.setText(null);
        txtdiem.setText(null);
    }//GEN-LAST:event_btnNewButtonActionPerformed

    private void btncapnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhapActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Update Customer set CardType=?,FullName=?,Gender=?,Birthday=?,Phone=?,Email=?,CMND=?,Address=?,Point=? where CodeCus =? ");
            ps.setString(10, txtmathe.getText());
            ps.setString(1, cbtype.getSelectedItem().toString());
            ps.setString(2, txtten.getText());
            ps.setString(3, cbsex.getSelectedItem().toString());
            ps.setString(4, dateChooserCombo1.getText());
            ps.setString(5, tbfmobile.getText());
            ps.setString(6, tbfemail.getText());
            ps.setString(7, tbfcmnd.getText());
            ps.setString(8, tbfdiachi.getText());
            ps.setString(9, txtdiem.getText());
            ps.executeUpdate();
            tbn.setRowCount(0);
            loadData();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btncapnhapActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
       food(TimkiemKH.getText());
        if (TimkiemKH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
        } // TODO add your handling code here:
    }//GEN-LAST:event_btntimkiemActionPerformed

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
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JTextField TimkiemKH;
    private javax.swing.JButton bntadd;
    private javax.swing.JButton btnNewButton;
    private javax.swing.JButton btncapnhap;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbsex;
    private javax.swing.JComboBox<String> cbtype;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField tbfcmnd;
    private javax.swing.JTextField tbfdiachi;
    private javax.swing.JTextField tbfemail;
    private javax.swing.JTextField tbfmobile;
    private javax.swing.JLabel tftrangthai;
    private javax.swing.JTextField txtdiem;
    private javax.swing.JTextField txtmathe;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
