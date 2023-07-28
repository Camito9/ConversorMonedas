module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.controlsfx.controls;
    requires com.google.gson;

    opens com.alura.conversor.main to javafx.fxml;
    exports com.alura.conversor.main;
    exports com.alura.conversor.entidad;
    opens com.alura.conversor.entidad;

}