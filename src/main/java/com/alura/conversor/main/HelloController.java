package com.alura.conversor.main;

import com.alura.conversor.comun.GestorMonedas;
import com.alura.conversor.comun.alert.AdministrarNotificaciones;
import com.alura.conversor.comun.base.CrearMonedas;
import com.alura.conversor.comun.conexion.ConexionApi;
import com.alura.conversor.entidad.Moneda;

import com.alura.conversor.entidad.RespuestaApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HelloController {

    public static final String COP = "COP";

    @FXML
    private TextField valorPesos;

    @FXML
    private TextField nombreMoneda;

    @FXML
    private TextField total;

    @FXML
    private ListView<String> suggestionsListView;

    @FXML
    private Button refrescar;

    @FXML
    private Button botonSalir;

    @FXML
    private Spinner<Integer> cantidadConvertir;
    private final ConexionApi conexionApi = new ConexionApi();
    private CrearMonedas crearMonedas;
    private GestorMonedas gestorMonedas;
    private Moneda moneda;
    private RespuestaApi respuestaApi;


    @FXML
    public void initialize() {
        asignarimagen();
        crearMonedas = new CrearMonedas();
        gestorMonedas = new GestorMonedas(crearMonedas.instanciarMonedas());

        List<String> listMonedas = gestorMonedas.obtenerListaMonedas();

        suggestionsListView.setVisible(false);
        suggestionsListView.setManaged(false);
        suggestionsListView.setCellFactory(TextFieldListCell.forListView());

        nombreMoneda.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                suggestionsListView.setVisible(false);
                suggestionsListView.setManaged(false);
            } else {
                List<String> currentSuggestions = listMonedas.stream()
                        .filter(suggestion -> suggestion.toLowerCase().startsWith(newValue.toLowerCase()))
                        .collect(Collectors.toList());

                if (currentSuggestions.isEmpty()) {
                    suggestionsListView.setVisible(false);
                    suggestionsListView.setManaged(false);
                } else {
                    ObservableList<String> observableList = FXCollections.observableArrayList(currentSuggestions);
                    suggestionsListView.setItems(observableList);
                    suggestionsListView.setVisible(true);
                    suggestionsListView.setManaged(true);
                }
            }
        });

        suggestionsListView.setOnMouseClicked(event -> {
            String monedaSeleccionada = suggestionsListView.getSelectionModel().getSelectedItem();
            nombreMoneda.setText(monedaSeleccionada);
            this.actulizarValor(monedaSeleccionada);
            suggestionsListView.setVisible(false);
            suggestionsListView.setManaged(false);
        });
    }

    public void actulizarValor(String monedaSeleccionada) {
        crearMonedas = new CrearMonedas();
        moneda = gestorMonedas.obtenerMonedaNombre(monedaSeleccionada);
        actulizarInputvalorPesos(String.valueOf(moneda.getValorEnPesos()));
    }

    public void actulizarInputvalorPesos(String monedaValorCOP){
        valorPesos.setText("$ " + monedaValorCOP + " COP");
    }

    public void asignarimagen() {
        refrescar.setGraphic(new ImageView(new Image("actualizar.png")));
    }

    @FXML
    public void calcularTotal() {
        int cantidad = cantidadConvertir.valueProperty().get();
        if (valorPesos.getText().isEmpty()) {
            AdministrarNotificaciones.mostrarAlertaError(
                    "Error",
                    null,
                    "No ha seleccionado ninguna moneda"
            );
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No ha seleccionado ninguna moneda");
            alert.showAndWait();
        } else {
            double intValorPesos = moneda.getValorEnPesos();
            double totalConversion = (cantidad * intValorPesos);
            total.setText(String.valueOf(totalConversion));
        }
    }

    public void actualizarValor() {
        if (moneda == null || moneda.getCodigoISO() == null) {
            AdministrarNotificaciones.mostrarAlertaError(
                    "Error de selección",
                    "No ha seleccionado ninguna moneda",
                    "Por favor, seleccione una moneda antes de continuar.");
        } else {
            try {
                String jsonValorEnPesos = this.conexionApi.obtenerValorPesos(moneda.getCodigoISO(), COP);
                if (jsonValorEnPesos != null) {
                    this.respuestaApi = new RespuestaApi();
                    respuestaApi = respuestaApi.convertirJsonARespuesta(jsonValorEnPesos);
                    double valorEnPesos = respuestaApi.getConversionRate();
                    moneda.setValorEnPesos(gestorMonedas.invertirACOP(valorEnPesos));
                    actulizarInputvalorPesos(String.valueOf( moneda.getValorEnPesos()));
                    calcularTotal();

                } else {
                    AdministrarNotificaciones.mostrarAlertaError(
                            "Error de conexión",
                            "No se pudo obtener el valor de pesos",
                            "Por favor, inténtelo nuevamente más tarde.");
                }
            } catch (IOException e) {
                AdministrarNotificaciones.mostrarAlertaError(
                        "Error de conexión",
                        "No se pudo obtener el valor de pesos",
                        "Por favor, verifique su conexión a internet y vuelva a intentarlo.");
            }
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de salida");
        alert.setHeaderText("¿Está seguro que desea salir?");
        alert.setContentText("Presione Aceptar para salir o Cancelar para cancelar.");

        ButtonType buttonTypeOk = new ButtonType("Aceptar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeOk) {
                // Cerrar la ventana o la aplicación
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }
}

