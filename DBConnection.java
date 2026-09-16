import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/library_db";
            String username = "library";
            String password = "library123";   // password blank hai to aise hi chhod do

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully!");
        } 
        catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}