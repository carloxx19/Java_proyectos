package com.mycompany.juego_monster_hunter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TertiaryController implements Initializable {

    public static String name;
    public static String monstruo;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TertiaryController.name = name;
    }

    @FXML
    public ImageView imagen_ganador;

    @FXML
    private Text nombre_ganador;

    @FXML
    private AnchorPane panel_final;

    @FXML
    private Button jugar;

    //TABLEVIEW
    @FXML
    public static TableView estadisticas;

    @FXML
    public static TableColumn ganador;
    
    @FXML
    public ObservableList<Personaje> lista;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombre_ganador.setText(name);

        if (monstruo.equals("RATHIAN")) {
            imagen_ganador.setImage(new Image("/com/mycompany/Imagenes/rathian_ataque.gif"));
        } else if (monstruo.equals("RATHALOS")) {
            imagen_ganador.setImage(new Image("/com/mycompany/Imagenes/rathalos_ataque.gif"));
        } else if (monstruo.equals("ANJANATH")) {
            imagen_ganador.setImage(new Image("/com/mycompany/Imagenes/anjanath_ataque.gif"));
        } else if (monstruo.equals("ELDER DRAKE TEOSTRA")) {
            imagen_ganador.setImage(new Image("/com/mycompany/Imagenes/teostra_ataque.gif"));
        }
    }

    @FXML
    private void cambiar_ventana_personajes() throws IOException {
        App.setRoot("primary");
    }
}