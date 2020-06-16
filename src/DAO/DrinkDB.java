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
import Entity.Drink;

public class DrinkDB {
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    Statement stmt = null;
    Vector<Drink> list = null;
    int num;
    ConnectDB db;

    public DrinkDB() {
        db = new ConnectDB();
    }

    @SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public Vector GetAllDrink(String a) {
        Vector<Object> data = new Vector<>();

        try {
            list = new Vector<Drink>();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Drink");
            eps.execute(db.conn);
            while (eps.next()) {
                Drink row = new Drink();
                Vector tam = new Vector();

                row.setCodeDrink(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setDrinkName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeDrink);
                tam.add(row.CodeType);
                tam.add(row.DrinkName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public Vector ShowCodeType(String CodeType) {
        Vector<Object> data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Drink where [CodeType] =" + CodeType);
            eps.execute(db.conn);
            while (eps.next()) {
                Drink row = new Drink();
                Vector tam = new Vector();

                row.setCodeDrink(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setDrinkName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeDrink);
                tam.add(row.CodeType);
                tam.add(row.DrinkName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // ------------------- SELECT ALL DRINK ------------------------------ //
    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector getAllDrink() {
        Vector data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("SELECT  * FROM Drink"
                    + "inner join ProductType \n"
                    + "on Drink.CodeType = ProductType.CodeType\n"+ " ");
            eps.execute(db.conn);
            while (eps.next()) {
                Drink row = new Drink();
                Vector tam = new Vector();

                row.setCodeDrink(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setDrinkName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeDrink);
                tam.add(row.CodeType);
                tam.add(row.DrinkName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // ------------------- SEARCH DRINK ------------------------------ //
    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector SearchDrink(String search) {
        Vector data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            String sql = "SELECT * FROM Drink WHERE DrinkName LIKE N'%" + search + "%'";
            eps.setCommand(sql);
            eps.execute(db.conn);
            while (eps.next()) {
                Drink row = new Drink();
                Vector tam = new Vector();

                row.setCodeDrink(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setDrinkName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeDrink);
                tam.add(row.CodeType);
                tam.add(row.DrinkName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void InsertDrink(String CodeDrink, String CodeType, String DrinkName, int Amount, float Price) {
        try {
            String sql = "Insert into DoUong values(?,?,?,?,?,)";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeDrink);
            pstmt.setString(2, CodeType);
            pstmt.setString(3, DrinkName);
            pstmt.setInt(4, Amount);
            pstmt.setFloat(5, Price);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateDrink(String CodeDrink, String CodeType,String DrinkName, int Amount, float Price) {
        try {
            String sql = "Update Drink SET CodeType = " + CodeType + "AND DrinkName = " + DrinkName
            					+ "AND Amount = " + Amount  + "AND Price = " + Price  + " where CodeDrink = ?";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeDrink);
            cstmt.executeUpdate();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DeteleDrink(String CodeDrink) {

        try {
            String sql = "Delete from Drink where CodeDrink = ?";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeDrink);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
