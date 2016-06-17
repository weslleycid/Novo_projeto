package br.com.application;

import br.com.controller.Main;
import br.com.controller.Usuarios;
import br.com.module.DAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 07/03/16.
 */
public class Login extends DAO{
    @FXML
    private FlowPane FPlogin;
    @FXML
    public PasswordField PFsenha;
    @FXML
    public TextField TFusuario;
    @FXML
    private Button BTNentrar;
    private ActionEvent event;

    Main main = new Main();
    Usuarios usuarios = new Usuarios();

    //=============== ação do botão entrar ====================
    public void onEntrar(ActionEvent event){
          this.event = event;

        if (TFusuario.getText().equals("") || PFsenha.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("atenção");
            a.setContentText("preencha todos os campos!");
            a.showAndWait();
        }else{
            loga();
        }
    }
    //============fim ação onEntrar=============

    //============metodo que fecha a aplicação=========
    public void onSair() {

        Platform.exit();
    }
    //===========fim onSair==============


    //==========metodo usado para verificar usuário no banco de dados==============
    public void loga(){
        usuarios.setEmail(String.valueOf(TFusuario.getText()));
        usuarios.setSenha(String.valueOf(PFsenha.getText()));
        try {
            abrirConexao();
            query = "SELECT usuario,senha FROM administrador where usuario = ? && senha = ?";
            pstnt = (PreparedStatement) conexao.prepareStatement(query);
            pstnt.setString(1, usuarios.getSenha());
            pstnt.setString(2, usuarios.getSenha());
            rs = pstnt.executeQuery();

                if (rs.next()) {
                        main.tela = "../viewer/Menu.fxml";
                        main.titulo = "sistema vendas";
                        main.resisable = true;
                        main.maximized = true;
                        main.novaTela();
                      fecharBanco();
                } else {
                    Alert e = new Alert(Alert.AlertType.INFORMATION);
                    e.setHeaderText("usuário ou senha incorreto!!!");
                    e.setContentText("tente novamente");
                    e.showAndWait();
                }
        }catch (Exception erro){
            Alert e = new Alert(Alert.AlertType.ERROR);
            e.setHeaderText("erro database");
            e.setContentText(""+erro.getMessage());
            e.showAndWait();
        }
    }
}
