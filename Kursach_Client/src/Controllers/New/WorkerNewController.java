package Controllers.New;

import Enums.RequestType;
import Models.Entities.Worker;
import Models.TCP.Request;
import Models.TCP.Response;
import Utility.ClientSocket;
import Utility.GetService;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkerNewController implements Initializable {
    public Button buttonBack;
    public Button buttonOk;
    public TextField textfieldDepartment;
    public TextField textfieldPosition;
    public TextField textfieldName;
    public Label labelMessage;
    public int WorkerID;

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/WorkerTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Ok_Pressed(ActionEvent actionEvent) throws IOException {
        try {
            Worker worker = new Worker();
            worker.setFio(textfieldName.getText());
            worker.setPosition(textfieldPosition.getText());
            worker.setOtdel(textfieldDepartment.getText());
            Request request;
            if (ClientSocket.getInstance().getWorkerId() != -1) {
                worker.setWorkerId(WorkerID);
                request = new Request(RequestType.UPDATE_WORKER, new Gson().toJson(worker));
            }
            else
            request = new Request(RequestType.ADD_WORKER, new Gson().toJson(worker));
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            Response response = new Gson().fromJson(ClientSocket.getInstance().getInStream().readLine(), Response.class);
            labelMessage.setText(response.getResponseMessage());
            labelMessage.setVisible(true);
            Thread.sleep(500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Tables/WorkerTable.fxml"));
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            ClientSocket.getInstance().setWorkerId(-1);
        } catch (Exception e) {
            labelMessage.setText("Произошла ошибка");
            labelMessage.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (ClientSocket.getInstance().getWorkerId() != -1) {
                GetService<Worker> workerGetService = new GetService<>(Worker.class);
                Worker worker = workerGetService.GetEntity(RequestType.GET_WORKER, new Worker(ClientSocket.getInstance().getWorkerId()));
                WorkerID = worker.getWorkerId();
                textfieldDepartment.setText(worker.getOtdel());
                textfieldName.setText(worker.getFio());
                textfieldPosition.setText(worker.getPosition());
            }
        } catch (Exception e) {

        }
    }
}
