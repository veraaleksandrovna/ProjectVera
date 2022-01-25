package Controllers.Tables;

import Enums.RequestType;
import Models.Entities.Produce;
import Models.TCP.Request;
import Models.TableEntities.TableProduce;
import Utility.ClientSocket;
import Utility.GetService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProduceController  implements Initializable {
    public TableView<TableProduce> tableview;
    public TableColumn<TableProduce,String> columnId;
    public TableColumn<TableProduce,String> columnName;
    public TableColumn<TableProduce,String> columnStrength;
    public TableColumn<TableProduce,String> columnWeight;
    public TableColumn<TableProduce,String> columnSize;
    public TableColumn<TableProduce,String> columnThickness;
    public TableColumn<TableProduce,String> columnAppearance;
    public TableColumn<TableProduce,String> columnStandart;
    public TableColumn<TableProduce,String> columnBatch;
    public TableColumn<TableProduce,String> columnWorker;
    public Button buttonAdd;
    public Button buttonDelete;
    public Button buttonEdit;
    public Button buttonBack;

    public void TableViewClicked(MouseEvent mouseEvent) {
        if(tableview.getSelectionModel().getSelectedItem()!=null){
            buttonDelete.setDisable(false);
            buttonEdit.setDisable(false);
        }
        else {
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
        }
    }

    public void Back_Pressed(ActionEvent actionEvent)  throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Edit_Pressed(ActionEvent actionEvent) throws IOException {
        TableProduce tableProduce = tableview.getSelectionModel().getSelectedItem();
        ClientSocket.getInstance().setProduceId(tableProduce.getId());
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/ProduceNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Delete_Pressed(ActionEvent actionEvent) throws IOException {
        Request requestModel = new Request();
        TableProduce tableProduce = tableview.getSelectionModel().getSelectedItem();
        requestModel.setRequestMessage(new Gson().toJson(new Produce(tableProduce.getId())));
        requestModel.setRequestType(RequestType.DELETE_PRODUCT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        tableview.getItems().remove(tableProduce);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        ClientSocket.getInstance().getInStream().readLine();
    }

    public void Add_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/ProduceNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnAppearance.setCellValueFactory(new PropertyValueFactory<>("Appearance"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnWorker.setCellValueFactory(new PropertyValueFactory<>("Worker"));
        columnBatch.setCellValueFactory(new PropertyValueFactory<>("Batch"));
        columnStandart.setCellValueFactory(new PropertyValueFactory<>("Standart"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        columnStrength.setCellValueFactory(new PropertyValueFactory<>("Strength"));
        columnThickness.setCellValueFactory(new PropertyValueFactory<>("Thickness"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));;
        GetService<Produce> produceGetService = new GetService<Produce>(Produce.class);
        Type listType = new TypeToken<ArrayList<Produce>>() {
        }.getType();
        List<Produce> produces = new Gson().fromJson(produceGetService.GetEntities(RequestType.GETALL_PRODUCT), listType);
        ObservableList<TableProduce> list = FXCollections.observableArrayList();
        if (produces.size() != 0 && produces!=null)
            for (Produce produce :
                produces) {
            TableProduce tableProduce = new TableProduce(produce.getProductId(),produce.getPrName(),
                    produce.getStrength(),produce.getWeight(),produce.getSize(),produce.getThickness(),produce.getAppearance(),
                    produce.getBaseProductsBaseProductId().getPrName(),produce.getSeriesSeriesId().getSeriaId(),produce.getSeriesWorkersWorkerId().getFio());
            list.add(tableProduce);
        }
        tableview.setItems(list);

    }
}
