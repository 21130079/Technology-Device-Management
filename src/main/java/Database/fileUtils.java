//package Database;
//import java.sql.Connection;
//public class fileUtils {
//    public static Connection connectDb() {
//        String url = "jdbc:sqlserver://localhost:1433;database=MusicWeb;user=MusicWeb;password=MusicWeb;encrypt=true;trustServerCertificate=true;";
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection connection = DriverManager.getConnection(url);
//            return connection;
//        } catch (Exception e) {
//            e.getStackTrace();
//            System.out.println("fail");
//        }
//        return null;
//
//    }
//
//}
