package com.mycompany.juego_monster_hunter;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    static String nombre_monstruo_ataque1;
    static String nombre_monstruo_ataque2;

    //IMAGEVIEW del player1 y su caja de texto
    @FXML
    private ImageView anjanath1;

    @FXML
    private ImageView teostra1;

    @FXML
    private ImageView rathian1;

    @FXML
    private ImageView rathalos1;

    @FXML
    public TextField nombre1;

    //IMAGEVIEW DEL PLAYER2 y su caja de texto
    @FXML
    private ImageView anjanath2;

    @FXML
    private ImageView teostra2;

    @FXML
    private ImageView rathian2;

    @FXML
    private ImageView rathalos2;

    @FXML
    private TextField nombre2;

    //boton que cambia a combate cuando se seleccionan los 2 personajes
    @FXML
    private Button seleccionar;

    @FXML
    private AnchorPane panel;

    //ELECCION DE PERSONAJES
    @FXML
    private void personajes1(MouseEvent event) {
        if (event.getSource().equals(rathalos1)) {
            nombre1.setText("RATHALOS");
        } else if (event.getSource().equals(anjanath1)) {
            nombre1.setText("ANJANATH");
        } else if (event.getSource().equals(teostra1)) {
            nombre1.setText("ELDER DRAKE TEOSTRA");
        } else if (event.getSource().equals(rathian1)) {
            nombre1.setText("RATHIAN");
        }
    }

    @FXML
    private void personajes2(MouseEvent event) {
        if (event.getSource().equals(rathalos2)) {
            nombre2.setText("RATHALOS");
        } else if (event.getSource().equals(anjanath2)) {
            nombre2.setText("ANJANATH");
        } else if (event.getSource().equals(teostra2)) {
            nombre2.setText("ELDER DRAKE TEOSTRA");
        } else if (event.getSource().equals(rathian2)) {
            nombre2.setText("RATHIAN");
        }
    }

    @FXML
    private void seleccionar(ActionEvent event) throws IOException {
        if (event.getSource().equals(seleccionar)) {

            if (nombre1.getText().length() > 0 && nombre2.getText().length() > 0) {
                nombre_monstruo_ataque1 = nombre1.getText();
                nombre_monstruo_ataque2 = nombre2.getText();

                cambiar_ventana_combate();

            } else {
                dialogoError("cada jugador debe seleccionar un personaje");
            }
        }
    }

    public void dialogoError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void cambiar_ventana_combate() throws IOException {
        App.setRoot("secondary");
    }
}