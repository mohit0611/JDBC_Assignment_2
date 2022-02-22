package com.knoldus;
import java.sql.*;

public class Main {
    static Connection con = null;
    static PreparedStatement stmt = null;
    static ResultSet rs;

    public void dbCon() throws Exception{
        con = DbConnection.getDbConnection();
    }

//Question 1: starts here
    public void putDataToProductTable(int productId, double productPrice, String productName) throws Exception{
        String query = "INSERT INTO product VALUES (?,?,?)";
        stmt = con.prepareStatement(query);
        stmt.setInt(1,productId);
        stmt.setDouble(2,productPrice);
        stmt.setString(3,productName);
        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");
    }

    public void putDataToCartTable(int productId, int productQuantity) throws Exception{
        String query = "INSERT INTO cart VALUES (?,?)";
        stmt =con.prepareStatement(query);
        stmt.setInt(1,productId);
        stmt.setInt(2,productQuantity);

        int count = stmt.executeUpdate();
        System.out.println(count + " rows Effected");
    }
//Question 1: ends here

//Question 2: starts here
    public void printData(int productId) throws Exception{
        String query = "SELECT * FROM product WHERE product.productId =" +productId;
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            System.out.println(rs.getDouble(2) + " " + rs.getString(3));
        }
        else{
            System.out.println("Empty");
        }
    }
//Question 2: ends here

//Question 2: starts here
    public void findAveragePrice() throws Exception{
        String query = "SELECT productId ,AVG(productPrice) AS 'Avg Price' FROM product GROUP BY productId";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1) + "  " + rs.getDouble(2));
        }
    }
//Question 3: ends here

    public static void main(String[] args) throws Exception{
        Main mainObj = new Main();
        mainObj.dbCon();
        
		mainObj.putDataToProductTable(11,23,"Mohiit_Kumar");
        mainObj.putDataToCartTable(11,5);
        mainObj.printData(11);
        mainObj.findAveragePrice();
		
        stmt.close();
        con.close();
    }
}
