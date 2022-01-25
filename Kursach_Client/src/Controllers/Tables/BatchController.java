package Controllers.Tables;

import Enums.RequestType;
import Models.Entities.Batch;
import Models.TCP.Request;
import Models.TableEntities.TableBatch;
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

public class BatchController implements Initializable {
    public TableView<TableBatch> tableview;
    public TableColumn<TableBatch, String> columnId;
    public TableColumn<TableBatch, String> columnDate;
    public TableColumn<TableBatch, String> columnWorker;
    public TableColumn<TableBatch, String> columnUser;
    public Button buttonAdd;
    public Button buttonDelete;
    public Button buttonEdit;
    public Button buttonBack;

    public void TableViewClicked(MouseEvent mouseEvent) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            buttonDelete.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
        }
    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Edit_Pressed(ActionEvent actionEvent) throws IOException {
        TableBatch tableBatch = tableview.getSelectionModel().getSelectedItem();
        ClientSocket.getInstance().setBatchId(tableBatch.getId());
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/BatchNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Delete_Pressed(ActionEvent actionEvent) throws IOException {
        Request requestModel = new Request();
        TableBatch tableBatch = tableview.getSelectionModel().getSelectedItem();
        requestModel.setRequestMessage(new Gson().toJson(new Batch(tableBatch.getId())));
        requestModel.setRequestType(RequestType.DELETE_BATCH);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        tableview.getItems().remove(tableBatch);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        ClientSocket.getInstance().getInStream().readLine();
    }

    public void Add_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/BatchNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("User"));
        columnWorker.setCellValueFactory(new PropertyValueFactory<>("Worker"));
        GetService<Batch> batchGetService = new GetService<Batch>(Batch.class);
        Type listType = new TypeToken<ArrayList<Batch>>() {
        }.getType();
        List<Batch> batches = new Gson().fromJson(batchGetService.GetEntities(RequestType.GETALL_BATCH), listType);
        ObservableList<TableBatch> list = FXCollections.observableArrayList();
        if (batches.size() != 0 && batches != null)
            for (Batch batch :
                    batches) {
                TableBatch tableBatch = new TableBatch(batch.getSeriaId(), String.valueOf(batch.getDate()), batch.getFIO(), batch.getLogin());
                list.add(tableBatch);
            }
        tableview.setItems(list);

    }
}
