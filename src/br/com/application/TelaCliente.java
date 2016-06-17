package br.com.application;

import br.com.controller.Main;
import br.com.module.DAO;
import br.com.module.Exclui_Cliente;
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
 * Created by lucas on 26/03/16.
 */
public class TelaCliente extends DAO{
    @FXML
    private TextField TFpesquisar;
    @FXML
    private Button BTpesquisar;
    @FXML
    private TableView TVclientes;
    @FXML
    private TableColumn TCcodigo;
    @FXML
    private TableColumn TCnome;
    @FXML
    private TableColumn TCemail;
    @FXML
    private TableColumn TCfone;
    @FXML
    private TableColumn TCsenha;
    @FXML
    private TableColumn TCcpf;
    @FXML
    private TableColumn TCstatus;
    @FXML
    private TableColumn TCdatCadastro;
    @FXML
    private TableColumn TCendereco;
    @FXML
    private TableColumn TCcep;

    private String exc;
    private Integer cod;

    private Integer codigo;
    private String nome;
    private String email;
    private Integer fone;
    private String senha;
    private String cpf;
    private Boolean ativo;
    private Timestamp data_cadastro;
    private String endereco;
    private Integer cep;

    Main Main =new Main();
    Exclui_Cliente excluir = new Exclui_Cliente();

    @FXML
    private void onPesquisar() throws SQLException {
        initialize();
    }

    public void onCadastrar() throws IOException {
        Main.tela = "../viewer/cadastro/Cadastro_Cliente.fxml";
        Main.titulo = "cadastro";
        Main.resisable = false;
        Main.novaTela();
    }

    @FXML
    public void onAtualizar() throws SQLException {
        TFpesquisar.setText("");
        initialize();
    }

    public void onExcluir() throws SQLException {
        Clientes u = (Clientes) TVclientes.getSelectionModel().getSelectedItem();
        exc = String.valueOf(u.getNome());
        cod = Integer.valueOf(u.getCodigo());

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("ATENÇÃO");
        a.setContentText("deseja excluir cliente: "+exc);

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK){
            excluir.codigo = cod;
            excluir.excluir();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public static class Clientes {

        private Integer codigo;
        private String nome;
        private String email;
        private Integer fone;
        private String senha;
        private String cpf;
        private Boolean ativo;
        private Timestamp data_cadastro;
        private String endereco;
        private Integer cep;

        public Clientes(Integer codigo, String nome, String email, Integer fone, String senha, String cpf, Boolean ativo, Timestamp data_cadastro, String endereco, Integer cep) {
            this.codigo = codigo;
            this.nome = nome;
            this.email = email;
            this.fone = fone;
            this.senha = senha;
            this.cpf = cpf;
            this.ativo = ativo;
            this.data_cadastro = data_cadastro;
            this.endereco = endereco;
            this.cep = cep;
        }

        public Integer getCodigo() {
            return codigo;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public Integer getFone() {
            return fone;
        }

        public String getSenha() {
            return senha;
        }

        public String getCpf() {
            return cpf;
        }

        public Boolean getAtivo() {
            return ativo;
        }

        public Timestamp getData_cadastro() {
            return data_cadastro;
        }

        public String getEndereco() {
            return endereco;
        }

        public Integer getCep() {
            return cep;
        }
    }

    @FXML
    private void initialize() throws SQLException {
        ObservableList<Clientes> clientes = FXCollections.observableArrayList();
        try {
            abrirConexao();
            query = "select * from cliente where nome like('%"+TFpesquisar.getText()+"%')";
            pstnt = (PreparedStatement) conexao.prepareStatement(query);
            rs = pstnt.executeQuery();

        while (rs.next()){
            codigo = rs.getInt("id_cliente");
            nome = rs.getString("nome");
            email = rs.getString("email");
            fone = rs.getInt("fone");
            senha = rs.getString("senha");
            cpf = rs.getString("cpf");
            ativo = rs.getBoolean("ativo");
            data_cadastro = rs.getTimestamp("data_cadastro");
            endereco = rs.getString("endereco");
            cep = rs.getInt("cep");
            clientes.addAll(new Clientes(codigo, nome, email, fone, senha, cpf, ativo, data_cadastro, endereco, cep));
        }
        }catch (Exception erro){
            Alert e = new Alert(Alert.AlertType.ERROR);
            e.setHeaderText("erro database\n"+erro.getMessage());
            e.setContentText(""+erro.getMessage());
            e.showAndWait();
            System.out.println(erro.getMessage());
        }

        TCcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        TCnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TCemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TCfone.setCellValueFactory(new PropertyValueFactory<>("fone"));
        TCsenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        TCcpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TCstatus.setCellValueFactory(new PropertyValueFactory<>("ativo"));
        TCdatCadastro.setCellValueFactory(new PropertyValueFactory<>("data_cadastro"));
        TCendereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TCcep.setCellValueFactory(new PropertyValueFactory<>("cep"));

        TVclientes.setItems(FXCollections.observableArrayList(clientes));

    }
}
