package penggajian_karyawan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import koneksi.koneksi;

public class FXMLDocumentController implements Initializable {

    private final koneksi koneksi = new koneksi();  
    @FXML
    private TextField namafield;         
    @FXML
    private TextField harifield;         
    @FXML
    private TextField upahfield;        
    @FXML
    private TextField totalfield;        
    @FXML
    private Button btnhitung;            
    @FXML
    private Button btnhapus;             
    @FXML
    private Button btnkeluar;            
    @FXML
    private TableView<?> tbtvkaryawan;   
    @FXML
    private TableColumn<?, ?> karyawanColumn;   
    @FXML
    private TableColumn<?, ?> penggajianColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    private void hitungklik(ActionEvent event) {
        try {
 
            String nama = namafield.getText();
            int hariKerja = Integer.parseInt(harifield.getText());
            double upahPerHari = Double.parseDouble(upahfield.getText());

            double totalGaji = hariKerja * upahPerHari;
            totalfield.setText("Rp " + totalGaji);
            simpanKaryawan(nama, upahPerHari, hariKerja, totalGaji);

        } catch (NumberFormatException e) {
            totalfield.setText("Input tidak valid");
        }
    }

    private void simpanKaryawan(String nama, double upahPerHari, int jumlahHariKerja, double totalGaji) {
        String sql = "INSERT INTO Karyawan (nama_karyawan, upah_per_hari, jumlah_hari_kerja, total_gaji) VALUES (?, ?, ?, ?)";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nama);
            pstmt.setDouble(2, upahPerHari);
            pstmt.setInt(3, jumlahHariKerja);
            pstmt.setDouble(4, totalGaji);
            pstmt.executeUpdate();
            System.out.println("Data karyawan berhasil disimpan!");

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data! Error: " + e.getMessage());
        }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        namafield.setText("");    
        harifield.setText("");    
        upahfield.setText("");    
        totalfield.setText("");   
        namafield.requestFocus(); 
    }

    
    @FXML
    private void keluarklik(ActionEvent event) {
        System.exit(0);  
    }
}
