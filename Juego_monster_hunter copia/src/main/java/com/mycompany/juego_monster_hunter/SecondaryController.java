package com.mycompany.juego_monster_hunter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.mycompany.juego_monster_hunter.TertiaryController;

public class SecondaryController implements Initializable {

    Personaje p1 = new Personaje();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private Button btn_atacar1;

    @FXML
    private Button btn_atacar2;

    @FXML
    private Button btn_defender1;

    @FXML
    private Button btn_defender2;

    @FXML
    private Button estado_alterado1;

    @FXML
    private Button estado_alterado2;

    @FXML
    public ImageView monstruo1;

    @FXML
    private ImageView monstruo2;

    @FXML
    private AnchorPane panel_combate;

    @FXML
    private ProgressBar vida1;

    @FXML
    private ProgressBar vida2;

    @FXML
    private ImageView volver;

    @FXML
    private Button btn_listo;

    @FXML
    private Text texto1;

    @FXML
    private Text texto2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciar();
    }

    public void iniciar() {
        vida1.setProgress(1.0);
        vida1.setStyle("-fx-accent:green");
        vida2.setProgress(1.0);
        vida2.setStyle("-fx-accent: green");
    }

    @FXML
    void volver_evento(MouseEvent event) throws IOException {
        if (event.getSource().equals(volver)) {
            cambiar_ventana_inicio();
        }
    }

    @FXML
    private void cambiar_ventana_inicio() throws IOException {
        App.setRoot("primary");
    }

    public void activar(ActionEvent event) {

        switch (p1.getM1()) {
            case "RATHIAN":
                monstruo1.setImage(new Image("/com/mycompany/Imagenes/rathian_ataque.gif"));
                break;
            case "RATHALOS":
                monstruo1.setImage(new Image("/com/mycompany/Imagenes/rathalos_ataque.gif"));
                break;
            case "ANJANATH":
                monstruo1.setImage(new Image("/com/mycompany/Imagenes/anjanath_ataque.gif"));
                break;
            case "ELDER DRAKE TEOSTRA":
                monstruo1.setImage(new Image("/com/mycompany/Imagenes/teostra_ataque.gif"));
                break;
            default:
                break;
        }

        switch (p1.getM2()) {
            case "RATHIAN":
                monstruo2.setImage(new Image("/com/mycompany/Imagenes/rathian_ataque.gif"));
                break;
            case "RATHALOS":
                monstruo2.setImage(new Image("/com/mycompany/Imagenes/rathalos_ataque.gif"));
                break;
            case "ANJANATH":
                monstruo2.setImage(new Image("/com/mycompany/Imagenes/anjanath_ataque.gif"));
                break;
            case "ELDER DRAKE TEOSTRA":
                monstruo2.setImage(new Image("/com/mycompany/Imagenes/teostra_ataque.gif"));
                break;
            default:
                break;
        }

        //HUD PPLAYER1
        btn_atacar1.setOpacity(1);
        btn_atacar1.setDisable(false);

        btn_defender1.setOpacity(1);
        btn_defender1.setDisable(false);

        vida1.setDisable(false);
        vida1.setOpacity(1);

        estado_alterado1.setOpacity(0.7);

        //HUD PPLAYER2
        btn_atacar2.setOpacity(0.7);

        btn_defender2.setOpacity(0.7);

        vida2.setDisable(true);
        vida2.setOpacity(1);

        estado_alterado2.setOpacity(0.7);

        //deshabilita el boton listo, porque no se va a usar mas
        btn_listo.setOpacity(0);
        btn_listo.setDisable(true);

        //pone el nombre del personaje elegido encima de la barra de vida
        texto1.setText(p1.m1);
        texto2.setText(p1.m2);
    }
    //almacenaran el nuevo valor de la vida despues de atacar y se pasan a la progressbar
    double nueva_vida1;
    double nueva_vida2;
    boolean defensa1 = false;
    boolean defensa2 = false;
    double a = Math.random() * (0.15 - 0.10) + 0.10;

    public void combate(ActionEvent event) throws IOException {
        //metodo defensa
        if (event.getSource().equals(btn_defender1)) {
            defensa1 = true;

            //se da valor al boton
            btn_atacar1.setOpacity(0.7);
            btn_atacar1.setDisable(true);
            btn_defender1.setOpacity(0.7);
            btn_defender1.setDisable(true);
            estado_alterado1.setOpacity(0.7);
            estado_alterado1.setDisable(true);

            btn_atacar2.setOpacity(1);
            btn_atacar2.setDisable(false);
            btn_defender2.setOpacity(1);
            btn_defender2.setDisable(false);
            
             if (vida2.getProgress() <= 0.5) {
                estado_alterado2.setOpacity(1);
                estado_alterado2.setDisable(false);
            }
        }
        //metodo de daño del personaje1 hacia el personaje2
        if (event.getSource().equals(btn_atacar1)) {

            if (defensa2 == true) {
                a = Math.random() * (0.09 - 0.07) + 0.07;
                defensa2 = false;
            } else {
                a = Math.random() * (0.15 - 0.10) + 0.10;
            }

            a = Math.round(a * 100d) / 100d;
            nueva_vida2 = vida2.getProgress() - a;
            vida2.setProgress(nueva_vida2);
            System.out.print(a+" ");

            //una vez ejecutado el ataque de seshabliita los botones del jugador1
            btn_atacar1.setOpacity(0.7);
            btn_atacar1.setDisable(true);
            btn_defender1.setOpacity(0.7);
            btn_defender1.setDisable(true);
            estado_alterado1.setOpacity(0.7);
            estado_alterado1.setDisable(true);

            //y se habilitan los del jugador2
            btn_atacar2.setOpacity(1);
            btn_atacar2.setDisable(false);
            btn_defender2.setOpacity(1);
            btn_defender2.setDisable(false);

            if (vida2.getProgress() <= 0.5) {
                estado_alterado2.setOpacity(1);
                estado_alterado2.setDisable(false);
            }
        }

        if (event.getSource().equals(estado_alterado1)) {
            if (defensa2 == true) {
                a = Math.random() * (0.15 - 0.10) + 0.10;
                defensa2 = false;
            } else {
                a = Math.random() * (0.25 - 0.20) + 0.20;
            }

            a = Math.round(a * 100d) / 100d;
            nueva_vida2 = vida2.getProgress() - a;
            vida2.setProgress(nueva_vida2);

            //una vez ejecutado el ataque de seshabliita los botones del jugador1
            btn_atacar1.setOpacity(0.7);
            btn_atacar1.setDisable(true);
            btn_defender1.setOpacity(0.7);
            btn_defender1.setDisable(true);
            estado_alterado1.setOpacity(0.7);
            estado_alterado1.setDisable(true);

            //y se habilitan los del jugador2
            btn_atacar2.setOpacity(1);
            btn_atacar2.setDisable(false);
            btn_defender2.setOpacity(1);
            btn_defender2.setDisable(false);

            if (vida2.getProgress() <= 0.5) {
                estado_alterado2.setOpacity(1);
                estado_alterado2.setDisable(false);
            }
        }

        a = Math.random() * (0.15 - 0.10) + 0.10;
        
        if(vida2.getProgress()<=0){
        TertiaryController.name = "player1";
        TertiaryController.monstruo = p1.m1;
        
        
        
        cambiar_ventana_final();
        }
    }

    public void combate2(ActionEvent event) throws IOException {
        if (event.getSource().equals(btn_defender2)) {
            defensa2 = true;
            btn_atacar1.setOpacity(1);
            btn_atacar1.setDisable(false);
            btn_defender1.setOpacity(1);
            btn_defender1.setDisable(false);
            
            if (vida1.getProgress() <= 0.5) {
                estado_alterado1.setOpacity(1);
                estado_alterado1.setDisable(false);
            }

            btn_atacar2.setOpacity(0.7);
            btn_atacar2.setDisable(true);
            btn_defender2.setOpacity(0.7);
            btn_defender2.setDisable(true);
             estado_alterado2.setOpacity(0.7);
            estado_alterado2.setDisable(true);
        }

        //metodo de daño del personaje2 hacia el personaje1
        if (event.getSource().equals(btn_atacar2)) {

            if (defensa1 == true) {
                a = Math.random() * (0.07 - 0.09) + 0.09;
                defensa1 = false;
            } else {
                a = Math.random() * (0.15 - 0.11) + 0.11;
            }

            a = Math.round(a * 100d) / 100d;
            nueva_vida1 = vida1.getProgress() - a;
            vida1.setProgress(nueva_vida1);

            btn_atacar1.setOpacity(1);
            btn_atacar1.setDisable(false);
            btn_defender1.setOpacity(1);
            btn_defender1.setDisable(false);
            
            if (vida1.getProgress() <= 0.5) {
                estado_alterado1.setOpacity(1);
                estado_alterado1.setDisable(false);
            }

            btn_atacar2.setOpacity(0.7);
            btn_atacar2.setDisable(true);
            btn_defender2.setOpacity(0.7);
            btn_defender2.setDisable(true);
            estado_alterado2.setOpacity(0.7);
            estado_alterado2.setDisable(true);

        }
        if (event.getSource().equals(estado_alterado2)) {
            if (defensa1 == true) {
                a = Math.random() * (0.15 - 0.10) + 0.10;
                defensa1 = false;
            } else {
                a = Math.random() * (0.25 - 0.20) + 0.20;
            }

            a = Math.round(a * 100d) / 100d;
            nueva_vida1 = vida1.getProgress() - a;
            vida1.setProgress(nueva_vida1);

            btn_atacar1.setOpacity(1);
            btn_atacar1.setDisable(false);
            btn_defender1.setOpacity(1);
            btn_defender1.setDisable(false);
            
            if (vida1.getProgress() <= 0.5) {
                estado_alterado1.setOpacity(1);
                estado_alterado1.setDisable(false);
            }

            btn_atacar2.setOpacity(0.7);
            btn_atacar2.setDisable(true);
            btn_defender2.setOpacity(0.7);
            btn_defender2.setDisable(true);
            estado_alterado2.setOpacity(0.7);
            estado_alterado2.setDisable(true);
        }
        
        a = Math.random() * (0.15 - 0.10) + 0.10;
        
        if(vida1.getProgress()<=0){
        TertiaryController.name = "player2";
        TertiaryController.monstruo = p1.m2;
        
        cambiar_ventana_final();
        }
    }
    
      @FXML
    private void cambiar_ventana_final() throws IOException {
        App.setRoot("tertiary");
    }
}