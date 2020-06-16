/*package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import DAO.ConnectDB;
import Entity.BillDetail;

public class BillDetailDB {
    Vector<BillDetail> listDouong;
    ConnectDB db;
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    int num;

    public BillDetailDB() {
        db = new ConnectDB();
    }

    public Vector SelectBillDetail(String a) {
        listDouong = new Vector<>();
        Vector data = new Vector();

        try {

            CachedRowSet rs = new CachedRowSetImpl();
            String sql = "SELECT *FROM BillDetail where CodeBill ='" + a + "' and Amount >0";
            rs.setCommand(sql);
            rs.execute(db.conn);
            while (rs.next()) {
                Vector templ = new Vector();
                BillDetail row = new BillDetail();

                row.setCodeDrink(rs.getString(2));
                row.setCodeFood(rs.getInt(2));
                row.setAmount(rs.getInt(2));
                row.setPrice(rs.getFloat(3));
                row.setAmount(rs.getInt(4));

                templ.addElement(row.CodeDrink);
                templ.addElement(row.CodeFood);
                templ.addElement(row.Amount);
                templ.addElement(row.SoLuong);
                listDouong.addElement(row);
                data.add(templ);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi data");
        }
        return data;

    }

    public Vector getALlHoaDonChiTiet(String x) {
        listDouong = new Vector<>();
        Vector data = new Vector();

        try {

            CachedRowSet rs = new CachedRowSetImpl();
            String sql = "SELECT * FROM BillDetail "
            		+ "inner join DoUong on BillDetail.idDoUong = DoUong.idDoUong "
            		+ "where BillDetail.Status = 1  "+x+"";
            rs.setCommand(sql);
            rs.execute(db.conn);
            while (rs.next()) {
                Vector templ = new Vector();
                BillDetail row = new BillDetail();

                row.setIdHoaDon(rs.getInt(1));
                row.setIdDoUong(rs.getInt(2));
                row.setDonGia(rs.getInt(3));
                row.setSoLuong(rs.getInt(4));
                templ.addElement(row.idHoaDon);
                templ.addElement(row.idDoUong);
                templ.addElement(row.DonGia);
                templ.addElement(row.SoLuong);
                listDouong.addElement(row);
                data.add(templ);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi data");
        }
        return data;

    }

    public void UpdateBillDetail(BillDetail c) {
        String update = "update BillDetail set SoLuong = SoLuong + ? where IdDoUong = ? and IdHoaDon =? ";
        String updateSoLuong = "update  DoUong set SoLuongCon = SoLuongCon - ?  where IdDoUong = ? ";

        try {

            PreparedStatement a = db.conn.prepareStatement(updateSoLuong);
            a.setInt(1, c.SoLuong);
            a.setInt(2, c.idDoUong);
            a.executeUpdate();
            a.close();

            try {

                PreparedStatement pr = db.conn.prepareStatement("insert into BillDetail values (?,?,?,?,?)");
                pr.setInt(1, c.idHoaDon);
                pr.setInt(2, c.idDoUong);
                pr.setInt(3, c.DonGia);
                pr.setInt(4, c.SoLuong);
                pr.setInt(5, 1);
                pr.executeUpdate();
                pr.close();
            } catch (Exception e) {
                PreparedStatement pr = db.conn.prepareStatement(update);
                pr.setInt(1, c.SoLuong);
                pr.setInt(2, c.idDoUong);
                pr.setInt(3, c.idHoaDon);
                pr.executeUpdate();
                pr.close();
                System.out.println("update so luong");
            }

        } catch (Exception e) {
            System.out.println("loi them moi");
        }
    }

    public void DeleteOneOrder(int idDoUong, int idHoaDon) {
        String update = "update BillDetail set SoLuong = SoLuong - 1 where IdDoUong = '" + idDoUong + "' and IdHoaDon =  '" + idHoaDon + "' ";
        String updateSoLuong = "update  DoUong set SoLuongCon = SoLuongCon +1  where IdDoUong = '" + idDoUong + "'  ";
        try {

            try {
                PreparedStatement pr = db.conn.prepareStatement(update);
                pr.executeUpdate();
                pr.close();

                PreparedStatement a = db.conn.prepareStatement(updateSoLuong);
                a.executeUpdate();
                a.close();
            } catch (Exception e) {
                System.out.println("loi xoa 1 mon");
            }

        } catch (Exception e) {
            System.out.println("loi toan tap");
        }
    }

    public void DeleteOrder(String c) throws SQLException {
        try {
            String sql = "select *from BillDetail where IdHoaDon =" + c + " ";
            Statement st = db.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String idDoUong = rs.getString(2);
                String soLuong = rs.getString(4);
                String update = "update DoUong set SoLuongCon = SoLuongCon + " + soLuong + " where idDoUong = " + idDoUong + "";
                PreparedStatement pr = db.conn.prepareStatement(update);
                pr.executeUpdate();
                pr.close();
            }

            String DeleteOrder = " delete from BillDetail where IdHoaDon = '" + c + "'"
                    + " delete from Bill where IdHoaDon = '" + c + "' ";

            PreparedStatement a = db.conn.prepareStatement(DeleteOrder);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xoa tat ca");
        }
    }

    public void UpdateStatus(String x, String idHoaDon) {
        try {
            String DeleteOrder = " update  BillDetail set Status = " + x + " where IdHoaDon = " + idHoaDon + "";
            PreparedStatement a = db.conn.prepareStatement(DeleteOrder);
            a.executeUpdate();
            a.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lỗi xóa hóa đơn chi tiết");
        }

    }

    public void DeleteOrderSelectRow(String a, String b) {
        String DeleteOrderRow = "update DoUong set SoLuongCon = SoLuongCon + (select SoLuong from BillDetail where IdHoaDon = '" + a + "' and IdDoUong = '" + b + "') "
                + "where IdDoUong = '" + b + "'delete from BillDetail where IdHoaDon = '" + a + "' and IdDoUong = '" + b + "'";
        try {
            PreparedStatement bb = db.conn.prepareStatement(DeleteOrderRow);
            bb.executeQuery();
            bb.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("loi xoa 1");
        }
    }
}
*/