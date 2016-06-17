package br.com.module;

import br.com.controller.Clientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lucas on 07/04/16.
 */
public class Cadastro_Cliente extends DAO{

    @FXML
    private TextField TFnome;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFfone;
    @FXML
    private PasswordField PFsenha;
    @FXML
    private PasswordField PFcsenha;
    @FXML
    private TextField TFcpf;
    @FXML
    private TextField TFendereco;
    @FXML
    private TextField TFcep;

    Clientes c = new Clientes();

    @FXML
    private void onCadastrar(ActionEvent actionEvent) throws SQLException {
        cadastrar();
    }
    @FXML
    private void onCancelar(){
    }

    private void cadastrar() throws SQLException {
        c.setNome(String.valueOf(TFnome.getText()));
        c.setEmail(String.valueOf(TFemail.getText()));
        c.setFone(Integer.valueOf(TFfone.getText()));
        c.setSenha(String.valueOf(PFsenha.getText()));
        c.setConfsenha(String.valueOf(PFcsenha.getText()));
        c.setCpf(String.valueOf(TFcpf.getText()));
        c.setEndereco(String.valueOf(TFendereco.getText()));
        c.setCep(Integer.valueOf(TFcep.getText()));

      try {
          query = "insert into cliente values(null,?,?,?,?,?,null,null,?,?)";
          abrirConexao();
          pstnt = (PreparedStatement) conexao.prepareStatement(query);
          pstnt.setString(1, c.getNome());
          pstnt.setString(2, c.getEmail());
          pstnt.setInt(3, c.getFone());
          pstnt.setString(4, c.getSenha());
          pstnt.setString(5, c.getCpf());
          pstnt.setString(6, c.getEndereco());
          pstnt.setInt(7, c.getCep());
          pstnt.execute();
      }catch(Exception e){
          Alert a = new Alert(Alert.AlertType.ERROR);
          a.setHeaderText("ERRO: "+e.getMessage());
          a.setContentText("tente novamente!!!");
          a.showAndWait();
      }
    }
}
