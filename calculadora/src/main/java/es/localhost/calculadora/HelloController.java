package es.localhost.calculadora;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la calculadora que maneja las operaciones y la interacción con la interfaz gráfica.
 *
 * @author Carlos Murillo (carlosmp15)
 * @date 19/06/2024
 * @version 0.1
 */
public class HelloController implements Initializable {
    @FXML private Label lVista;
    @FXML private Label lLog;

    private double num1;
    private Operacion operacionActual;
    private boolean nuevaOperacion;
    private enum Operacion {SUMA, RESTA, MULTIPLICACION, DIVISION, MODULO}

    /**
     * Método que maneja el evento de clic en el botón "0".
     */
    @FXML private void onCeroClick() { addNumeroVista("0"); }
    /**
     * Método que maneja el evento de clic en el botón "1".
     */
    @FXML private void onUnoClick() { addNumeroVista("1"); }
    /**
     * Método que maneja el evento de clic en el botón "2".
     */
    @FXML private void onDosClick() { addNumeroVista("2"); }
    /**
     * Método que maneja el evento de clic en el botón "3".
     */
    @FXML private void onTresClick() { addNumeroVista("3"); }
    /**
     * Método que maneja el evento de clic en el botón "4".
     */
    @FXML private void onCuatroClick() { addNumeroVista("4"); }
    /**
     * Método que maneja el evento de clic en el botón "5".
     */
    @FXML private void onCincoClick() { addNumeroVista("5"); }
    /**
     * Método que maneja el evento de clic en el botón "6".
     */
    @FXML private void onSeisClick() { addNumeroVista("6"); }
    /**
     * Método que maneja el evento de clic en el botón "7".
     */
    @FXML private void onSieteClick() { addNumeroVista("7"); }
    /**
     * Método que maneja el evento de clic en el botón "8".
     */
    @FXML private void onOchoClick() { addNumeroVista("8"); }
    /**
     * Método que maneja el evento de clic en el botón "9".
     */
    @FXML private void onNueveClick() { addNumeroVista("9"); }
    /**
     * Método que maneja el evento de clic en el botón ".".
     */
    @FXML private void onComaClick() { addNumeroVista("."); }
    /**
     * Método que maneja el evento de clic en el botón "CE" o "C".
     */
    @FXML private void onResetClick() { reset(); }
    /**
     * Método que maneja el evento de clic en el botón "Borrar".
     */
    @FXML private void onBorrarClick() { borrUltNumero(); }
    /**
     * Método que maneja el evento de clic en el botón "Sumar".
     */
    @FXML private void onSumarClick() { iniciarOperacion(Operacion.SUMA); }
    /**
     * Método que maneja el evento de clic en el botón "Restar".
     */
    @FXML private void onRestarClick() { iniciarOperacion(Operacion.RESTA); }
    /**
     * Método que maneja el evento de clic en el botón "Multiplicar".
     */
    @FXML private void onMultiplicarClick() { iniciarOperacion(Operacion.MULTIPLICACION); }
    /**
     * Método que maneja el evento de clic en el botón "Dividir".
     */
    @FXML private void onDividirClick() { iniciarOperacion(Operacion.DIVISION); }
    /**
     * Método que maneja el evento de clic en el botón "Modulo".
     */
    @FXML private void onModuloClick() { iniciarOperacion(Operacion.MODULO); }
    /**
     * Método que maneja el evento de clic en el botón "Igual".
     */
    @FXML private void onIgualClick() { evaluarOperacion(); }


    /**
     * Agrega el número seleccionado a la vista de la calculadora.
     *
     * @param numero Número a agregar.
     */
    private void addNumeroVista(String numero) {
        if (nuevaOperacion) {
            lVista.setText(numero);
            nuevaOperacion = false;
        } else {
            if (numero.equals(".") && lVista.getText().contains(".")) {
                // Evitar agregar más de un punto decimal
                return;
            }

            String currentText = lVista.getText();
            if (currentText.length() < 10) {
                if (currentText.equals("0") && !numero.equals(".")) {
                    lVista.setText(numero);
                } else {
                    lVista.setText(currentText + numero);
                }
            }
        }
    }

    /**
     * Borra el último número ingresado en la vista de la calculadora.
     */
    private void borrUltNumero() {
        String currentText = lVista.getText();
        if (currentText.length() > 1) {
            lVista.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            lVista.setText("0");
        }
    }

    /**
     * Inicia una operación específica (suma, resta, multiplicación, división o módulo).
     *
     * @param operacion Tipo de operación a iniciar.
     */
    private void iniciarOperacion(Operacion operacion) {
        try{
            num1 = Double.parseDouble(lVista.getText());
            operacionActual = operacion;
            nuevaOperacion = true;
            actualizarVistaOperacion();
        } catch (Exception ignored){
            lVista.setText("0");
            lLog.setText("");}
    }

    /**
     * Evalúa la operación actualmente seleccionada y muestra el resultado en la vista.
     */
    private void evaluarOperacion() {
        String vistaText = lVista.getText().trim();
        double num2;

        try {
            num2 = Double.parseDouble(vistaText);
        } catch (NumberFormatException e) {
            lVista.setText("0");
            lLog.setText("");
            return;
        }
        double resultado = 0;

        switch (operacionActual) {
            case SUMA:
                resultado = num1 + num2;
                break;
            case RESTA:
                resultado = num1 - num2;
                break;
            case MULTIPLICACION:
                resultado = num1 * num2;
                break;
            case DIVISION:
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    lVista.setText("0");
                    lLog.setText("");
                    return;
                }
                break;
            case MODULO:
                if (num2 != 0) {
                    resultado = num1 % num2;
                } else {
                    lVista.setText("0");
                    lLog.setText("");
                    return;
                }
                break;
        }

        // Redondear a dos decimales
        resultado = Math.round(resultado * 100.0) / 100.0;

        // Limitar a 10 caracteres
        String resultadoString = String.valueOf(resultado);
        if (resultadoString.length() > 10) {
            resultadoString = resultadoString.substring(0, 10);
        }

        lVista.setText(resultadoString);
        lLog.setText(num1 + " " + getOperador() + " " + num2 + " = " + resultadoString);
        nuevaOperacion = true;
    }


    /**
     * Actualiza la vista de la calculadora para mostrar la operación actual en curso.
     */
    private void actualizarVistaOperacion() {
        String operador = getOperador();
        lVista.setText(num1 + " " + operador);
    }

    /**
     * Retorna el símbolo del operador actual según la operación seleccionada.
     *
     * @return Símbolo del operador actual.
     */
    private String getOperador() {
        return switch (operacionActual) {
            case SUMA -> "+";
            case RESTA -> "-";
            case MULTIPLICACION -> "x";
            case DIVISION -> "/";
            case MODULO -> "%";
        };
    }

    /**
     * Reinicia todos los valores de la calculadora a su estado inicial.
     */
    private void reset() {
        lVista.setText("0");
        lLog.setText("");
        num1 = 0;
        operacionActual = null;
        nuevaOperacion = false;
    }

    /**
     * Método de inicialización del controlador al cargar la interfaz de usuario.
     *
     * @param url             URL de inicialización (no utilizada en este contexto).
     * @param resourceBundle  ResourceBundle de inicialización (no utilizada en este contexto).
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }
}
