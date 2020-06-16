package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import Entity.Employee;
import javax.swing.table.DefaultTableModel;



public class EmployeeDB {
	PreparedStatement pstmt = null;
    CallableStatement cstmt = null;   
    Statement stmt = null;
    Vector<Employee> list = null;
    int num;
    ConnectDB db;
    
    public EmployeeDB(){
        db = new ConnectDB();        
    }    
    
    public void InsertEmp(Employee e){
        list = new Vector<Employee>();
        list.add(e);
        try{
            String sql = "Insert into Employee values(?,?,?,?,?,?,?,?,?,?,?,?,?)";                        
                        
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, e.getCodeEmp());
            pstmt.setString(2, e.getFullName());            
            pstmt.setString(3, e.getPassword());            
            pstmt.setString(4, e.getGender());
            pstmt.setString(5, e.getBirthday());
            pstmt.setString(6, e.getPhone());
            pstmt.setString(7, e.getEmail());
            pstmt.setString(8, e.getCMND());
            pstmt.setString(9, e.getAddress());                  
            pstmt.setBinaryStream(13, new FileInputStream(e.getImg()));
            pstmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void UpdateEmp(Employee e){        
        try {
            String sql = "{call sp_updateEmp(?,?,?,?,?,?,?,?,?,?,?,?)}";
            cstmt = db.conn.prepareCall(sql);
            cstmt.setString(1, e.getCodeEmp());
            cstmt.setString(2, e.getFullName());            
            cstmt.setString(3, e.getPassword());            
            cstmt.setString(4, e.getGender());
            cstmt.setString(5, e.getBirthday());
            cstmt.setString(6, e.getPhone());
            cstmt.setString(7, e.getEmail());
            cstmt.setString(8, e.getCMND());
            cstmt.setString(9, e.getAddress());                                  
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    @SuppressWarnings("resource")
	public Vector<Object> SearchEmp(String FullName,String Phone){
        Vector<Object> data = new Vector<>();
        
        try {
            list = new Vector<Employee>();
            CachedRowSet eps = new CachedRowSetImpl();
            String sql = "SELECT * FROM Employee WHERE FullName LIKE N'%"+FullName+"%' OR Phone LIKE '%"+Phone+"%'";                   
            eps.setCommand(sql);           
            eps.execute(db.conn);
            while (eps.next()){
                Employee row = new Employee();
                Vector<String> tam = new Vector<String>();
                
                row.setCodeEmp(eps.getString(1));
                row.setFullName(eps.getString(2));
                row.setGender(eps.getString(4));
                row.setBirthday(eps.getString(5));
                row.setPhone(eps.getString(6));
                row.setEmail(eps.getString(7));
                row.setCMND(eps.getString(8));
                row.setAddress(eps.getString(9)); 
                
                tam.addElement(row.CodeEmp);
                tam.addElement(row.FullName);
                tam.addElement(row.Gender);
                tam.addElement(row.Birthday);
                tam.addElement(row.Phone);
                tam.addElement(row.Email);
                tam.addElement(row.CMND);
                tam.addElement(row.Address);          
                list.addElement(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
 
    @SuppressWarnings("resource")
	public Vector<Object> getAllEmployee1(){
        Vector<Object> data = new Vector<>();
        
        try {
            list = new Vector<Employee>();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Employee");
            eps.execute(db.conn);
            while (eps.next()){
                Employee row = new Employee();
                Vector<Object> tam = new Vector<Object>();
                
                row.setCodeEmp(eps.getString(1));
                row.setFullName(eps.getString(2));
                row.setPassword(eps.getString(3));
                row.setGender(eps.getString(4));
                row.setBirthday(eps.getString(5));
                row.setPhone(eps.getString(6));
                row.setEmail(eps.getString(7));
                row.setCMND(eps.getString(8));
                row.setAddress(eps.getString(9));
                row.setImg2(eps.getBytes(13));
                        
                tam.addElement(row.CodeEmp);
                tam.addElement(row.FullName);
                tam.addElement(row.Password);
                tam.addElement(row.Gender);
                tam.addElement(row.Birthday);
                tam.addElement(row.Phone);                
                tam.addElement(row.Email);
                tam.addElement(row.CMND);
                tam.addElement(row.Address);
                tam.addElement(row.Img2);
                list.addElement(row);
                data.add(tam);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;    
    }    
    
    @SuppressWarnings("resource")
	public Vector<Object> getAllEmployee(){
              
              
        Vector<Object> data = new Vector<>();
        
        try {
            list = new Vector<Employee>();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Employee");
            eps.execute(db.conn);
            while (eps.next()){
                Employee row = new Employee();
                Vector<String> tam = new Vector<String>();
                
                row.setCodeEmp(eps.getString(1));
                row.setFullName(eps.getString(2));
                row.setGender(eps.getString(4));
                row.setBirthday(eps.getString(5));
                row.setPhone(eps.getString(6));
                row.setEmail(eps.getString(7));
                row.setCMND(eps.getString(8));
                row.setAddress(eps.getString(9));
                
                tam.add(row.CodeEmp);
                tam.add(row.FullName);
                tam.add(row.Gender);
                tam.add(row.Birthday);
                tam.add(row.Phone);
                tam.add(row.Email);
                tam.add(row.CMND);
                tam.addElement(row.Address);
                
                list.add(row);
                data.add(tam);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return data;
    }           
}
