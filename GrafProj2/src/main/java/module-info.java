module org.example.grafproj2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.grafproj2 to javafx.fxml;
    exports org.example.grafproj2;
}