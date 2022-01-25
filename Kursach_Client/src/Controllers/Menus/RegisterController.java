package Controllers.Menus;

import Enums.RequestType;
import Enums.ResponseStatus;
import Models.Entities.User;
import Models.TCP.Request;
import Models.TCP.Response;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    public PasswordField passwordfieldPassword;
    public PasswordField passwordfieldConfirmPassword;
    public Button buttonSignUp;
    public Button buttonBack;
    public TextField textfieldLogin;
    public Label labelMessage;

    public void Signup_Pressed(ActionEvent actionEvent) throws IOException {
        labelMessage.setVisible(false);
        User user = new User();
        user.setLogin(textfieldLogin.getText());
        user.setPassword(textfieldLogin.getText());
        user.setRole(1);
        if (!passwordfieldPassword.getText().equals(passwordfieldConfirmPassword.getText())) {
            labelMessage.setText("Пароли не совпадают");
            labelMessage.setVisible(true);
            return;
        }

        if (textfieldLogin.getText().equals("") || passwordfieldPassword.getText().equals("") || passwordfieldConfirmPassword.getText().equals("")) {
            labelMessage.setText("Не все поля заполнены.");
            labelMessage.setVisible(true);
            return;
        }
        Request request = new Request();
        request.setRequestMessage(new Gson().toJson(user));
        request.setRequestType(RequestType.REGISTER);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response response = new Gson().fromJson(answer, Response.class);
        if (response.getResponseStatus() == ResponseStatus.OK) {
            labelMessage.setVisible(false);
            ClientSocket.getInstance().setUser(new Gson().fromJson(response.getResponseData(), User.class));
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root;

            root = FXMLLoader.load(getClass().getResource("/Grade/GradeMenu.fxml"));

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        } else {
            labelMessage.setText("Пользователь с таким логином уже существует.");
            labelMessage.setVisible(true);
        }
    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/Auth.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }
}
