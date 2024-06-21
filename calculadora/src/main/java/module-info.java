module es.localhost.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens es.localhost.calculadora to javafx.fxml;
    exports es.localhost.calculadora;
}