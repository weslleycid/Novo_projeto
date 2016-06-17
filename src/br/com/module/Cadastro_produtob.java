package br.com.module;


import br.com.controller.Produtos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by weslley on 10/05/2016.
 */
public class Cadastro_produtob extends  DAO {
    @FXML
    private TextField TFcoddigob;
    @FXML
    private TextField TFnomep;
    @FXML
    private TextField TFmarcap;
    @FXML
    private TextField TFprecop;
    @FXML
    private TextField TFquantidadep;
    @FXML
    private TextField TFfornecedorp;
    @FXML
    private TextField TAdescricaop;

    Produtos p = new Produtos();

    @FXML
    private void onCadastrar(ActionEvent actionEvent) throws SQLException {
        cadastrar();
    }

    @FXML
    private void onCancelar() {
    }
    private void cadastrar() throws SQLException {
        p.setCodigo_barras(Integer.valueOf(TFcoddigob.getText()));
        p.setNome_produto(String.valueOf(TFnomep.getText()));
        p.setMarca_produto(String.valueOf(TFmarcap.getText()));
        p.setPreco_produto(Double.valueOf(TFprecop.getText()));
        p.setQuantidade_produto(Integer.valueOf(TFquantidadep.getText()));
        p.setFornecedor_produto(String.valueOf(TFfornecedorp.getText()));
        p.setDescricao_produto(String.valueOf(TAdescricaop.getText()));


        try {
            query = "insert into produtos values(null,?,?,?,?,?,?,?,)";
            abrirConexao();
            pstnt = (PreparedStatement) conexao.prepareStatement(query);
            pstnt.setInt(1, p.getCodigo_barras());
            pstnt.setString(2, p.getNome_produto());
            pstnt.setString(3, p.getMarca_produto());
            pstnt.setDouble(4,p.getPreco_produto());
            pstnt.setInt(5,p.getQuantidade_produto());
            pstnt.setString(6,p.getFornecedor_produto());
            pstnt.setString(7,p.getDescricao_produto());
            pstnt.execute();
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("ERRO: "+e.getMessage());
            a.setContentText("tente novamente!!!");
            a.showAndWait();
        }
    }
}