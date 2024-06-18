module com.desarts.playprogetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.desarts.playprogetto to javafx.fxml;
    exports com.desarts.playprogetto;
}