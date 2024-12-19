package karyawan;

import javafx.beans.property.*;

public class Karyawan {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty position;
    private final DoubleProperty salary;

    // Constructor
    public Karyawan(int id, String name, String position, double salary) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
    }

    // Getter untuk properti
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty positionProperty() {
        return position;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    // Getter untuk nilai properti
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPosition() {
        return position.get();
    }

    public double getSalary() {
        return salary.get();
    }

    // Setter untuk properti
    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }
}
