import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
        String query = "SELECT * FROM users WHERE username = ?;";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, args[1]);
        ResultSet rs = stmt.execute();
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }



    }
}
