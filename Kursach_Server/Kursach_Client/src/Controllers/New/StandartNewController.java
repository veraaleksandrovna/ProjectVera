package Controllers.New;

import Enums.RequestType;
import Models.Entities.Standart;
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

public class StandartNewController implements Initializable {
    public Button buttonOk;
    public javafx.scene.control.Button buttonBack;
    public Spinner spinnerStrength;
    public TextField textfieldName;
    public Spinner spinnerWeight;
    public Spinner spinnerSize;
    public Spinner spinnerThickness;
    public Spinner spinnerAppearance;
    public Label labelMessage;
    private SpinnerValueFactory<Integer> valueAppearanceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueStrengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueSizeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueWeightFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueThicknessFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private int StandartID;

    public void Ok_Pressed(ActionEvent actionEvent) throws IOException {
        try {
            Request request;
            Standart standart = new Standart();
            standart.setAppearance((Integer) spinnerAppearance.getValue());
            standart.setPrName(textfieldName.getText());
            standart.setSize((Integer) spinnerSize.getValue());
            standart.setStrength((Integer) spinnerStrength.getValue());
            standart.setThickness((Integer) spinnerThickness.getValue());
            standart.setWeight((Integer) spinnerWeight.getValue());
            if (ClientSocket.getInstance().getStandartId() != -1) {
                standart.setBaseProductId(StandartID);
                request = new Request(RequestType.UPDATE_STANDART, new Gson().toJson(standart));
            } else request = new Request(RequestType.ADD_STANDART, new Gson().toJson(standart));

            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            Response response = new Gson().fromJson(ClientSocket.getInstance().getInStream().readLine(), Response.class);
            labelMessage.setText(response.getResponseMessage());
            labelMessage.setVisible(true);
            Thread.sleep(500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Tables/StandartTable.fxml"));
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            ClientSocket.getInstance().setStandartId(-1);
        } catch (Exception e) {
            labelMessage.setText("Произошла ошибка");
            labelMessage.setVisible(true);

        }

    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/StandartTable.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            spinnerAppearance.setValueFactory(valueAppearanceFactory);
            spinnerSize.setValueFactory(valueSizeFactory);
            spinnerStrength.setValueFactory(valueStrengthFactory);
            spinnerThickness.setValueFactory(valueThicknessFactory);
            spinnerWeight.setValueFactory(valueWeightFactory);
            if (ClientSocket.getInstance().getStandartId() != -1) {
                GetService<Standart> standartGetService = new GetService<>(Standart.class);
                Standart standart = standartGetService.GetEntity(RequestType.GET_STANDART, new Standart(ClientSocket.getInstance().getStandartId()));
                StandartID = standart.getBaseProductId();
                valueAppearanceFactory.setValue(standart.getAppearance());
                spinnerAppearance.setValueFactory(valueAppearanceFactory);
                valueSizeFactory.setValue(standart.getSize());
                spinnerSize.setValueFactory(valueSizeFactory);
                valueStrengthFactory.setValue(standart.getStrength());
                spinnerStrength.setValueFactory(valueStrengthFactory);
                valueThicknessFactory.setValue(standart.getThickness());
                spinnerThickness.setValueFactory(valueThicknessFactory);
                valueWeightFactory.setValue(standart.getWeight());
                spinnerWeight.setValueFactory(valueWeightFactory);
                textfieldName.setText(standart.getPrName());
            }
        } catch (Exception e) {

        }
    }
}
