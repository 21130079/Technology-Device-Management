module com.example.technologydevicemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
//    requires mysql.connector.j;
    requires java.sql;

    exports com.example.technologydevicemanagement.controller;
    opens com.example.technologydevicemanagement.controller to javafx.fxml;
    exports com.example.technologydevicemanagement;
    opens com.example.technologydevicemanagement to javafx.fxml;
}