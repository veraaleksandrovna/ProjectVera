package Controllers.Tables;

import Enums.RequestType;
import Models.Entities.Worker;
import Models.TCP.Request;
import Models.TableEntities.TableWorker;
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

public class WorkerController implements Initializable {
    public Button buttonAdd;
    public Button buttonDelete;
    public Button buttonEdit;
    public Button buttonBack;
    public TableColumn<TableWorker, String> columnEmail;
    public TableColumn<TableWorker, String> columnDepartment;
    public TableColumn<TableWorker, String> columnPosition;
    public TableColumn<TableWorker, String> columnName;
    public TableColumn<TableWorker, String> columnId;
    public TableView<TableWorker> tableview;

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Edit_Pressed(ActionEvent actionEvent) throws IOException {
        TableWorker tableWorker = tableview.getSelectionModel().getSelectedItem();
        ClientSocket.getInstance().setWorkerId(tableWorker.getId());
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/WorkerNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Delete_Pressed(ActionEvent actionEvent) throws IOException {
        Request requestModel = new Request();
        TableWorker workerModel = tableview.getSelectionModel().getSelectedItem();
        requestModel.setRequestMessage(new Gson().toJson(new Worker(workerModel.getId(), workerModel.getName(), workerModel.getPosition(), workerModel.getDepartment())));
        requestModel.setRequestType(RequestType.DELETE_WORKER);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        tableview.getItems().remove(workerModel);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        ClientSocket.getInstance().getInStream().readLine();
    }

    public void Add_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/WorkerNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);

    }

    public void TableViewClicked(MouseEvent mouseEvent) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            buttonDelete.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDelete.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("Department"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        GetService<Worker> batchGetService = new GetService<Worker>(Worker.class);
        Type listType = new TypeToken<ArrayList<Worker>>() {
        }.getType();
        List<Worker> workers = new Gson().fromJson(batchGetService.GetEntities(RequestType.GETALL_WORKER), listType);
        ObservableList<TableWorker> tableWorkers = FXCollections.observableArrayList();
        if (workers.size() != 0 && workers != null)
            for (Worker worker :
                    workers) {
                TableWorker tableWorker = new TableWorker(worker.getWorkerId(), worker.getFio(), worker.getPosition(), worker.getOtdel());
                tableWorkers.add(tableWorker);
            }
        tableview.setItems(tableWorkers);

    }
}
