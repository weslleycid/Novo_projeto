package br.com.module;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lucas on 09/04/16.
 */
public class Exclui_Cliente extends DAO{
    public Integer codigo;

    public void excluir() throws SQLException {
        query = "delete from cliente where codigo_cliente = '"+codigo+"'";
        try {
            abrirConexao();
            pstnt = (PreparedStatement) conexao.prepareStatement(query);
            pstnt.execute();

            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setHeaderText(null);
            b.setContentText("cliente excluido com sucesso!!!");
            b.showAndWait();
        }catch (Exception erro){
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setHeaderText(""+erro);
            b.setContentText("falha ao excluir cliente!!!");
            b.showAndWait();
        }
    }
}
