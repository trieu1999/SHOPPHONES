/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

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
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Menu extends javax.swing.JFrame {

    DefaultTableModel drink = new DefaultTableModel();
    DefaultTableModel food = new DefaultTableModel();
    DefaultTableModel productType = new DefaultTableModel();

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setLocation(250, 110);
        setSize(1000, 560);
        this.setVisible(false);
        this.setTitle("MENU");
        loaddrink();
        loadfood();
        loadProductybe();

    }

    public void loaddrink() {
        try {

            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            int number;
            Vector row, column;
            column = new Vector();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from Drink");

            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            drink.setColumnIdentifiers(column);
            while (rs.next()) {
                tfCodeType.addItem(rs.getString("CodeType"));
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                drink.addRow(row);
                Drinktable.setModel(drink);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Drinktable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (Drinktable.getSelectedRow() >= 0) {
                    tfCodeDrink.setText(Drinktable.getValueAt(Drinktable.getSelectedRow(), 0) + "");
                    tfDrinkName.setText(Drinktable.getValueAt(Drinktable.getSelectedRow(), 1) + "");
                    tfCodeType.setSelectedItem(Drinktable.getModel().getValueAt(Drinktable.getSelectedRow(), 2) + "");
                    tfAmount.setText(Drinktable.getValueAt(Drinktable.getSelectedRow(), 3) + "");
                    tfPrice.setText(Drinktable.getValueAt(Drinktable.getSelectedRow(), 4) + "");

                }
            }
        });
    }

    public void loadfood() {
        try {

            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            int number;
            Vector row, column;
            column = new Vector();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from Food");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            food.setColumnIdentifiers(column);
            while (rs.next()) {
                afCodeType.addItem(rs.getString("CodeType"));
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                food.addRow(row);
                foodtable.setModel(food);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        foodtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (foodtable.getSelectedRow() >= 0) {
                    afCodeFood.setText(foodtable.getValueAt(foodtable.getSelectedRow(), 0) + "");
                    afFoodName.setText(foodtable.getValueAt(foodtable.getSelectedRow(), 1) + "");
                    afCodeType.setSelectedItem(foodtable.getModel().getValueAt(foodtable.getSelectedRow(), 2) + "");
                    afAmount.setText(foodtable.getValueAt(foodtable.getSelectedRow(), 3) + "");
                    afPrice.setText(foodtable.getValueAt(foodtable.getSelectedRow(), 4) + "");

                }
            }
        });
    }

    public void loadProductybe() {
        try {

            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            int number;
            Vector row, column;
            column = new Vector();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from ProductType");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            for (int i = 1; i <= number; i++) {
                column.add(metadata.getColumnName(i));
            }
            productType.setColumnIdentifiers(column);
            while (rs.next()) {
                
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                productType.addRow(row);
                ProductTypeTable.setModel(productType);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        ProductTypeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (ProductTypeTable.getSelectedRow() >= 0) {

                    Codepro.setText(ProductTypeTable.getValueAt(ProductTypeTable.getSelectedRow(), 0) + "");
                    Namepro.setText(ProductTypeTable.getValueAt(ProductTypeTable.getSelectedRow(), 1) + "");
                    sizepro.setText(ProductTypeTable.getValueAt(ProductTypeTable.getSelectedRow(), 2) + "");

                }
            }
        });
    }

    private void food(String FoodName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from Food where FoodName like ?");

            TimkiemFood.getText();
            ps.setString(1, "%" + FoodName + "%");
            while (food.getRowCount() > 0) {
                food.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeFood = rs.getString("CodeFood");
                String Name = rs.getString("FoodName");
                String CodeType = rs.getString("CodeType");
                String Amount = rs.getString("Amount");
                String Price = rs.getString("Price");
                Vector row = new Vector();
                row.add(CodeFood);
                row.add(Name);
                row.add(CodeType);
                row.add(Amount);
                row.add(Price);
                food.addRow(row);
            }
            food.fireTableDataChanged();
            ps.executeUpdate();
            food.setRowCount(0);
            loadfood();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void drink(String DrinkName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from Drink where DrinkName like ?");

            TimkiemDrink.getText();
            ps.setString(1, "%" + DrinkName + "%");
            while (drink.getRowCount() > 0) {
                drink.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeDrink = rs.getString("CodeDrink");
                String Name = rs.getString("DrinkName");
                String CodeType = rs.getString("CodeType");
                String Amount = rs.getString("Amount");
                String Price = rs.getString("Price");
                Vector row = new Vector();
                row.add(CodeDrink);
                row.add(Name);
                row.add(CodeType);
                row.add(Amount);
                row.add(Price);
                drink.addRow(row);
            }
            drink.fireTableDataChanged();
            ps.executeUpdate();
            drink.setRowCount(0);
            loaddrink();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void producttype(String TypeName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from ProductType where TypeName  like ?");

            TimkiemProduct.getText();
            ps.setString(1, "%" + TypeName + "%");
            while (productType.getRowCount() > 0) {
                productType.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeType = rs.getString("CodeType");
                String Name = rs.getString("TypeName");
                String Size = rs.getString("Size");
                Vector row = new Vector();
                row.add(CodeType);
                row.add(Name);
                row.add(Size);
                productType.addRow(row);
            }
            productType.fireTableDataChanged();
            ps.executeUpdate();
            productType.setRowCount(0);
            loaddrink();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private boolean checkfood() {
        if (afCodeFood.getText().equals("")) {
            trangthaifood.setText("Bạn chưa nhập mã thức ăn!");
            return false;
        } else if (afFoodName.getText().equals("")) {
            trangthaifood.setText("Bạn chưa nhập tên thức ăn!");
            return false;
        }else if (!afAmount.getText().trim().matches("[0-9]+")) {
                trangthaifood.setText( "số lượng phải là số ! ");
                return false;
        } else if (afCodeType.getSelectedItem().equals("")) {
            trangthaifood.setText("Bạn chưa nhập loại thức ăn!");
            return false;
        }else if (!afPrice.getText().trim().matches("[0-9]+")) {
                trangthaifood.setText( "giá tiền phải là số! ");
                return false;
        }
        return true;

    }

    private boolean checkDrink() {
        if (tfCodeDrink.getText().equals("")) {
            trangthaidrink.setText("Bạn chưa nhập mã nước!");
            return false;
        } else if (tfDrinkName.getText().equals("")) {
            trangthaidrink.setText("Bạn chưa nhập tên nước!");
            return false;
        } else if (tfCodeType.getSelectedItem().equals("")) {
            trangthaidrink.setText("Bạn chưa nhập loại nước!");
            return false;
        } else if (!afAmount.getText().trim().matches("[0-9]+")) {
                trangthaidrink.setText( "số lượng phải là số ! ");
                return false;
        } else if (!afPrice.getText().trim().matches("[0-9]+")) {
                trangthaidrink.setText( "giá tiền phải là số! ");
                return false;
        }
        return true;

    }

    private boolean checkProductype() {
        if (Codepro.getText().equals("")) {
            trangthaiproduct.setText("Bạn chưa nhập loại nước!");
            return false;
        } else if (Namepro.getText().equals("")) {
            trangthaiproduct.setText("Bạn chưa nhập tên loại!");
            return false;
        } else if (sizepro.getText().equals("")) {
            trangthaiproduct.setText("Bạn chưa nhập size!");
            return false;
        }

        return true;

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        afCodeFood = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        afFoodName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        afPrice = new javax.swing.JTextField();
        afAmount = new javax.swing.JTextField();
        trangthaifood = new javax.swing.JLabel();
        InsertEmp1 = new javax.swing.JButton();
        UpdateEmp1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Canderfood = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        foodtable = new javax.swing.JTable();
        Searchfood = new javax.swing.JButton();
        TimkiemFood = new javax.swing.JTextField();
        homefood = new javax.swing.JButton();
        afCodeType = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfCodeDrink = new javax.swing.JTextField();
        tfDrinkName = new javax.swing.JTextField();
        tfPrice = new javax.swing.JTextField();
        tfAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Drinktable = new javax.swing.JTable();
        trangthaidrink = new javax.swing.JLabel();
        InsertEmp = new javax.swing.JButton();
        UpdateEmp = new javax.swing.JButton();
        DeleterDrink = new javax.swing.JButton();
        Canderdrink = new javax.swing.JButton();
        SearchDrink = new javax.swing.JButton();
        TimkiemDrink = new javax.swing.JTextField();
        homedrink = new javax.swing.JButton();
        tfCodeType = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Namepro = new javax.swing.JTextField();
        sizepro = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProductTypeTable = new javax.swing.JTable();
        SearchProduct = new javax.swing.JButton();
        TimkiemProduct = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        Canderpro = new javax.swing.JButton();
        homepro = new javax.swing.JButton();
        trangthaiproduct = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Codepro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setName(""); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Thức Ăn");

        jLabel9.setText("CodeFood");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("FoodName");

        jLabel11.setText("CodeType");

        jLabel12.setText("Amount");

        jLabel13.setText("Price");

        afPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afPriceActionPerformed(evt);
            }
        });

        trangthaifood.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        trangthaifood.setForeground(new java.awt.Color(255, 51, 51));
        trangthaifood.setText("Trạng Thái");

        InsertEmp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user (1)(1).png"))); // NOI18N
        InsertEmp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertEmp1ActionPerformed(evt);
            }
        });

        UpdateEmp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user(2)(1).png"))); // NOI18N
        UpdateEmp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateEmp1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Canderfood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/x-button.png"))); // NOI18N
        Canderfood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanderfoodActionPerformed(evt);
            }
        });

        foodtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(foodtable);

        Searchfood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/search (1).png"))); // NOI18N
        Searchfood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchfoodActionPerformed(evt);
            }
        });

        TimkiemFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimkiemFoodActionPerformed(evt);
            }
        });

        homefood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        homefood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homefoodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(homefood, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(258, 258, 258)
                                        .addComponent(trangthaifood, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(133, 133, 133)
                                        .addComponent(InsertEmp1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(UpdateEmp1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(afCodeType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(afAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addComponent(Canderfood, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(afPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(afCodeFood, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(afFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(Searchfood)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TimkiemFood, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homefood))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afCodeFood, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afCodeType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(trangthaifood, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(InsertEmp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UpdateEmp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(Canderfood, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Searchfood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimkiemFood))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 908, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Food", jPanel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Quản Lý Nước Uống");

        jLabel1.setText("CodeDrink");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("DrinkName");

        jLabel4.setText("CodeType");

        jLabel5.setText("Amount");

        jLabel6.setText("Price");

        Drinktable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Drinktable);

        trangthaidrink.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        trangthaidrink.setForeground(new java.awt.Color(255, 0, 51));
        trangthaidrink.setText("Trạng Thái");

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

        DeleterDrink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user.png"))); // NOI18N
        DeleterDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleterDrinkActionPerformed(evt);
            }
        });

        Canderdrink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/x-button.png"))); // NOI18N
        Canderdrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanderdrinkActionPerformed(evt);
            }
        });

        SearchDrink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/search (1).png"))); // NOI18N
        SearchDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchDrinkActionPerformed(evt);
            }
        });

        homedrink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        homedrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homedrinkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDrinkName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(InsertEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(UpdateEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(35, 35, 35)
                                .addComponent(tfCodeDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCodeType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(DeleterDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Canderdrink, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)))
                        .addGap(34, 34, 34)
                        .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(SearchDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TimkiemDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(homedrink)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(227, 227, 227)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(202, 202, 202)
                                        .addComponent(trangthaidrink, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(homedrink)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCodeDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCodeType, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDrinkName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(trangthaidrink, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InsertEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleterDrink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UpdateEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Canderdrink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SearchDrink)
                    .addComponent(TimkiemDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Drink", jPanel2);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 255));
        jLabel15.setText(" Loại sản phẩm");

        jLabel16.setText("CodeType");

        jLabel17.setText("CodeName");

        jLabel18.setText("Size");

        ProductTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(ProductTypeTable);

        SearchProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/search (1).png"))); // NOI18N
        SearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchProductActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user (1)(1).png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Canderpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/x-button.png"))); // NOI18N
        Canderpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanderproActionPerformed(evt);
            }
        });

        homepro.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\TheCoffee\\src\\IMAGE\\smart-home.png")); // NOI18N
        homepro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeproActionPerformed(evt);
            }
        });

        trangthaiproduct.setForeground(new java.awt.Color(255, 51, 51));
        trangthaiproduct.setText("Trạng Thái");

        jLabel20.setText("Thông Tin");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user(2)(1).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/user.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Namepro)
                                    .addComponent(sizepro)
                                    .addComponent(trangthaiproduct, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Codepro)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))
                    .addComponent(homepro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(Canderpro, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(SearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TimkiemProduct))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(homepro)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(Codepro))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Namepro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sizepro, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(trangthaiproduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Canderpro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TimkiemProduct, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ProductType", jPanel4);

        jPanel1.add(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void afPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_afPriceActionPerformed

    private void InsertEmp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertEmp1ActionPerformed
        if (checkfood()) {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                PreparedStatement ps = conn.prepareStatement("Insert into Food values(?,?,?,?,?)");
                ps.setString(1, afCodeFood.getText());
                ps.setString(2, afFoodName.getText());
                ps.setString(3, afCodeType.getSelectedItem().toString());
                ps.setString(4, afAmount.getText());
                ps.setString(5, afPrice.getText());
                int chk = ps.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm food thành công");
                    loadfood();
                    food.setRowCount(0);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());

            }

        }

    }//GEN-LAST:event_InsertEmp1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (checkProductype()) {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                PreparedStatement ps = conn.prepareStatement("Insert into ProductType values(?,?,?)");
                ps.setString(1, Codepro.getText());
                ps.setString(2, Namepro.getText());
                ps.setString(3, sizepro.getText());

                int chk = ps.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm Loại thành công");
                    loadProductybe();
                    productType.setRowCount(0);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());

            }

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void CanderfoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanderfoodActionPerformed
        afAmount.setText(null);
        afCodeFood.setText(null);

        afFoodName.setText(null);
        afPrice.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_CanderfoodActionPerformed

    private void CanderproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanderproActionPerformed
        Namepro.setText(null);
        sizepro.setText(null); // TODO add your handling code here:
    }//GEN-LAST:event_CanderproActionPerformed

    private void CanderdrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanderdrinkActionPerformed
        tfAmount.setText(null);
        tfCodeDrink.setText(null);

        tfDrinkName.setText(null);
        tfPrice.setText(null); // TODO add your handling code here:
    }//GEN-LAST:event_CanderdrinkActionPerformed

    private void homefoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homefoodActionPerformed
        MainCoffe food = new MainCoffe();
        this.setVisible(false);
        food.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_homefoodActionPerformed

    private void homedrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homedrinkActionPerformed
        MainCoffe drink = new MainCoffe();
        this.setVisible(false);
        drink.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_homedrinkActionPerformed

    private void homeproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeproActionPerformed
        MainCoffe pro = new MainCoffe();
        this.setVisible(false);
        pro.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_homeproActionPerformed

    private void UpdateEmp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateEmp1ActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Update Food set FoodName=?,CodeType=?,Amount=?,Price=? where CodeFood =? ");
            ps.setString(5, afCodeFood.getText());
            ps.setString(1, afFoodName.getText());
            ps.setString(2, afCodeType.getSelectedItem().toString());
            ps.setString(3, afAmount.getText());
            ps.setString(4, afPrice.getText());
            ps.executeUpdate();
            food.setRowCount(0);
            loadfood();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateEmp1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Delete Food where CodeFood =?");
            ps.setString(1, foodtable.getValueAt(foodtable.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete this Thức ăn", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                food.setRowCount(0);
                loadfood();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SearchfoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchfoodActionPerformed
        food(TimkiemFood.getText());
        if (TimkiemFood.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchfoodActionPerformed

    private void DeleterDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleterDrinkActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Delete Drink where CodeDrink =?");
            ps.setString(1, Drinktable.getValueAt(Drinktable.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete this Drink", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                drink.setRowCount(0);
                loaddrink();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } // TODO add your handling code here:
    }//GEN-LAST:event_DeleterDrinkActionPerformed

    private void UpdateEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateEmpActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Update Drink set DrinkName=?,CodeType=?,Amount=?,Price=? where CodeDrink =? ");
            ps.setString(5, tfCodeDrink.getText());
            ps.setString(1, tfDrinkName.getText());
            ps.setString(2, tfCodeType.getSelectedItem().toString());
            ps.setString(3, tfAmount.getText());
            ps.setString(4, tfPrice.getText());
            ps.executeUpdate();
            drink.setRowCount(0);
            loaddrink();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }  // TODO add your handling code here:
    }//GEN-LAST:event_UpdateEmpActionPerformed

    private void SearchDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchDrinkActionPerformed
        drink(TimkiemDrink.getText());
        if (TimkiemDrink.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
        } // TODO add your handling code here:
    }//GEN-LAST:event_SearchDrinkActionPerformed

    private void SearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchProductActionPerformed
        producttype(TimkiemProduct.getText());
        if (TimkiemProduct.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
        } // TODO add your handling code here:
    }//GEN-LAST:event_SearchProductActionPerformed

    private void InsertEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertEmpActionPerformed
        if (checkDrink()) {
            try {
                Dbconnect a = new Dbconnect();
                Connection conn = a.getConnection();
                PreparedStatement ps = conn.prepareStatement("Insert into Drink values(?,?,?,?,?)");
                ps.setString(1, tfCodeDrink.getText());
                ps.setString(2, tfDrinkName.getText());
                ps.setString(3, tfCodeType.getSelectedItem().toString());
                ps.setString(4, tfAmount.getText());
                ps.setString(5, tfPrice.getText());
                int chk = ps.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm Drink thành công");
                    loaddrink();
                    drink.setRowCount(0);
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());

            }

        }
    }//GEN-LAST:event_InsertEmpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Update ProductType set TypeName=? , Size=? where CodeType =? ");
            ps.setString(3, Codepro.getText());
            ps.setString(1, Namepro.getText());
            ps.setString(2, sizepro.getText());
            ps.executeUpdate();
            productType.setRowCount(0);
            loadProductybe();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("Delete ProductType where CodeType =?");
            ps.setString(1, ProductTypeTable.getValueAt(ProductTypeTable.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete this ProductType", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                productType.setRowCount(0);
                loadProductybe();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TimkiemFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimkiemFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimkiemFoodActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Canderdrink;
    private javax.swing.JButton Canderfood;
    private javax.swing.JButton Canderpro;
    private javax.swing.JTextField Codepro;
    private javax.swing.JButton DeleterDrink;
    private javax.swing.JTable Drinktable;
    private javax.swing.JButton InsertEmp;
    private javax.swing.JButton InsertEmp1;
    private javax.swing.JTextField Namepro;
    private javax.swing.JTable ProductTypeTable;
    private javax.swing.JButton SearchDrink;
    private javax.swing.JButton SearchProduct;
    private javax.swing.JButton Searchfood;
    private javax.swing.JTextField TimkiemDrink;
    private javax.swing.JTextField TimkiemFood;
    private javax.swing.JTextField TimkiemProduct;
    private javax.swing.JButton UpdateEmp;
    private javax.swing.JButton UpdateEmp1;
    private javax.swing.JTextField afAmount;
    private javax.swing.JTextField afCodeFood;
    private javax.swing.JComboBox<String> afCodeType;
    private javax.swing.JTextField afFoodName;
    private javax.swing.JTextField afPrice;
    private javax.swing.JTable foodtable;
    private javax.swing.JButton homedrink;
    private javax.swing.JButton homefood;
    private javax.swing.JButton homepro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField sizepro;
    private javax.swing.JTextField tfAmount;
    private javax.swing.JTextField tfCodeDrink;
    private javax.swing.JComboBox<String> tfCodeType;
    private javax.swing.JTextField tfDrinkName;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JLabel trangthaidrink;
    private javax.swing.JLabel trangthaifood;
    private javax.swing.JLabel trangthaiproduct;
    // End of variables declaration//GEN-END:variables
}
