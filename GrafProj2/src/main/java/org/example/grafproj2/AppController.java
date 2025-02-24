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
import java.util.List;

import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;


public class AppController {

    @FXML
    private Pane drawingPane; // Legătura cu Pane din FXML

    private Graf graf; // Instanța de Graf
    private Node m_SelectedNode=null;
    private boolean m_isFirstNodeSelected;

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
        drawingPane.setOnMouseDragged(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
               // List<Node> for_nodes =graf.getNodes();
                for(Node node : graf.getNodes())
                {
                    int dx =(int) (node.getPos().getX()- event.getX());
                    int dy =(int) (node.getPos().getY()- event.getY());
                    int distanceSquared = dx*dx+dy*dy;

                    if(distanceSquared < MIN_DISTANCE*MIN_DISTANCE)
                    {
                        if(m_SelectedNode != null)
                        {
                            if(graf.addEdge(m_SelectedNode, node))
                            {
                                drawLine((int) m_SelectedNode.getPos().getX(),(int)m_SelectedNode.getPos().getY(),(int)node.getPos().getX(),(int)node.getPos().getY());

                            }
                            m_isFirstNodeSelected=false;
                            m_SelectedNode=null;

                        }
                        else
                        {
                            m_isFirstNodeSelected=true;
                            m_SelectedNode=node;
                        }
                        break;
                    }
                }
            }
        });

    }

    private void drawLine(int startX, int startY, int endX, int endY)
    {

        Line line = new Line(startX, startY, endX, endY);
        drawingPane.getChildren().addAll(line);


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
