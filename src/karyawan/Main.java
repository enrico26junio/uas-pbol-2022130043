package karyawan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            // Simpan stage utama agar bisa diakses untuk berpindah scene
            primaryStage = stage;

            // Muat tampilan awal dari FXMLKaryawan.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/karyawan/FXMLKaryawan.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("Aplikasi Penggajian Karyawan");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void changeScene(String fxmlFile) {
        try {
            // Ganti tampilan ke file FXML yang diinginkan
            Parent root = FXMLLoader.load(Main.class.getResource("/karyawan/" + fxmlFile));
            primaryStage.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println("Error saat mengganti scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
