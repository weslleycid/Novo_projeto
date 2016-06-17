package br.com.application;

import br.com.module.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;

/**
 * Created by lucas on 25/03/16.
 */
public class TelaPrincipal extends DAO{
    @FXML
    private Label Ltotalc;
    @FXML
    private LineChart<String, Integer> graph;

    @FXML
    private void initialize(){
        ObservableList<XYChart.Series<String, Integer>> lineChartData = FXCollections.observableArrayList();

        LineChart.Series<String, Integer> series = new LineChart.Series<String, Integer>();
        series.setName("vendas diarias");
        series.getData().add(new XYChart.Data("domingo", 23));
        series.getData().add(new XYChart.Data("segunda", 14));
        series.getData().add(new XYChart.Data("terça", 15));
        series.getData().add(new XYChart.Data("quarta", 24));
        series.getData().add(new XYChart.Data("quinta", 34));
        series.getData().add(new XYChart.Data("sexta", 36));
        series.getData().add(new XYChart.Data("sabado", 22));

        lineChartData.add(series);

        graph.setData(lineChartData);
        graph.createSymbolsProperty();
        atRecentes();
    }

    private void atRecentes(){
        try {
            query = "SELECT COUNT(id_cliente) as quantidade FROM cliente";
            abrirConexao();
            pstnt = (PreparedStatement) conexao.prepareStatement(query);
            rs = pstnt.executeQuery();
            rs.first();
            Ltotalc.setText(Integer.toString(rs.getInt("quantidade"))+" clientes");
        }catch (Exception erro){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(""+erro);
            a.setContentText("erro");
            a.showAndWait();
        }
    }
}
