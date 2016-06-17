package br.com.module;
    import javafx.scene.control.Alert;

    import java.sql.PreparedStatement;
    import java.sql.SQLException;

    /**
     * Created by lucas on 09/04/16.
     */
    public class Exclui_Produto extends DAO{
        private Integer codigo_produto;

        public void excluir() throws SQLException {
            query = "delete from produto where codigo_Produto = '"+codigo_Produto+"'";
            try {
                abrirConexao();
                pstnt = (PreparedStatement) conexao.prepareStatement(query);
                pstnt.execute();

                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setHeaderText(null);
                b.setContentText("Produto excluido com sucesso!!!");
                b.showAndWait();
            }catch (Exception erro){
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setHeaderText(""+erro);
                b.setContentText("falha ao excluir produto!!!");
                b.showAndWait();
            }
        }
    }


