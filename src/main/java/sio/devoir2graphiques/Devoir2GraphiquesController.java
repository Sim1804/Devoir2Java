package sio.devoir2graphiques;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sio.devoir2graphiques.Tools.ConnexionBDD;
import sio.devoir2graphiques.Tools.GraphiqueController;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

public class Devoir2GraphiquesController implements Initializable
{
    ConnexionBDD maCnx;
    GraphiqueController graphiqueController;
    HashMap<Integer, Double> datasGraphique1;
    HashMap<String,Integer> datasGraphique3;
    XYChart.Series<String, Number> serieGraph1;
    XYChart.Series<String, Number> serieGraph2;
    @FXML
    private Button btnGraph1;
    @FXML
    private Button btnGraph2;
    @FXML
    private Button btnGraph3;
    @FXML
    private AnchorPane apGraph1;
    @FXML
    private LineChart graph1;
    @FXML
    private Label lblTitre;
    @FXML
    private AnchorPane apGraph2;
    @FXML
    private AnchorPane apGraph3;
    @FXML
    private PieChart graph3;
    @FXML
    private BarChart graph2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitre.setText("Devoir : Graphique n°1");
        apGraph1.toFront();

        // A vous de jouer
        try {
            maCnx = new ConnexionBDD();
            graphiqueController = new GraphiqueController();

            datasGraphique1 = new HashMap<>();
            datasGraphique1 = graphiqueController.getDatasGraphique1();
            serieGraph1 = new XYChart.Series<>();
            serieGraph1.setName("Moyenne");
            graph1.getData().clear();

            for (Integer age : datasGraphique1.keySet())
            {
                serieGraph1.getData().add(new XYChart.Data(age,datasGraphique1.get(age)));
            }
            graph1.getData().add(serieGraph1);
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void menuClicked(Event event) throws SQLException {
        if(event.getSource() == btnGraph1)
        {
            lblTitre.setText("Devoir : Graphique n°1");
            apGraph1.toFront();
            graph1.getData().clear();
            datasGraphique1 = new HashMap<>();
            datasGraphique1 = graphiqueController.getDatasGraphique1();
            serieGraph1 = new XYChart.Series<>();
            serieGraph1.setName("Moyenne");

            for (Integer age : datasGraphique1.keySet())
            {
                serieGraph1.getData().add(new XYChart.Data(age,datasGraphique1.get(age)));
            }
            graph1.getData().add(serieGraph1);
            graph1.setTitle("Moyenne de salaire par âge");
            // A vous de jouer

        }
        else if(event.getSource() == btnGraph2)
        {
            lblTitre.setText("Devoir : Graphique n°2");
            apGraph2.toFront();

            // A vous de jouer
            graph2.getData().clear();
            serieGraph2 = new XYChart.Series<>();
            HashMap<String,Integer> datasGraphique2 = graphiqueController.getDatasGgraphique2();
            for (String nombre : datasGraphique3.keySet())
            {
                serieGraph2.setName(nombre);
                graph2.getData().add(new XYChart.Data<>(datasGraphique2.get(nombre), Integer.parseInt(String.valueOf(datasGraphique2.get(nombre)))));
            }
            graph2.getData().add(serieGraph2);
            serieGraph2 = new XYChart.Series<>();

        }
        else
        {
            lblTitre.setText("Devoir : Graphique n°3");
            apGraph3.toFront();

            // A vous de jouer
            graph3.getData().clear();

            ObservableList<PieChart.Data> datasGraph3 = FXCollections.observableArrayList();
            datasGraphique3 = graphiqueController.getDatasGgraphique3();

            for (String sexe : datasGraphique3.keySet())
            {
                datasGraph3.add(new PieChart.Data(sexe,datasGraphique3.get(sexe)));
            }
            graph2.setData(datasGraph3);

        }
    }
}