package Controllers.Tables;

import Enums.RequestType;
import Models.Entities.Standart;
import Models.TCP.Request;
import Models.TableEntities.TableStandart;
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

public class StandartController implements Initializable {
    public TableView<TableStandart> tableview;
    public TableColumn<TableStandart, String> columnId;
    public TableColumn<TableStandart, String> columnName;
    public TableColumn<TableStandart, String> columnStrength;
    public TableColumn<TableStandart, String> columnWeight;
    public TableColumn<TableStandart, String> columnSize;
    public TableColumn<TableStandart, String> columnThickness;
    public TableColumn<TableStandart, String> columnAppearance;
    public Button buttonBack;
    public Button buttonEdit;
    public Button buttonDelete;
    public Button buttonAdd;

    public void TableViewClicked(MouseEvent mouseEvent) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            buttonDelete.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
        }
    }

    public void Add_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/StandartNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Delete_Pressed(ActionEvent actionEvent) throws IOException {
        Request requestModel = new Request();
        TableStandart tableStandart = tableview.getSelectionModel().getSelectedItem();
        requestModel.setRequestMessage(new Gson().toJson(new Standart(tableStandart.getId())));
        requestModel.setRequestType(RequestType.DELETE_STANDART);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        tableview.getItems().remove(tableStandart);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        ClientSocket.getInstance().getInStream().readLine();
    }

    public void Edit_Pressed(ActionEvent actionEvent) throws IOException {
        TableStandart tableStandart = tableview.getSelectionModel().getSelectedItem();
        ClientSocket.getInstance().setStandartId(tableStandart.getId());
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/StandartNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnStrength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        columnThickness.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        columnAppearance.setCellValueFactory(new PropertyValueFactory<>("appearance"));
        GetService<Standart> batchGetService = new GetService<Standart>(Standart.class);
        Type listType = new TypeToken<ArrayList<Standart>>() {
        }.getType();
        List<Standart> standarts = new Gson().fromJson(batchGetService.GetEntities(RequestType.GETALL_STANDART), listType);
        ObservableList<TableStandart> list = FXCollections.observableArrayList();
        if (standarts.size() != 0 && standarts != null)
            for (Standart standart :
                    standarts) {
                TableStandart tableStandart = new TableStandart(standart.getBaseProductId(), standart.getPrName(), standart.getStrength(), standart.getWeight(), standart.getSize(), standart.getThickness(), standart.getAppearance());
                list.add(tableStandart);
            }
        tableview.setItems(list);
    }
}
