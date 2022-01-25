package Controllers.Tables;

import Enums.RequestType;
import Models.Entities.User;
import Models.TCP.Request;
import Models.TableEntities.TableUser;
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

public class UserController implements Initializable {
    public TableView<TableUser> tableview;
    public TableColumn<TableUser, String> columnId;
    public TableColumn<TableUser, String> columnLogin;
    public TableColumn<TableUser, String> columnPassword;
    public TableColumn<TableUser, String> columnRole;
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

    public void Add_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/UserNew.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);

    }

    public void Delete_Pressed(ActionEvent actionEvent) throws IOException {
        Request requestModel = new Request();
        TableUser tableUser = tableview.getSelectionModel().getSelectedItem();
        requestModel.setRequestMessage(new Gson().toJson(new User(tableUser.getId())));
        requestModel.setRequestType(RequestType.DELETE_USER);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        tableview.getItems().remove(tableUser);
        buttonEdit.setDisable(true);
        buttonDelete.setDisable(true);
        ClientSocket.getInstance().getInStream().readLine();

    }

    public void Edit_Pressed(ActionEvent actionEvent) throws IOException {
        TableUser tableUser = tableview.getSelectionModel().getSelectedItem();
        ClientSocket.getInstance().setUserId(tableUser.getId());
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/NewForms/UserNew.fxml"));
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
        columnRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("Name"));
        GetService<User> batchGetService = new GetService<User>(User.class);
        Type listType = new TypeToken<ArrayList<User>>() { }.getType();
        List<User> users = new Gson().fromJson(batchGetService.GetEntities(RequestType.GETALL_USER), listType);
        ObservableList<TableUser> list = FXCollections.observableArrayList();
        if (users.size() != 0 && users != null)
            for (User user :
                    users) {
                TableUser tableUser = new TableUser(user.getUserId(), user.getLogin(), user.getPassword(), String.valueOf(user.getRole()));
                list.add(tableUser);
            }
        tableview.setItems(list);

    }
}
