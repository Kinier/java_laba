module com.example.kyrsach {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.kyrsach to javafx.fxml;
    exports com.example.kyrsach;
    exports com.example.kyrsach.database;
    opens com.example.kyrsach.database to javafx.fxml;
}
