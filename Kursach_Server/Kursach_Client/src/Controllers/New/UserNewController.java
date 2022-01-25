package Controllers.New;

import Enums.RequestType;
import Enums.Roles;
import Models.Entities.User;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserNewController implements Initializable {
    public TextField textfieldPassword;
    public TextField textfieldLogin;
    public Button buttonOk;
    public Button buttonBack;
    public ChoiceBox checkboxRole;
    public Label labelMessage;
    public int UserID;

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/UserTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Ok_Pressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        try {
            User user = new User();
            String Role = checkboxRole.getSelectionModel().getSelectedItem().toString();
            if (Role.equals(Roles.User.toString()))
                user.setRole(1);
            else
                user.setRole(0);
            user.setPassword(textfieldPassword.getText());
            user.setLogin(textfieldLogin.getText());
            Request request;
            if (ClientSocket.getInstance().getUserId() != -1) {
                user.setUserId(UserID);
                request = new Request(RequestType.UPDATE_USER, new Gson().toJson(user));
            } else
                request = new Request(RequestType.ADD_USER, new Gson().toJson(user));
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            Response response = new Gson().fromJson(ClientSocket.getInstance().getInStream().readLine(), Response.class);
            labelMessage.setText(response.getResponseMessage());
            labelMessage.setVisible(true);
            ClientSocket.getInstance().setUserId(-1);
            Thread.sleep(1500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Tables/UserTable.fxml"));
            Scene newScene = new Scene(root);
            stage.setScene(newScene);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            checkboxRole.getItems().removeAll(checkboxRole.getItems());
            checkboxRole.getItems().addAll(String.valueOf(Roles.User), String.valueOf(Roles.Admin));
            if (ClientSocket.getInstance().getUserId() != -1) {
                GetService<User> userGetService = new GetService<>(User.class);
                User user = userGetService.GetEntity(RequestType.GET_USER, new User(ClientSocket.getInstance().getUserId()));
                UserID = user.getUserId();
                textfieldLogin.setText(user.getLogin());
                textfieldPassword.setText(user.getPassword());
                checkboxRole.getSelectionModel().select(user.getRole());
            }
        } catch (Exception e) {

        }
    }
}
