module com.example.technologydevicemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.sql;

    opens com.example.technologydevicemanagement to javafx.fxml;
    exports com.example.technologydevicemanagement;
}