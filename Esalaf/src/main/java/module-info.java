module com.example.esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.model to javafx.base;
    opens com.example.esalaf to javafx.fxml;
    exports com.example.esalaf;
}