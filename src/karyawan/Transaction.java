package karyawan;

import javafx.beans.property.*;

public class Transaction {
    private final IntegerProperty transactionId;
    private final IntegerProperty employeeId;
    private final StringProperty date;
    private final DoubleProperty amount;

    public Transaction(int transactionId, int employeeId, String date, double amount) {
        this.transactionId = new SimpleIntegerProperty(transactionId);
        this.employeeId = new SimpleIntegerProperty(employeeId);
        this.date = new SimpleStringProperty(date);
        this.amount = new SimpleDoubleProperty(amount);
    }

    // Properti untuk binding dengan TableView
    public IntegerProperty transactionIdProperty() {
        return transactionId;
    }

    public IntegerProperty employeeIdProperty() {
        return employeeId;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    // Getter untuk nilai properti
    public int getTransactionId() {
        return transactionId.get();
    }

    public int getEmployeeId() {
        return employeeId.get();
    }

    public String getDate() {
        return date.get();
    }

    public double getAmount() {
        return amount.get();
    }

    // Setter untuk nilai properti
    public void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + getTransactionId() +
                ", employeeId=" + getEmployeeId() +
                ", date='" + getDate() + '\'' +
                ", amount=" + getAmount() +
                '}';
    }
}
