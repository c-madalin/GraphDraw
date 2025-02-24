package org.example.grafproj2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStart extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Graf graf = new Graf();

        // Încarcă fișierul FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));

        // Obține controller-ul și setează obiectul Graf
        StackPane root = loader.load();
        AppController controller = loader.getController();

        controller.setGraf(graf); // Setează Graf în controller

        // Crează scena și o asociază cu fereastra
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Draw Circles on Click");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
