package com.example.kyrsach.database;

import java.sql.*;
import com.example.kyrsach.database.productsModel;
public class manufacturersModel extends Database{



    public String selectAllManufacturers(){
        Statement st;
        ResultSet rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();
            rs = st.executeQuery(
                    "SELECT * from manufacturers ORDER BY id ASC"
            );

            while (rs.next())
            {
                //System.out.println(
                answer+= rs.getString(1) + "+";
                answer+= rs.getString(2) + "+";
                answer+= rs.getString(3) + "=";

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

    public String insertManufacturer(String[] dataSliced){
        Statement st;
        int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            st.executeUpdate(
                    "INSERT INTO manufacturers(name, city)" +
                            " VALUES (\'" + dataSliced[1] + "\', \'" +  dataSliced[2] + "\' )"
            );

            answer = "ok";

            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }
    public String deleteManufacturer(String[] dataSliced){
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
/*
DELETE FROM products WHERE (id, user_id, manufacturer_id, name, amount, price)=
(20, 1, 1, 'dafasdf', 34234,32423)
 */
            rs = st.executeUpdate(
                    "DELETE FROM manufacturers WHERE (id, name, city)=" +
                            "(" +  dataSliced[1]  + ", " + "\'" + dataSliced[2] + "\'"  + ", " + "\'" + dataSliced[3] + "\')"
            );


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
    public String findManufacturer(String[] dataSliced){
        Statement st;
        ResultSet rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeQuery(
                    "SELECT * from manufacturers WHERE (name, city)="+
                            "(" + "\'" +  dataSliced[3] + "\'" + ", " + "\'" + dataSliced[4] + "\'" + ")"
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
                answer+= rs.getString(3) + "=";


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
    public String editManufacturer(String[] dataSliced){
        Statement st;
        int rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();


            rs = st.executeUpdate(
                    "UPDATE manufacturers SET name=\'" +
                            dataSliced[2]+"\', city=\'" + dataSliced[3] + "\'"  +
                            " WHERE (id="  + dataSliced[1] +  ")"
            );


            if (rs > 0){
                answer = "ok";
            }else{
                answer = "not";
            }




            //rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }
    public boolean isSuchManufacturerExists(String dataSliced){
        Statement st;
        ResultSet rs;
        boolean answer = false;
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeQuery(
                    "SELECT * " +
                            "FROM manufacturers " +
                            "WHERE id=" + "\'" + dataSliced + "\'" + " LIMIT 1"
            );
            String[] result;



            if (rs.next())
                answer = true;
            else
                answer = false;



            // rs.close(); вот это всегда закрывал а тут решил не закрыть
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }

}
