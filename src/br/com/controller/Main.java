package br.com.controller;

import br.com.application.Login;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public String tela;
    @FXML
    public Stage stage,primaryStage;
    public String titulo = "";
    public Boolean maximized = false;
    public Boolean resisable = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../viewer/Menu.fxml"));
        this.primaryStage.setTitle("sistema de vendas");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.setWidth(720);
        this.primaryStage.setHeight(480);
        this.primaryStage.setResizable(true);
        this.primaryStage.setMaximized(true);
        this.primaryStage.show();
    }
    //=======metodo para trocar de tela que sera utilizado em outras classes========
    public void trocarTela(ActionEvent event) {
        Node node = (Node) event.getSource();

        stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(tela));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinHeight(272);
        stage.setMinWidth(480);
        stage.show();
    }
    @FXML
    public void novaTela() throws IOException {
        Stage stage = new Stage();
        Pane root = FXMLLoader.load(getClass().getResource(tela));
        Scene scene = new Scene(root);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.setMaximized(maximized);
        stage.setResizable(resisable);
        stage.setMinHeight(272);
        stage.setMinWidth(480);
        stage.setHeight(480);
        stage.setWidth(640);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
