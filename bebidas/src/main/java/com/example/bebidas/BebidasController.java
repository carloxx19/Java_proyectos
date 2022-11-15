package com.example.bebidas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import org.json.JSONArray;
import javafx.scene.control.ChoiceBox;

public class BebidasController implements Initializable {

    @FXML
    private TableView<?> bebidasVer;

    @FXML
    private ComboBox<Categoria> categorias;

    @FXML
    private TextField filtro;

    @FXML
    private ChoiceBox<Categoria> choice;

    private ObservableList<Categoria> listaCategorias;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        try {
            jsonCategorias();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        asociarElementos();
    }

    private void instancias() {
        listaCategorias = FXCollections.observableArrayList();
    }

    private void jsonCategorias() throws IOException {
        String url = "https://api.chucknorris.io/jokes/categories";

        InputStream inputStream;
        inputStream = new  URL(url).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String response = bufferedReader.readLine();
        JSONArray jsonArray = new JSONArray(response);
        String categoria;

        for (int i = 0; i < jsonArray.length(); i++) {
            categoria = (String) jsonArray.get(i);
            listaCategorias.add(new Categoria(categoria));
        }
    }

    private void asociarElementos() {
        choice.setItems(listaCategorias);
        categorias.setItems(listaCategorias);
    }
}