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
import Entity.Customer;

public class CustomerDB {
		PreparedStatement pstmt = null;
	    CallableStatement cstmt = null;   
	    Statement stmt = null;
	    Vector<Customer> list = null;
	    int num;
	    ConnectDB db;
	    
	    public CustomerDB(){
	        db = new ConnectDB();        
	    }    
	    
	    public void InsertCustomer(Customer e){
	        list.add(e);
	        try{
	            stmt = db.conn.createStatement();
	            String sql = "Insert into Customer values('"+e.getCodeCus()+"','"+e.getCardType()+"',N'"+e.getFullName()
	                                                 +"','"+e.getGender()+"',N'"+e.getBirthday()
	                                                 +"','"+e.getPhone()+"','"+e.getEmail()+"',N'"+e.getCMND()
	                                                 +"','"+e.getAddress()+"','"+e.getPoint()+"')";
	            stmt.executeUpdate(sql);
	            stmt.close();            
	        }catch(SQLException ex){
	            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public void UpdateCustomer(Customer e){        
	        try {
	            String sql = "{call sp_updateCus(?,?,?,?,?,?,?,?,?,?)}";
	            cstmt = db.conn.prepareCall(sql);
	            cstmt.setString(1,e.getCodeCus());
	            cstmt.setString(2,e.getCardType());
	            cstmt.setString(3,e.getFullName());             
	            cstmt.setString(4,e.getGender());
	            cstmt.setString(5,e.getBirthday());
	            cstmt.setString(6,e.getPhone());
	            cstmt.setString(7,e.getEmail());
	            cstmt.setString(8,e.getCMND());            
	            cstmt.setString(9,e.getAddress());
	            cstmt.setInt(10, e.getPoint());
	            cstmt.execute();
	            cstmt.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    
	    public void DeleteCustomer(String CodeCus){
	        
	        try {
	            String sql = "Delete from Customer where CodeCus = ?";            
	            pstmt = db.conn.prepareStatement(sql);
	            pstmt.setString(1, CodeCus);
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
		public Vector SearchCustomer(String Cus){
	        Vector<Object> data = new Vector<>();
	        
	        try {
	            list = new Vector();
	            CachedRowSet eps = new CachedRowSetImpl();
	            String sql = "SELECT * FROM Customer WHERE FullName LIKE N'%"+Cus+"%' OR Phone LIKE '%"+Cus+"%'";                   
	            eps.setCommand(sql);           
	            eps.execute(db.conn);
	            while (eps.next()){
	                Customer row = new Customer();
	                Vector tam = new Vector();
	                
	                row.setCodeCus(eps.getString(1));
	                row.setCardType(eps.getString(2));
	                row.setFullName(eps.getString(3));                
	                row.setGender(eps.getString(4));
	                row.setBirthday(eps.getString(5));
	                row.setPhone(eps.getString(6));
	                row.setEmail(eps.getString(7));
	                row.setCMND(eps.getString(8));
	                row.setAddress(eps.getString(9));
	                row.setPoint(eps.getInt(10));
	                
	                tam.add(row.CodeCus);
	                tam.add(row.CardType);
	                tam.add(row.FullName);                
	                tam.add(row.Gender);
	                tam.add(row.Birthday);
	                tam.add(row.Phone);
	                tam.add(row.Email);
	                tam.add(row.CMND);
	                tam.add(row.Address);
	                tam.add(row.Point);
	                list.add(row);
	                data.add(tam);               
	           
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return data;
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
		public Vector getAllCustomer(){
	        Vector<Object> data = new Vector<>();
	        
	        try {
	            list = new Vector();
	            CachedRowSet eps = new CachedRowSetImpl();
	            eps.setCommand("Select * from Customer");
	            eps.execute(db.conn);
	            while (eps.next()){
	                Customer row = new Customer();
	                Vector tam = new Vector();
	                
	                row.setCodeCus(eps.getString(1));
	                row.setCardType(eps.getString(2));
	                row.setFullName(eps.getString(3));                
	                row.setGender(eps.getString(4));
	                row.setBirthday(eps.getString(5));
	                row.setPhone(eps.getString(6));
	                row.setEmail(eps.getString(7));
	                row.setCMND(eps.getString(8));
	                row.setAddress(eps.getString(9));
	                row.setPoint(eps.getInt(10));
	                
	                tam.add(row.CodeCus);
	                tam.add(row.CardType);
	                tam.add(row.FullName);                
	                tam.add(row.Gender);
	                tam.add(row.Birthday);
	                tam.add(row.Phone);
	                tam.add(row.Email);
	                tam.add(row.CMND);
	                tam.add(row.Address);
	                tam.add(row.Point);
	                list.add(row);
	                data.add(tam);               
	                
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return data;
	    }
	    
}
