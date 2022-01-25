package Controllers.New;

import Enums.RequestType;
import Models.Entities.Batch;
import Models.Entities.User;
import Models.Entities.Worker;
import Models.TCP.Request;
import Models.TCP.Response;
import Utility.ClientSocket;
import Utility.GetService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BatchNewController implements Initializable {
    public Button buttonOk;
    public Button buttonBack;
    public ChoiceBox choiceboxWorker;
    public ChoiceBox choiceboxUser;
    public Label labelMessage;
    public DatePicker datepicker;
    public int BatchID;

    public void Ok_Pressed(ActionEvent actionEvent) throws IOException {
        try {
            Batch batch = new Batch();
            Request request;
            batch.setWorkersWorkerId(new Worker((Integer) choiceboxWorker.getSelectionModel().getSelectedItem()));
            batch.setUsersUserId(new User((Integer) choiceboxUser.getSelectionModel().getSelectedItem()));
            batch.setDate(Date.valueOf(datepicker.getValue()));
            if (ClientSocket.getInstance().getBatchId() != -1) {
                batch.setSeriaId(BatchID);
                request = new Request(RequestType.UPDATE_BATCH, new Gson().toJson(batch));
            } else request = new Request(RequestType.ADD_BATCH, new Gson().toJson(batch));
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            Response response = new Gson().fromJson(ClientSocket.getInstance().getInStream().readLine(), Response.class);
            labelMessage.setText(response.getResponseMessage());
            labelMessage.setVisible(true);
            Thread.sleep(500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Tables/BatchTable.fxml"));
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            ClientSocket.getInstance().setBatchId(-1);
        } catch (Exception e) {
            labelMessage.setText("Произошла ошибка");
            labelMessage.setVisible(true);
        }

    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/BatchTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            GetService<User> userGetService = new GetService<>(User.class);
            GetService<Worker> workerGetService = new GetService<>(Worker.class);
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> users = new Gson().fromJson(userGetService.GetEntities(RequestType.GETALL_USER), listType);
            listType = new TypeToken<ArrayList<Worker>>() {
            }.getType();
            List<Worker> workers = new Gson().fromJson(workerGetService.GetEntities(RequestType.GETALL_WORKER), listType);
            for (Worker worker :
                    workers) {
                choiceboxWorker.getItems().add(worker.getWorkerId());
            }
            for (User user :
                    users) {
                choiceboxUser.getItems().add(user.getUserId());
            }
            if (ClientSocket.getInstance().getBatchId() != -1) {
                GetService<Batch> batchGetService = new GetService<>(Batch.class);
                Batch batch = batchGetService.GetEntity(RequestType.GET_BATCH, new Batch(ClientSocket.getInstance().getBatchId()));
                BatchID = batch.getSeriaId();
                datepicker.setValue(batch.getDate().toLocalDate());
                choiceboxUser.setValue(batch.getUsersUserId().getUserId());
                choiceboxWorker.setValue(batch.getWorkersWorkerId().getWorkerId());
            }
        } catch (Exception e) {

        }
    }
}

