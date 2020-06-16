package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import DAO.ConnectDB;
import Entity.ProductType;

public class ProductTypeDB {

	    PreparedStatement pstmt = null;
	    CallableStatement cstmt = null;
	    Statement stmt = null;
	    Vector<ProductType> list = null;
	    int num;
	    ConnectDB db;

	    public ProductTypeDB() {
	        db = new ConnectDB();
	    }

	    public void InsertProductType(String a,String b,String c) {

	        try {
	            String sql = "Insert into ProductType values(?,?,?)";
	            pstmt = db.conn.prepareStatement(sql);
	            pstmt.setString(1, a);
	            pstmt.setString(2, b);
	            pstmt.setString(3, c);
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    public void UpdateTypeName(String a, String CodeType) {
	        try {
	            String sql = "Update ProductType SET TypeName = " + a +  " where CodeType = ?";
	            pstmt = db.conn.prepareStatement(sql);
	            pstmt.setString(1, CodeType);
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

	    public void UpdateSize(String b, String CodeType) {

	        try {
	            String sql = "  Update ProductType SET Size = " + b + " where CodeType = ?";
	            pstmt = db.conn.prepareStatement(sql);
	            pstmt.setString(1, CodeType);
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

	    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
		public Vector GetAllProductType() {
	        Vector<Object> data = new Vector<>();

	        try {
	            list = new Vector();
	            CachedRowSet eps = new CachedRowSetImpl();
	            eps.setCommand("Select * from ProductType");
	            eps.execute(db.conn);
	            while (eps.next()) {
	            	ProductType row = new ProductType();
	                Vector tam = new Vector();

	                row.setCodeType(eps.getString(1));
	                row.setTypeName(eps.getString(2));
	                row.setSize(eps.getString(3));

	                tam.addElement(row.CodeType);
	                tam.addElement(row.TypeName);
	                tam.addElement(row.Size);

	                list.addElement(row);
	                data.add(tam);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return data;
	    }

	    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
		public Vector SearchProductType(String c) {
	        Vector<Object> data = new Vector<>();
	        try {
	            list = new Vector();
	            CachedRowSet eps = new CachedRowSetImpl();
	            String sql = "SELECT * FROM ProductType WHERE Size = " + c + "";
	            eps.setCommand(sql);
	            eps.execute(db.conn);
	            while (eps.next()) {
	            	ProductType row = new ProductType();
	                Vector tam = new Vector();

	                row.setCodeType(eps.getString(1));
	                row.setTypeName(eps.getString(2));
	                row.setSize(eps.getString(3));

	                tam.addElement(row.CodeType);
	                tam.addElement(row.TypeName);
	                tam.addElement(row.Size);

	                list.addElement(row);
	                data.add(tam);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return data;
	    }

	    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
		public Vector SearchAllProductType(String name) {
	        Vector<Object> data = new Vector<>();
	        try {
	            list = new Vector();
	            CachedRowSet eps = new CachedRowSetImpl();
	            String sql = "SELECT * FROM ProductType WHERE TypeName LIKE N'%" + name + "%'";
	            eps.setCommand(sql);
	            eps.execute(db.conn);
	            while (eps.next()) {
	            	ProductType row = new ProductType();
	                Vector tam = new Vector();

	                row.setCodeType(eps.getString(1));
	                row.setTypeName(eps.getString(2));
	                row.setSize(eps.getString(3));

	                tam.addElement(row.CodeType);
	                tam.addElement(row.TypeName);
	                tam.addElement(row.Size);

	                list.addElement(row);
	                data.add(tam);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductTypeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return data;
	    }
}
