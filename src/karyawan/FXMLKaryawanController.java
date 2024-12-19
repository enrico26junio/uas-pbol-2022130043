package karyawan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLKaryawanController implements Initializable {

    // Input fields untuk Karyawan
    @FXML
    private TextField txtEmployeeId, txtEmployeeName, txtEmployeePosition, txtEmployeeSalary;

    // TableView Karyawan
    @FXML
    private TableView<Karyawan> tblEmployees;
    @FXML
    private TableColumn<Karyawan, Integer> colEmployeeId;
    @FXML
    private TableColumn<Karyawan, String> colEmployeeName, colEmployeePosition;
    @FXML
    private TableColumn<Karyawan, Double> colEmployeeSalary;

    // Button untuk pindah halaman transaksi
    @FXML
    private Button btnGoToTransaction;

    // List Observable untuk karyawan
    private final ObservableList<Karyawan> employeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inisialisasi TableView Karyawan
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblEmployees.setItems(employeeList);
    }

    // Event handler untuk menambahkan karyawan
    @FXML
    private void handleAddEmployee(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtEmployeeId.getText());
            String name = txtEmployeeName.getText();
            String position = txtEmployeePosition.getText();
            double salary = Double.parseDouble(txtEmployeeSalary.getText());

            if (name.isEmpty() || position.isEmpty()) {
                showAlert(AlertType.WARNING, "Input Tidak Lengkap", "Nama dan posisi wajib diisi.");
                return;
            }

            Karyawan employee = new Karyawan(id, name, position, salary);
            employeeList.add(employee);
            addEmployeeToDatabase(employee);
            clearEmployeeInput();
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Kesalahan Format", "Pastikan ID dan Gaji dalam format angka.");
        }
    }

    // Event handler untuk pindah ke halaman transaksi
    @FXML
    private void handleGoToTransaction(ActionEvent event) {
        try {
            // Load file FXML untuk transaksi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTransaction.fxml"));
            Parent transactionRoot = loader.load();

            // Dapatkan stage dari tombol saat ini
            Stage stage = (Stage) btnGoToTransaction.getScene().getWindow();

            // Set scene baru
            Scene scene = new Scene(transactionRoot);
            stage.setScene(scene);
            stage.setTitle("Halaman Transaksi");
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Load Error", "Gagal memuat halaman transaksi: " + e.getMessage());
        }
    }

    // Membersihkan input karyawan
    private void clearEmployeeInput() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeePosition.clear();
        txtEmployeeSalary.clear();
    }

    // Menambahkan karyawan ke database
    private void addEmployeeToDatabase(Karyawan employee) {
        String query = "INSERT INTO employees (id, name, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getPosition());
            stmt.setDouble(4, employee.getSalary());
            stmt.executeUpdate();
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Database Error", "Gagal menyimpan karyawan: " + e.getMessage());
        }
    }

    // Menampilkan alert
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
