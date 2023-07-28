package com.alura.conversor.comun.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
public class AdministrarNotificaciones {
    private static final ButtonType BUTTON_TYPE_OK = new ButtonType("Aceptar");
    private static final ButtonType BUTTON_TYPE_CANCEL = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

    public static void mostrarAlertaError(String title, String header, String content) {
        mostrarAlerta(Alert.AlertType.ERROR, title, header, content);
    }

    public static void mostrarAlertaInformacion(String title, String header, String content) {
        mostrarAlerta(Alert.AlertType.INFORMATION, title, header, content);
    }

    public static void mostrarAlertaAdvertencia(String title, String header, String content) {
        mostrarAlerta(Alert.AlertType.WARNING, title, header, content);
    }

    public static ButtonType mostrarAlertaConfirmacion(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().setAll(BUTTON_TYPE_OK, BUTTON_TYPE_CANCEL);

        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }

    private static void mostrarAlerta(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
