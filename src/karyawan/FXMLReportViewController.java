package karyawan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXMLReportViewController implements Initializable {

    @FXML
    private TableView<Karyawan> tblKaryawan;

    @FXML
    private TableColumn<Karyawan, Integer> colEmployeeId;

    @FXML
    private TableColumn<Karyawan, String> colEmployeeName;

    @FXML
    private TableColumn<Karyawan, String> colEmployeePosition;

    @FXML
    private TableColumn<Karyawan, Double> colEmployeeSalary;

    @FXML
    private TableView<Transaction> tblTransactions;

    @FXML
    private TableColumn<Transaction, Integer> colTransactionId;

    @FXML
    private TableColumn<Transaction, Integer> colTransactionEmployeeId;

    @FXML
    private TableColumn<Transaction, String> colTransactionDate;

    @FXML
    private TableColumn<Transaction, Double> colTransactionAmount;

    private final ObservableList<Karyawan> employeeList = FXCollections.observableArrayList();
    private final ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi kolom tabel karyawan
        colEmployeeId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colEmployeeName.setCellValueFactory(data -> data.getValue().nameProperty());
        colEmployeePosition.setCellValueFactory(data -> data.getValue().positionProperty());
        colEmployeeSalary.setCellValueFactory(data -> data.getValue().salaryProperty().asObject());

        // Inisialisasi kolom tabel transaksi
        colTransactionId.setCellValueFactory(data -> data.getValue().transactionIdProperty().asObject());
        colTransactionEmployeeId.setCellValueFactory(data -> data.getValue().employeeIdProperty().asObject());
        colTransactionDate.setCellValueFactory(data -> data.getValue().dateProperty());
        colTransactionAmount.setCellValueFactory(data -> data.getValue().amountProperty().asObject());

        // Muat data dari database
        loadEmployeeData();
        loadTransactionData();
    }

    private void loadEmployeeData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM karyawan")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String position = rs.getString("posisi");
                double salary = rs.getDouble("gaji");

                employeeList.add(new Karyawan(id, name, position, salary));
            }
            tblKaryawan.setItems(employeeList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTransactionData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transaksi")) {

            while (rs.next()) {
                int transactionId = rs.getInt("id_transaksi");
                int employeeId = rs.getInt("id_karyawan");
                String date = rs.getString("tanggal");
                double amount = rs.getDouble("jumlah");

                transactionList.add(new Transaction(transactionId, employeeId, date, amount));
            }
            tblTransactions.setItems(transactionList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
