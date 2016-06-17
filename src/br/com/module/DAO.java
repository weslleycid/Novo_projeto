package br.com.module;
/*
* classe usada para fazer a conexão com banco de dados
* */

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    //variaveis usadas para logar usuario ao banco de dados
    private String url = "jdbc:mysql://localhost:3306/sistema_vendas?autoReconnect=true&useSSL=false";
    private String drive = "com.mysql.jdbc.Driver";
    private String user = "root";
    private String password = "mysql";
    public String query;

    public ResultSet rs;
    public Connection conexao;
    public PreparedStatement pstnt;

    public void abrirConexao() throws SQLException{
        try {
            Class.forName(drive);
            conexao = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException ex) {//tratamento de erro de drive

            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);

        }catch (SQLException erro){
            System.out.println(erro);
            throw new RuntimeException(erro);
        }
    }

    public void fecharBanco() throws Exception{//criando metodo que fechao conexão com o banco
        if (pstnt != null) { //limpando os dados de conexão
            pstnt.close();//fechando o ambiente de conexão
            System.out.println("Execuçao da Query fechada\n");
        }
    }
}
