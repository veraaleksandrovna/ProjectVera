package Controllers.Analytics;

import Enums.RequestType;
import Models.Entities.Produce;
import Utility.GetService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiagramController implements Initializable {
    public Button buttonBack;
    public ChoiceBox choiceboxProduction;
    public NumberAxis numberAxis = new NumberAxis();
    public CategoryAxis categoryAxis = new CategoryAxis();
    public LineChart<String,Number> Chart = new LineChart<String,Number>(categoryAxis,numberAxis);
    List<Produce> produceList;

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GetService<Produce> produceGetService = new GetService<Produce>(Produce.class);
        Type listType = new TypeToken<ArrayList<Produce>>() { }.getType();
        produceList = new Gson().fromJson(produceGetService.GetEntities(RequestType.GETALL_PRODUCT), listType);
        for (Produce produce :
                produceList) {
            choiceboxProduction.getItems().add(produce.getPrName());
        }
    }

    public void Produce_Chosen(ActionEvent actionEvent) {
        Produce produce = produceList.get(choiceboxProduction.getSelectionModel().getSelectedIndex());
        Chart.getData().removeAll(Chart.getData());
        XYChart.Series series = new XYChart.Series();
        series.setName("Оцениваемая продукция");
        series.getData().add(new XYChart.Data<>("Внешний вид", produce.getAppearance()));
        series.getData().add(new XYChart.Data<>("Размер", produce.getSize()));
        series.getData().add(new XYChart.Data<>("Вес", produce.getWeight()));
        series.getData().add(new XYChart.Data<>("Толщина", produce.getThickness()));
        series.getData().add(new XYChart.Data<>("Прочность", produce.getStrength()));
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Базовый продукт");
        series2.getData().add(new XYChart.Data<>("Внешний вид", produce.getBaseProductsBaseProductId().getAppearance()));
        series2.getData().add(new XYChart.Data<>("Размер", produce.getBaseProductsBaseProductId().getSize()));
        series2.getData().add(new XYChart.Data<>("Вес", produce.getBaseProductsBaseProductId().getWeight()));
        series2.getData().add(new XYChart.Data<>("Толщина", produce.getBaseProductsBaseProductId().getThickness()));
        series2.getData().add(new XYChart.Data<>("Прочность", produce.getBaseProductsBaseProductId().getStrength()));
        Chart.getData().addAll(series,series2);
    }
}
