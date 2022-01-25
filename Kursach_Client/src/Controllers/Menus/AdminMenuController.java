package Controllers.Menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {
    public Button buttonProduce;
    public Button buttonDiagram;
    public Button buttonUser;
    public Button buttonStandart;
    public Button buttonBatch;
    public Button buttonWorker;
    public Button buttonLogout;
    public Button buttonGrade;

    public void Produce_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/ProduceTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Diagram_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Analytics/Diagram.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void User_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/UserTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Standart_Pressed(ActionEvent actionEvent)throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/StandartTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Batch_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/BatchTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Worker_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/WorkerTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Logout_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Menus/Auth.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    public void Grade_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Grade/GradeMenu.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }
}
