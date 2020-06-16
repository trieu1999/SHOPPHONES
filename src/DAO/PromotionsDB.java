package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import com.sun.rowset.CachedRowSetImpl;

import Entity.Promotions;
import DAO.ConnectDB;

public class PromotionsDB {
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    Statement stmt = null;
    Vector<Promotions> list = null;
    int num;
    ConnectDB db;

    public PromotionsDB() {
        db = new ConnectDB();
    }

    public void InsertPromotions(String PromoName,int DiscountPromo,String StartPromo, String EndPromo, String Description) {

    	try {
            pstmt = db.conn.prepareStatement("Insert into Promotions values(?,?,?,?,?)");
            pstmt.setString(1, PromoName);
            pstmt.setInt(2, DiscountPromo);
            pstmt.setString(3, StartPromo);
            pstmt.setString(4, EndPromo);
            pstmt.setString(5, Description);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm chương trình khuyến mãi thành công.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }

    public void UpdatePromotions(int DiscountPromo,String StartPromo, String EndPromo, String Description,String CodePromo) {
    	 try {
             pstmt = db.conn.prepareStatement("Update Promotions set DiscountPromo=?, StartPromo=?, EndPromo=?, Description=? where CodePromo=?");
             pstmt.setInt(1, DiscountPromo);
             pstmt.setString(2, StartPromo);
             pstmt.setString(3, EndPromo);
             pstmt.setString(4, Description);
             pstmt.setString(5, CodePromo);
             pstmt.executeUpdate();
             JOptionPane.showMessageDialog(null, "Cập nhật chương trình thành công.");
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
         }
    }

    public void DeletePromotions(int CodePromo) {
    	try {
            pstmt = db.conn.prepareStatement("Delete from Promotions where CodePromo=?");
            pstmt.setInt(1,CodePromo );
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa chương trình thành công.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public Vector GetAllPromotions() {
        Vector<Object> data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("select * from Promotions");
            eps.execute(db.conn);
            while (eps.next()) {
            	Promotions row = new Promotions();
                Vector tam = new Vector();

                row.setCodePromo(eps.getInt(1));
                row.setPromoName(eps.getString(2));
                row.setDiscountPromo(eps.getInt(3));
                row.setStartPromo(eps.getString(4));
                row.setEndPromo(eps.getString(5));
                row.setDescription(eps.getString(6));

                tam.addElement(row.CodePromo);
                tam.addElement(row.PromoName);
                tam.addElement(row.DiscountPromo);
                tam.addElement(row.StartPromo);
                tam.addElement(row.EndPromo);
                tam.addElement(row.Description);

                list.addElement(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        return data;
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector SearchAllPromotions(String name) {
        Vector<Object> data = new Vector<>();
        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            String sql = "SELECT * FROM Promotions WHERE PromoName LIKE N'%" + name + "%'";
            eps.setCommand(sql);
            eps.execute(db.conn);
            while (eps.next()) {
            	Promotions row = new Promotions();
                Vector tam = new Vector();

                row.setCodePromo(eps.getInt(1));
                row.setPromoName(eps.getString(2));
                row.setDiscountPromo(eps.getInt(3));
                row.setStartPromo(eps.getString(4));
                row.setEndPromo(eps.getString(5));
                row.setDescription(eps.getString(6));
                
                tam.addElement(row.CodePromo);
                tam.addElement(row.PromoName);
                tam.addElement(row.DiscountPromo);
                tam.addElement(row.StartPromo);
                tam.addElement(row.EndPromo);
                tam.addElement(row.Description);

                list.addElement(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        return data;
    }
}
