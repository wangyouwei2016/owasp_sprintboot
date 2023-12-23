import java.sql.*;
import java.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

class LoggedOutException extends Exception {
    public LoggedOutException(String message) {
        super(message);
    }
}

public class External {

    public static HashMap<String, String> accountLookup(String accountId, String jwt) throws Exception {
        if (jwt == null) {
            throw new LoggedOutException("User is not logged in");
        } else {
            Claims claims = Jwts.parser()
                    .setSigningKey("Key".getBytes("UTF-8"))
                    .parseClaimsJws(jwt).getBody();

            if ((claims.get("logged_in")).toString().equals("true")) {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/BankApp?useSSL=false", "root", "letmein");

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT username FROM tbl_user WHERE id = '" + accountId + "';");

                if (!rs.next()) {
                    con.close();
                    throw new Exception("Account not found");
                }

                String user = new String();
                user = rs.getString("username");
                rs = stmt.executeQuery("SELECT balance, dob FROM tbl_account WHERE user_id = '" + accountId + "';");
                HashMap<String, String> results = new HashMap<String, String>();

                if (rs.next()) {
                    results.put("balance", rs.getString("balance").toString());
                    results.put("dob", rs.getString("dob").toString());
                    results.put("username", user);
                }
                con.close();

                return results;
            } else {
                throw new LoggedOutException("User is not logged in");
            }
        }
    }
}
