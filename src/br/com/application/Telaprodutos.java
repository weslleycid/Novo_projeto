package br.com.application;

import br.com.controller.Main;
import br.com.module.DAO;
import br.com.module.Exclui_Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by weslley on 10/05/2016.
 */
public class Telaprodutos {


    /**
     * Created by lucas on 26/03/16.
     */
    public class TelaProduto extends DAO {
        @FXML
        private TextField TFpesquisar;
        @FXML
        private Button BTpesquisar;
        @FXML
        private TableView TVprodutos;
        @FXML
        private TableColumn TCcodigop;
        @FXML
        private TableColumn TCcodigob;
        @FXML
        private TableColumn TCnomep;
        @FXML
        private TableColumn TCmarcap;
        @FXML
        private TableColumn TCprecop;
        @FXML
        private TableColumn TCquantidadep;
        @FXML
        private TableColumn TCfornecedorp;
        @FXML
        private TableColumn TCdescricaop;

        private String exc;
        private Integer cod;

        private Integer codigo_produto;
        private Integer codigo_barras;
        private String nome_produto;
        private String marca_produto;
        private Double preco_produto;
        private Integer quantidade_produto;
        private String fornecedor_produto;
        private String descricao_produto;

        br.com.controller.Main Main =new Main();
        Exclui_Produto excluir = new Exclui_Produto();

        @FXML
        private void onPesquisar() throws SQLException {
            initialize();
        }

        public void onCadastrar() throws IOException {
            Main.tela = "../viewer/cadastro/Cadastro_produto.fxml";
            Main.titulo = "cadastro de produtos";
            Main.resisable = false;
            Main.novaTela();
        }

        @FXML
        public void onAtualizar() throws SQLException {
            TFpesquisar.setText("");
            initialize();
        }

        public void onExcluir() throws SQLException {
            br.com.application.Telaprodutos.Produtos p = (br.com.application.Telaprodutos.Produtos) TVprodutos.getSelectionModel().getSelectedItem();
            exc = String.valueOf(u.getNome());
            cod = Integer.valueOf(u.getCodigo());

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("deseja excluir produto: "+exc);

            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK){
                excluir.codigo_produto = cod;
                excluir.excluir();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }

        public class Produtos {

            private Integer codigo_produto;
            private Integer codigo_barras ;
            private String nome_produto;
            private String marca_produto;
            private Double  preco_produto;
            private Integer quantidade_produto;
            private String fornecedor_produto;
            private String descricao_produto;

            public Produtos(Integer codigo_produto, Integer codigo_barras, String nome_produto, String marca_produto , Double preco_produto, Integer quantidade_produto, String fornecedor_produto, String descricao_produto) {
                this.codigo_produto = codigo_produto;
                this.codigo_barras = codigo_barras;
                this.nome_produto = nome_produto;
                this.marca_produto = marca_produto;
                this.preco_produto = preco_produto;
                this.quantidade_produto = quantidade_produto;
                this.fornecedor_produto = fornecedor_produto;
                this.descricao_produto = descricao_produto;
            }

            public Integer getCodigo_produto() {
                return codigo_produto;
            }

            public Integer getCodigo_barras() {
                return codigo_barras;
            }

            public String getNome_produto() {
                return nome_produto;
            }

            public String getMarca_produto() {
                return marca_produto;
            }

            public Double getPreco_produto() {
                return preco_produto;
            }

            public Integer getQuantidade_produto() {
                return quantidade_produto;
            }

            public String getFornecedor_produto() {
                return fornecedor_produto;
            }

            public String getDescricao_produto() {
                return descricao_produto;
            }
        }

        @FXML
        private void initialize() throws SQLException {
            ObservableList<br.com.application.Telaprodutos.Produtos> produtos = FXCollections.observableArrayList();
            try {
                abrirConexao();
                query = "select * from produtos where nome like('%"+TFpesquisar.getText()+"%')";
                pstnt = (PreparedStatement) conexao.prepareStatement(query);
                rs = pstnt.executeQuery();

                while (rs.next()){
                    codigo_produto = rs.getInt("id_produtos");
                    codigo_barras = rs.getInt("codigo_barras");
                    nome_produto = rs.getString("nome");
                    marca_produto = rs.getString("marca");
                    preco_produto = rs.getDouble("preco");
                    quantidade_produto = rs.getInt("quantidade");
                    fornecedor_produto = rs.getString("fornecedor");
                    descricao_produto = rs.getString("descricao");
                    produtos.addAll(new br.com.application.Telaprodutos.Produtos(codigo_produto, codigo_barras, nome_produto, marca_produto, preco_produto, quantidade_produto, fornecedor_produto, descricao_produto));
                }
            }catch (Exception erro){
                Alert e = new Alert(Alert.AlertType.ERROR);
                e.setHeaderText("erro database\n"+erro.getMessage());
                e.setContentText(""+erro.getMessage());
                e.showAndWait();
                System.out.println(erro.getMessage());
            }

            TCcodigop.setCellValueFactory(new PropertyValueFactory<>("codigop"));
            TCcodigob.setCellValueFactory(new PropertyValueFactory<>("codigo_barras"));
            TCnomep.setCellValueFactory(new PropertyValueFactory<>("nome"));
            TCmarcap.setCellValueFactory(new PropertyValueFactory<>("marca"));
            TCprecop.setCellValueFactory(new PropertyValueFactory<>("preco"));
            TCquantidadep.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            TCfornecedorp.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
            TCdescricaop.setCellValueFactory(new PropertyValueFactory<>("descricao"));

            TVprodutos.setItems(FXCollections.observableArrayList(produtos));

        }
    }

}
