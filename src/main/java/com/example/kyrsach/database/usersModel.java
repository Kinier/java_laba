package com.example.kyrsach.database;

import java.sql.*;

public class usersModel extends Database{




    public String selectAllUsers(){
        Statement st;
        ResultSet rs;
        String answer = "";
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();
            rs = st.executeQuery(
                    "SELECT * from users"
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
    public boolean isSuchUserExists(String[] dataSliced){
        Statement st;
        ResultSet rs;
        boolean answer = false;
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeQuery(
                    "SELECT * " +
                            "FROM users " +
                            "WHERE name=" + "\'" + dataSliced[1] + "\'" + " LIMIT 1"
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
    public String[] login(String[] dataSliced){
        Statement st;
        ResultSet rs;
        String[] answer = {"not", null};
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            rs = st.executeQuery(
                    "SELECT * " +
                            "FROM users " +
                            "WHERE name=" + "\'" + dataSliced[1] + "\'" + " AND password=" + "\'" + dataSliced[2] + "\'" + " LIMIT 1"
            );




            if (rs.next()){
                answer[0] = "ok";
                answer[1] = rs.getString("id");
            }



            // rs.close(); вот это всегда закрывал а тут решил не закрыть
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Query has failed");
            e.printStackTrace();
        }
        return answer;
    }

    public String[]  register(String[] dataSliced){
        int res = 0;
        Statement st;
        ResultSet rs;
        String[] answer = {"not", null};
        Connection connection;
        try { // попытка подключения к базе данных
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //connection = getConnection();

            st = connection.createStatement();

            res = st.executeUpdate(
                    "INSERT INTO users(name, password)" +
                            " VALUES (" + "\'" + dataSliced[1] + "\', " + "\'" + dataSliced[2] + "\'"+")"
                    , Statement.RETURN_GENERATED_KEYS);
            rs= st.getGeneratedKeys();
            if (rs.next()) {
                answer[0] = "ok";
                answer[1] = rs.getString(1);
            } else {
                answer[0] = "not";
            }

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
