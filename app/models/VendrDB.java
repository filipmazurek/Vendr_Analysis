package models;

import play.db.*;
import java.sql.*;
import javax.sql.*;
import java.util.ArrayList;

public class VendrDB {

    public static ArrayList<String> getAllUserNames() throws SQLException {
    //    return getAllNames("users");
        return queryDatabase("SELECT * FROM users", 1);
    }

    public static Integer getNumLikesUserDate(String uid, String startDate, String endDate) throws SQLException {
        return Integer.parseInt(queryDatabase("SELECT COUNT(*) FROM user_like_ad WHERE user_id=" + stringifyName(uid) +" AND did_like IS TRUE AND time_of_like <= " + stringifyName(endDate) + "AND time_of_like > " +stringifyName(startDate)));
    }


    /*
    public static ArrayList<String> getAllNames(String table) throws SQLException {
        assert table.equals("users");
        Connection connection = null;
        ArrayList<String> names = new ArrayList<String>();
        try {
            connection = DB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                String name = rs.getString(1);
                names.add(name);
            }
            rs.close();
            statement.close();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e) {
                }
            }
        }
        return names;
    }
*/
    public static ArrayList<String> queryDatabase(String query, int idx) throws SQLException {
        Connection connection = null;
        ArrayList<String> ret = new ArrayList<String>();

        try {
            connection = DB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(idx);
                ret.add(name);
            }
            rs.close();
            statement.close();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e) {
                }
            }
        }
        return ret;
    }

    public static String queryDatabase(String query) throws SQLException {
        Connection connection = null;
       String ret = "";

        try {
            connection = DB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery(query);
            while (rs.next()) {
                ret = rs.toString();
            }
            rs.close();
            statement.close();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e) {
                }
            }
        }
        return ret;
    }

    public static String stringifyName(String name){
        return "'" + name + "'";
    }

}
