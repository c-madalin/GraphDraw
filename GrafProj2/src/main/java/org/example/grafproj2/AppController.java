package org.example.grafproj2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.FileWriter;
import java.io.IOException;


public class AppController {

    @FXML
    private Pane drawingPane; // Legătura cu Pane din FXML

    private Graf graf; // Instanța de Graf

    private static final double RADIUS = 20; // Raza cercului
    private static final double MIN_DISTANCE = RADIUS * 2; // Distanța minimă dintre cercuri

    // Setter pentru Graf
    public void setGraf(Graf graf) {
        this.graf = graf;
    }

    @FXML
    public void initialize() {
        // Adaugă event listener pentru click pe Pane
        //drawingPane.setOnMouseClicked(this::drawCircle);

        drawingPane.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                drawCircle(event);
            }
        });

    }

    private void drawCircle(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Verifică dacă există suprapunere
        if (isOverlapping(x, y)) {
            System.out.println("Cercul nu poate fi plasat aici! Este prea aproape de alt cerc.");
            return; // Nu adăuga cercul dacă există suprapunere
        }

        // Creează un nod în graf
        graf.addNode((int) x, (int) y);
        int nodeId = graf.getNodes().size(); // ID-ul nodului

        // Creează un cerc la poziția click-ului
        Circle circle = new Circle(x, y, RADIUS, Color.BLUE);
        circle.setStroke(Color.BLACK);

        // Creează un Label pentru ID-ul nodului
        Label label = new Label(String.valueOf(nodeId));
        label.setTextFill(Color.WHITE); // Text alb pentru contrast
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        // Ajustează poziția Label-ului pentru a-l centra în cerc
        label.setLayoutX(x - 5); // Ajustează pe orizontală
        label.setLayoutY(y - 7); // Ajustează pe verticală

        // Adaugă cercul și Label-ul în Pane
        drawingPane.getChildren().addAll(circle, label);

        // Scrie ID-ul nodului în fișier
        try (FileWriter writer = new FileWriter("src/main/resources/org/example/grafproj2/componente.txt", true)) {
            writer.write(nodeId + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Debugging
        System.out.println("-----------");
        for (var node : graf.getNodes()) {
            System.out.println(node);
        }
    }

    private boolean isOverlapping(double x, double y) {
        for (var node : drawingPane.getChildren()) {
            if (node instanceof Circle) {
                Circle existingCircle = (Circle) node;
                double existingX = existingCircle.getCenterX();
                double existingY = existingCircle.getCenterY();

                // Calcul distanță dintre noul cerc și cercurile existente
                double distance = Math.sqrt(Math.pow(existingX - x, 2) + Math.pow(existingY - y, 2));

                if (distance < MIN_DISTANCE) {
                    return true; // Suprapunere detectată
                }
            }
        }
        return false; // Nu există suprapunere
    }
}
