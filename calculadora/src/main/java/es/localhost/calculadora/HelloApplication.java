package es.localhost.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Punto de entrada para la aplicación calculadora.
 *
 * @author Carlos Murillo (carlosmp15)
 * @date 19/06/2024
 * @version 0.1
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 349, 548);

        stage.setTitle("Calculadora");
        scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
