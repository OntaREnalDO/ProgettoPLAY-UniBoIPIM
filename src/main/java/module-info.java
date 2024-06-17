module com.desarts.playprogetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.desarts.playprogetto to javafx.fxml;
    exports com.desarts.playprogetto;
}