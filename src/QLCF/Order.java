/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLCF;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Order extends javax.swing.JFrame {
 DefaultTableModel drink = new DefaultTableModel();
  DefaultTableModel food = new DefaultTableModel();
   SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    SimpleDateFormat ftnow = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat ftNgay = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");
    /**
     * Creates new form Order
     */
    public Order() {
        initComponents();
         
        this.setLocation(120, 80);
        setSize(1200, 600);
        this.setVisible(false);
        this.setTitle("Đặt Bàn");
        loaddrink();
        loadfood();
        clock();

    }
    
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date t = new Date();
                        time.setText(ft.format(t));
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
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
                afDrinkName.addItem(rs.getString("DrinkName"));
                giathucuong.addItem(rs.getString("Price"));
                
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                drink.addRow(row);
                formdrink.setModel(drink);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
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
                affoodName1.addItem(rs.getString("FoodName"));
                giathucan.addItem(rs.getString("Price"));
   
                
              
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                food.addRow(row);
                TableOder.setModel(food);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

}

    private void food(String FoodName) {
        try {
            Dbconnect a = new Dbconnect();
            Connection conn = a.getConnection();
            PreparedStatement ps = conn.prepareStatement("select *from Food where FoodName like ?");
        
            affoodName1.setSelectedIndex(1);
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

            afDrinkName.setSelectedIndex(1);
            ps.setString(1, "%" + DrinkName + "%");
            while (drink.getRowCount() > 0) {
                drink.removeRow(0);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CodeFood = rs.getString("CodeDrink");
                String Name = rs.getString("DrinkName");
                String CodeType = rs.getString("CodeType");
                String Amount = rs.getString("Amount");
                String Price = rs.getString("Price");
                Vector row = new Vector();
                row.add(CodeFood);
                row.add(Name);
                row.add(CodeType);
                row.add(Amount);
                row.add(Price);
                
                drink.addRow(row);
                
            }
           
            ps.executeUpdate();
            drink.setRowCount(0);
            loaddrink();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        ban1 = new javax.swing.JButton();
        ban2 = new javax.swing.JButton();
        ban3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        ban4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Searchfood = new javax.swing.JButton();
        soluong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tennv = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        afDrinkName = new javax.swing.JComboBox();
        affoodName1 = new javax.swing.JComboBox();
        giathucan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        giathucuong = new javax.swing.JComboBox<>();
        labeltientong = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableOder = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        formdrink = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Thanhtoan = new javax.swing.JTextArea();

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\TheCoffee\\src\\IMAGE\\hinhnenoder.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1776, 1962));
        jLabel1.setMinimumSize(new java.awt.Dimension(1776, 2962));
        jLabel1.setPreferredSize(new java.awt.Dimension(1776, 1962));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/smart-home.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(0, 0, 50, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Khu bàn"));

        ban1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        ban1.setText("Bàn 1");
        ban1.setMaximumSize(new java.awt.Dimension(75, 41));
        ban1.setMinimumSize(new java.awt.Dimension(75, 41));
        ban1.setPreferredSize(new java.awt.Dimension(75, 41));

        ban2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        ban2.setText("Bàn 2");
        ban2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban2ActionPerformed(evt);
            }
        });

        ban3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        ban3.setText("Bàn 3");
        ban3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton5.setText("Bàn 5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton6.setText("Bàn 6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton7.setText("Bàn 7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton8.setText("Bàn 8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton9.setText("Bàn 12");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        ban4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        ban4.setText("Bàn 4");
        ban4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ban4ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton11.setText("Bàn 9");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton12.setText("Bàn 11");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton13.setText("Bàn 13");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton14.setText("Bàn 14");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/select.png"))); // NOI18N
        jButton15.setText("Bàn 10");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ban3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ban1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ban2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ban4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ban1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ban2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ban3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ban4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 40, 290, 520);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn dịch vụ"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Tên Đồ Uống  :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tên Thức Ăn  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Số Lượng  :");

        Searchfood.setForeground(new java.awt.Color(0, 0, 255));
        Searchfood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/pay (1).png"))); // NOI18N
        Searchfood.setText("Đặt Bàn");
        Searchfood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchfoodActionPerformed(evt);
            }
        });

        soluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soluongActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Tên Nhân Viên :");

        jButton16.setForeground(new java.awt.Color(0, 0, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/monney.png"))); // NOI18N
        jButton16.setText("Thanh Toán");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        afDrinkName.setEditable(true);
        afDrinkName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afDrinkNameActionPerformed(evt);
            }
        });

        affoodName1.setEditable(true);
        affoodName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                affoodName1ActionPerformed(evt);
            }
        });

        giathucan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giathucanActionPerformed(evt);
            }
        });

        jLabel5.setText(" Giá Thức Ăn       :");

        jLabel7.setText(" Giá Thức Uống   :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labeltientong, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(giathucan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Searchfood, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(tennv)
                    .addComponent(affoodName1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giathucuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(afDrinkName, 0, 139, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soluong)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afDrinkName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(affoodName1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(giathucan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(giathucuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Searchfood, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labeltientong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(290, 40, 570, 280);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        TableOder.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TableOder);

        formdrink.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(formdrink);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(290, 320, 570, 240);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(0, 0, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/password.png"))); // NOI18N
        jButton18.setText("Calculator");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setForeground(new java.awt.Color(51, 51, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/printf.png"))); // NOI18N
        jButton17.setText("Print");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 0, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/x-button.png"))); // NOI18N
        jButton19.setText("Reset");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        time.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 157, Short.MAX_VALUE)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(860, 320, 290, 240);

        Thanhtoan.setColumns(20);
        Thanhtoan.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Thanhtoan.setRows(5);
        jScrollPane3.setViewportView(Thanhtoan);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(860, 50, 290, 270);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ban2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban2ActionPerformed

    private void ban3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void ban4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ban4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ban4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void SearchfoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchfoodActionPerformed
         while (true) {
            if (tennv.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên! ");
                return;
            } else if (!soluong.getText().trim().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Số lượng phải nhập bằng số! ");
                return;
            } else {
                break;
            }
        }
        food((String) affoodName1.getSelectedItem());  
      drink((String) afDrinkName.getSelectedItem()); 
    
   
    }//GEN-LAST:event_SearchfoodActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainCoffe home = new MainCoffe();
        this.setVisible(false);
        home.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        while (true) {
            if (tennv.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên! ");
                return;
            } else if (!soluong.getText().trim().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Số lượng phải nhập bằng số! ");
                return;
            } else {
                break;
            }
        }
        Thanhtoan.setText("**********************************\n");
        Thanhtoan.setText(Thanhtoan.getText()+"*       Tổng Thanh Toán Bàn      *\n");
        Thanhtoan.setText(Thanhtoan.getText()+"**********************************\n");
        
          Date obj = new Date();
          String date = obj.toString();
          Thanhtoan.setText(Thanhtoan.getText()+"\n"+date+"\n\n");
          Thanhtoan.setText(Thanhtoan.getText()+"Giá Thức Ăn:"+ giathucan.getSelectedItem() + "\n\n" );
          Thanhtoan.setText(Thanhtoan.getText()+"Giá Thức Uống:"+giathucuong.getSelectedItem()  + "\n\n");
          Thanhtoan.setText(Thanhtoan.getText()+"Tên nhân viên:"+tennv.getText()+"\n\n");
          Thanhtoan.setText(Thanhtoan.getText()+"Tên Thức ăn:"+affoodName1.getSelectedItem()+"\n\n");
          Thanhtoan.setText(Thanhtoan.getText()+"Tên Đồ Uống:"+afDrinkName.getSelectedItem()+"\n\n");
          Thanhtoan.setText(Thanhtoan.getText()+"Số Lượng:"+soluong.getText()+"\n\n"); 
              Thanhtoan.setText(Thanhtoan.getText()+"Tổng tiền:"+ this.labeltientong.getText()+"\n\n"); 
              int tientong = Integer.parseInt(labeltientong.getText()) ;
           
           int a = (int) giathucan.getSelectedItem();
           int b = (int) giathucuong.getSelectedItem();
             int s = Integer.parseInt(soluong.getText());
           tientong = (a + b) * s ;
       
          String g  = Integer.toString(tientong);
          Thanhtoan.setText(g);
       
          Thanhtoan.setText(Thanhtoan.getText()+ban1.getText()+"\n\n\n");
        
           
            
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            Thanhtoan.print();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       Thanhtoan.setText("");
       tennv.setText("");
       soluong.setText("");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void afDrinkNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afDrinkNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_afDrinkNameActionPerformed

    private void affoodName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_affoodName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_affoodName1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
//       int click=JOptionPane.showConfirmDialog(null, "Sẽ Phát Triển Trong Tương Lai?", "Thông báo", 2);
//       if(click==JOptionPane.YES_OPTION){}
             maytinh Info = new maytinh();
            Info.setVisible(true);
            
    }//GEN-LAST:event_jButton18ActionPerformed

    private void soluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soluongActionPerformed

    private void giathucanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giathucanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_giathucanActionPerformed

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
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Searchfood;
    private javax.swing.JTable TableOder;
    private javax.swing.JTextArea Thanhtoan;
    private javax.swing.JComboBox afDrinkName;
    private javax.swing.JComboBox affoodName1;
    private javax.swing.JButton ban1;
    private javax.swing.JButton ban2;
    private javax.swing.JButton ban3;
    private javax.swing.JButton ban4;
    private javax.swing.JTable formdrink;
    private javax.swing.JComboBox<String> giathucan;
    private javax.swing.JComboBox<String> giathucuong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labeltientong;
    private javax.swing.JTextField soluong;
    private javax.swing.JTextField tennv;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
