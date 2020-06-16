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

import Entity.Shift;
import DAO.ConnectDB;

public class ShiftDB {
	PreparedStatement pstmt = null;
    CallableStatement cstmt = null;   
    Statement stmt = null;
    Vector<Shift> list = null;
    int num;
    ConnectDB db;
    
    public ShiftDB(){
        db = new ConnectDB();        
    }    
    
    public void InsertShift(Shift e){
        list.add(e);
        try{
            stmt = db.conn.createStatement();
            String sql = "Insert into Shift values('"+e.getCodeShift()+"','"+e.getShiftName()+"','"+e.getTimeStart()
                                                 +"','"+e.getTimeStop()+"','"+e.getSalary()+"')";
            stmt.executeUpdate(sql);
            stmt.close();            
        }catch(SQLException ex){
            Logger.getLogger(ShiftDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public Vector SearchShift(String Shift){
        Vector<Object> data = new Vector<>();
        
        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            String sql = "SELECT * FROM Shifts WHERE CodeShift = ? AND ShiftName like ?";                   
            eps.setCommand(sql);           
            eps.execute(db.conn);
            while (eps.next()){
            	Shift row = new Shift();
                Vector tam = new Vector();
                
                row.setCodeShift(eps.getString(1));
                row.setShiftName(eps.getString(2));
                
                tam.add(row.CodeShift);
                tam.add(row.ShiftName);
                list.add(row);
                data.add(tam);               
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShiftDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public void DeleteShift(String code){
        
        try {
            String sql = "DELETE FROM Shifts WHERE CodeShift = " + code + "";            
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
