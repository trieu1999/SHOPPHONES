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

import Entity.Food;
import DAO.ConnectDB;

public class FoodDB {
    PreparedStatement pstmt = null;
    CallableStatement cstmt = null;
    Statement stmt = null;
    Vector<Food> list = null;
    int num;
    ConnectDB db;

    public FoodDB() {
        db = new ConnectDB();
    }

    @SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public Vector GetAllFood(String a) {
        Vector<Object> data = new Vector<>();

        try {
            list = new Vector<Food>();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Food");
            eps.execute(db.conn);
            while (eps.next()) {
            	Food row = new Food();
                Vector tam = new Vector();

                row.setCodeFood(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setFoodName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeFood);
                tam.add(row.CodeType);
                tam.add(row.FoodName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public Vector ShowCodeType(String CodeType) {
        Vector<Object> data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("Select * from Food where [CodeType] =" + CodeType);
            eps.execute(db.conn);
            while (eps.next()) {
            	Food row = new Food();
                Vector tam = new Vector();

                row.setCodeFood(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setFoodName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeFood);
                tam.add(row.CodeType);
                tam.add(row.FoodName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // ------------------- SELECT ALL FOOD ------------------------------ //
    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector getAllFood(String x) {
        Vector data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            eps.setCommand("SELECT  * FROM Food"
                    + "inner join ProductType \n"
                    + "on Food.CodeType = ProductType.CodeType\n"+ " ");
            eps.execute(db.conn);
            while (eps.next()) {
            	Food row = new Food();
                Vector tam = new Vector();

                row.setCodeFood(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setFoodName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeFood);
                tam.add(row.CodeType);
                tam.add(row.FoodName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // ------------------- SEARCH FOOD ------------------------------ //
    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public Vector SearchFood(String search) {
        Vector data = new Vector<>();

        try {
            list = new Vector();
            CachedRowSet eps = new CachedRowSetImpl();
            String sql = "SELECT * FROM Food WHERE FoodName LIKE N'%" + search + "%'";
            eps.setCommand(sql);
            eps.execute(db.conn);
            while (eps.next()) {
            	Food row = new Food();
                Vector tam = new Vector();

                row.setCodeFood(eps.getString(1));
                row.setCodeType(eps.getString(2));
                row.setFoodName(eps.getString(3));
                row.setAmount(eps.getInt(4));
                row.setPrice(eps.getFloat(5));

                tam.add(row.CodeFood);
                tam.add(row.CodeType);
                tam.add(row.FoodName);
                tam.add(row.Amount);
                tam.add(row.Price);

                list.add(row);
                data.add(tam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void InsertDrink(String CodeFood, String CodeType, String FoodName, int Amount, float Price) {
        try {
            String sql = "Insert into DoUong values(?,?,?,?,?,)";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeFood);
            pstmt.setString(2, CodeType);
            pstmt.setString(3, FoodName);
            pstmt.setInt(4, Amount);
            pstmt.setFloat(5, Price);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateFood(String CodeFood, String CodeType,String FoodName, int Amount, float Price) {
        try {
            String sql = "Update Food SET CodeType = " + CodeType + "AND FoodName = " + FoodName
            					+ "AND Amount = " + Amount  + "AND Price = " + Price  + " where CodeFood = ?";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeFood);
            cstmt.executeUpdate();
            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DeteleFood(String CodeFood) {

        try {
            String sql = "Delete from Food where CodeFood = ?";
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, CodeFood);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
