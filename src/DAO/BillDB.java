package DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import DAO.ConnectDB;
import Entity.Bill;
import DAO.ProductTypeDB;

public class BillDB {
    Vector<Bill> list = null;
    ConnectDB db;
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    Statement stmt = null;
    int num;

    public BillDB() {
        db = new ConnectDB();
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector getAllBill(String a) {
        Vector<Object> data = new Vector<>();
        list = new Vector();
        try {

            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Bill where [TimePayment] is not null");
            eps.execute(db.conn);
            while (eps.next()) {
            	Bill row = new Bill();
                Vector tam = new Vector();

                row.setCodeBill(eps.getInt(1));
                row.setCodeEmp(eps.getString(2));
                row.setCodeCus(eps.getString(3));
                row.setCodeTable(eps.getString(4));
                row.setTimePayment(eps.getDate(5));
                row.setTotalMoney(eps.getFloat(6));

                tam.addElement(row.CodeBill);
                tam.addElement(row.CodeEmp);
                tam.addElement(row.CodeCus);
                tam.addElement(row.CodeTable);
                tam.addElement(row.TimePayment);
                tam.addElement(row.TotalMoney);

                list.addElement(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void InsertBill(Bill c) {
        try {
            PreparedStatement pr = db.conn.prepareStatement("Insert into Bill values (?,?,?,?,?,?)");
            pr.setString(1, c.CodeEmp);
            pr.setString(2, c.CodeCus);
            pr.setString(3, c.CodeTable);
            pr.setDate(4, c.TimePayment);
            pr.setFloat(5, c.TotalMoney);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            System.out.println("loi them BillDB!");
        }
    }

    public void UpdateStatus(String x, String y) {
        String updateStatus = "update  Bill set Status = "+x+" where CodeBill = " + y + "";
        try {
            PreparedStatement a = db.conn.prepareStatement(updateStatus);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xoa Bill");
        }

    }

    public void UpdateTotalMoney(String money, String CodeBill) {
        String updateTotalMoney = "update  Bill set [TotalMoney] = '" + money
                + "' where CodeBill = '" + CodeBill + "'";

        try {
            PreparedStatement a = db.conn.prepareStatement(updateTotalMoney);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xuat Bill");
        }
    }

    public void UpdateTimePayment(Date Time, String money, String CodeBill) {
        String updateTimePayment = "Update  Bill set [TimePayment] = '" + Time + "'"
                + " where CodeBill = '" + CodeBill + "' update  Bill set [TotalMoney] = '" + money
                + "' where CodeBill = '" + CodeBill + "'";

        try {
            PreparedStatement a = db.conn.prepareStatement(updateTimePayment);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xuat Bill");
        }
    }

    public void UpdateBill(String money, String CodeBill) {

        String updateBill = " Update  Bill set [TotalMoney] = '" + money + "' where CodeBill = '" + CodeBill + "'";

        try {
            PreparedStatement a = db.conn.prepareStatement(updateBill);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xuat Bill");
        }

    }

    public void UpdatePoint(String CodeCus, String NewPoint) {
        String Point = "update Customer set Point = Point + " + NewPoint + " where [CardType] = '" + CodeCus + "'";
        try {
            PreparedStatement a = db.conn.prepareStatement(Point);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xuat Bill");
        }

    }
}
