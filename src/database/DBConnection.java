package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tcp?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "kamisama123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;

    private DBConnection() {

    }

    public static DBConnection getDBHelper() {
        return DBHelper.dbHelper;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class DBHelper {
        private static final DBConnection dbHelper = new DBConnection();
    }
}