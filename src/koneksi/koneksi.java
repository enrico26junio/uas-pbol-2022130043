package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private final String url = "jdbc:mysql://localhost:3306/uas_pbol"; // URL database
    private final String user = "root"; 
    private final String password = ""; 

    public Connection getConnection() {
        Connection conn = null;
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Menyambungkan ke database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal! Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan! Error: " + e.getMessage());
        }
        return conn;
    }
}
