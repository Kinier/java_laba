package com.example.kyrsach.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class productsModel extends Database{



    public String selectAllProducts(){
        Statement st;
        ResultSet rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();
            rs = st.executeQuery(
                    "SELECT * from products ORDER BY id ASC"
            );
/*
    + - пробелы
    = - перевод на следующую строку
 */
            while (rs.next())
            {
                //System.out.println(

                answer+= rs.getString(1) + "+";
                answer+= rs.getString(2) + "+";
                answer+= rs.getString(3) + "+";
                answer+= rs.getString(4) + "+";
                answer+= rs.getString(5) + "+";
                answer+= rs.getString(6) + "=";


                //);
            }
            /*if (answer.equals("")){
                answer = "not";
            }else{
                answer = "ok";
            }*/
            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }

    public String insertProduct(String[] dataSliced){
        Statement st;
        //int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO products(user_id, manufacturer_id, name, amount, price)" +
                            " VALUES (" + dataSliced[1] + ", "+ dataSliced[2] + ", " + "\'" + dataSliced[3] + "\'" + ","  + dataSliced[4]  + ", " + dataSliced[5] + ")"
            );

/*
    + - пробелы
    = - перевод на следующую строку
 */

                answer = "ok";



            //rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }

    public String deleteProduct(String[] dataSliced){
        Statement st;
        int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeUpdate(
                    "DELETE FROM products WHERE (id, user_id, manufacturer_id, name, amount, price)=" +
                            "(" +  dataSliced[1]  + ", " +   dataSliced[2]  + ", " +  dataSliced[3] + ", " + "\'" + dataSliced[4] + "\'" + ","  + dataSliced[5]  + ", " + dataSliced[6] + ")"
            );

/*
    + - пробелы
    = - перевод на следующую строку
 */
            if (rs > 0)
                answer = "ok";
            else
                answer = "not";



            //rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }
    public String findProduct(String[] dataSliced){
        Statement st;
        ResultSet rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeQuery(
                    "SELECT * from products WHERE (manufacturer_id, name, amount, price)="+
                            "(" +  dataSliced[3] + ", " + "\'" + dataSliced[4] + "\'" + ","  + dataSliced[5]  + ", " + dataSliced[6] + ")"
            );
/*
    + - пробелы
    = - перевод на следующую строку
 */
            while (rs.next())
            {
                //System.out.println(
                answer+= rs.getString(1) + "+";
                answer+= rs.getString(2) + "+";
                answer+= rs.getString(3) + "+";
                answer+= rs.getString(4) + "+";
                answer+= rs.getString(5) + "+";
                answer+= rs.getString(6) + "=";
                System.out.println(answer);

                //);
            }

            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }

    public String editProduct(String[] dataSliced){
        Statement st;
        int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();
            //>для разбора месива что пришло
            /*List<String[]> rowList = new ArrayList<String[]>();
            for (int i = 0; i < productsNotSliced.length; i++){
                rowList.add(i, productsNotSliced[i].split("\\+")); // делим каждый row в одну ячейку массива rowlist
            }*/

            //<
            st = connection.createStatement();


            rs = st.executeUpdate(
                    "UPDATE products SET manufacturer_id=" +
                            dataSliced[3]+", name=\'" + dataSliced[4] + "\', amount=" + dataSliced[5]+ ", price=" + dataSliced[6]  +
                            " WHERE (id="  + dataSliced[1] +  ")"
            );

/*
    + - пробелы
    = - перевод на следующую строку
 */
            if (rs > 0){
                answer = "ok";
            }else{
                answer = "not";
            }

            System.out.println(answer);


            //rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }
    public void deletManufacturerOfProducts(String ManufacturerId){
        Statement st;
        int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();


            /*st.executeUpdate(
                    "UPDATE products SET manufacturer_id=0" +
                            " WHERE (manufacturer_id="  + ManufacturerId +  ")"
            );*/
            st.executeUpdate(
                    "DELETE FROM products WHERE (manufacturer_id="  + ManufacturerId +  ")"
            );


            System.out.println(answer);


            //rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
    }

}
