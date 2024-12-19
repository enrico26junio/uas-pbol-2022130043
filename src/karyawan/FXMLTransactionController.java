package karyawan;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXMLTransactionController implements Initializable {

    // Deklarasi elemen UI
    @FXML private TextField txtTransactionId;
    @FXML private TextField txtTransactionEmployeeId;
    @FXML private TextField txtTransactionDate;
    @FXML private TextField txtTransactionAmount;

    @FXML private TableView<Transaction> tblTransactions;
    @FXML private TableColumn<Transaction, Integer> colTransactionId;
    @FXML private TableColumn<Transaction, Integer> colTransactionEmployeeId;
    @FXML private TableColumn<Transaction, String> colTransactionDate;
    @FXML private TableColumn<Transaction, Double> colTransactionAmount;

    // ObservableList untuk menyimpan data transaksi
    private final ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi kolom tabel
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        colTransactionEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colTransactionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTransactionAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Hubungkan ObservableList dengan TableView
        tblTransactions.setItems(transactionList);
    }

    @FXML
    private void handleAddTransaction() {
        try {
            Transaction transaction = validateTransactionFields();
            transactionList.add(transaction);
            clearFields();
            showAlert(AlertType.INFORMATION, "Sukses", "Data transaksi berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Tidak Valid", "Harap masukkan angka yang valid pada ID dan Jumlah.");
        } catch (DateTimeParseException e) {
            showAlert(AlertType.ERROR, "Input Tidak Valid", "Format tanggal harus YYYY-MM-DD atau DD-MM-YYYY.");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Input Tidak Valid", e.getMessage());
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Kesalahan", "Terjadi kesalahan: " + e.getMessage());
        }
    }

    @FXML
    private void handleViewReport() {
        try {
            Parent reportRoot = FXMLLoader.load(getClass().getResource("FXMLReportView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Laporan Transaksi");
            stage.setScene(new Scene(reportRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Gagal Membuka Laporan", "Halaman laporan tidak dapat dimuat.");
        }
    }

    private Transaction validateTransactionFields() throws IllegalArgumentException {
        // Ambil input dari user
        int transactionId = Integer.parseInt(txtTransactionId.getText().trim());
        int employeeId = Integer.parseInt(txtTransactionEmployeeId.getText().trim());
        String dateInput = txtTransactionDate.getText().trim();
        double amount = Double.parseDouble(txtTransactionAmount.getText().trim());

        // Validasi angka
        if (transactionId <= 0 || employeeId <= 0) {
            throw new IllegalArgumentException("ID Transaksi dan ID Karyawan harus positif.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah transaksi harus lebih besar dari 0.");
        }

        // Validasi format tanggal
        String date;
        try {
            date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_DATE).toString(); // Format YYYY-MM-DD
        } catch (DateTimeParseException e) {
            date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString(); // Format DD-MM-YYYY
        }

        return new Transaction(transactionId, employeeId, date, amount);
    }

    private void clearFields() {
        txtTransactionId.clear();
        txtTransactionEmployeeId.clear();
        txtTransactionDate.clear();
        txtTransactionAmount.clear();
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
