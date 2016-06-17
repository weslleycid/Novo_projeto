package br.com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;

/**
 * Created by lucas on 24/03/16.
 */
public class Menu {
    @FXML
    private BorderPane BPmenu;
    @FXML
    private Label LBtitulo;


    public void onPrincipal() throws IOException {
        Parent principal = FXMLLoader.load(getClass().getResource("../viewer/Principal.fxml"));
        BPmenu.setCenter(principal);
        LBtitulo.setText("principal");
    }

    public void onCliente() throws IOException {
        Parent cliente = FXMLLoader.load(getClass().getResource("../viewer/tabelas/Cliente.fxml"));
        BPmenu.setCenter(cliente);
        LBtitulo.setText("clientes");
    }

}
