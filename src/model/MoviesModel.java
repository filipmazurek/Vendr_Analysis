package model;

import play.db.*;
import java.sql.*;
import javax.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoviesModel{
    private static final String TOP_ACTOR_TABLE = "";
    private static final String ALL_ACTOR_TABLE = "";
    private static final String MOVIE_TABLE = "";
    private static final String PAGE_ONE = "1";


    private static final String RESOURCE_PACKAGE = "resources/informtation";
    private static final ResourceBundle MY_RESOURCES = ResourceBundle.getBundle(RESOURCE_PACKAGE);
    private static final String MY_API_KEY = MY_RESOURCES.getString("movieDBKey");


    public static class ActorInfo {
        public ActorInfo() {
        }
    }

    public static class MovieInfo {
        public MovieInfo() {
        }
    }


    public static ArrayList<String> getAllNames(String table) throws SQLException {
        assert table.equals("TOP_ACTOR_TABLE")
                || table.equals("ALL_ACTOR_TABLE")
                || table.equals("MOVIE_TABLE");
        Connection connection = null;
        ArrayList<String> names = new ArrayList<String>();
        try {
            connection = DB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT name FROM " + table + " ORDER BY name");
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
                } catch (Exception e) {
                }
            }
        }
        return names;
    }

    public static ActorInfo getActorInfo() {

    }

    public static MovieInfo getMovieInfo() {

    }


    // below are the actual queries which may populate the database

    private static ArrayList<ActorInfo> getPopularActors(String page) {

        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/person/popular?page=" +
                page + "1&language=en-US&api_key=" + MY_API_KEY)
                .body("{}")
                .asString();
    }


    public static someImageType getActorImage(String actor_id) {

        HttpResponse<String> response = Unirest.get("https://api.themoviedb.org/3/person/"+
                actor_id +"/images?api_key=" + MY_API_KEY)
                .body("{}")
                .asString();
    }

    // TODO: add other getter methods to access TMBD



}
