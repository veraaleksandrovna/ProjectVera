package Controllers.New;

import Enums.RequestType;
import Models.Entities.Batch;
import Models.Entities.Produce;
import Models.Entities.Standart;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProduceNewController implements Initializable {
    public Button buttonOk;
    public Button buttonBack;
    public Spinner spinnerStrength;
    public TextField textfieldName;
    public Spinner spinnerWeight;
    public Spinner spinnerSize;
    public Spinner spinnerThickness;
    public Spinner spinnerAppearance;
    public ChoiceBox choiceboxStandart;
    public ChoiceBox choiceboxBatch;
    public ChoiceBox choiceboxWorker;
    public Label labelMessage;
    private SpinnerValueFactory<Integer> valueAppearanceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueStrengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueSizeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueWeightFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private SpinnerValueFactory<Integer> valueThicknessFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
    private int ProduceID;

    public void Ok_Pressed(ActionEvent actionEvent) throws IOException {
        try {
            Produce produce = new Produce();
            Request request;
            produce.setSeriesWorkersWorkerId(new Worker((Integer) choiceboxWorker.getSelectionModel().getSelectedItem()));
            produce.setSeriesSeriesId(new Batch((Integer) choiceboxBatch.getSelectionModel().getSelectedItem()));
            produce.setBaseProductsBaseProductId(new Standart((Integer) choiceboxStandart.getSelectionModel().getSelectedItem()));
            produce.setAppearance((Integer) spinnerAppearance.getValue());

            produce.setPrName(textfieldName.getText());

            produce.setSize((Integer) spinnerSize.getValue());
            produce.setStrength((Integer) spinnerStrength.getValue());
            produce.setThickness((Integer) spinnerThickness.getValue());
            produce.setWeight((Integer) spinnerWeight.getValue());
            if (ClientSocket.getInstance().getProduceId() != -1){
                produce.setProductId(ProduceID);
                request = new Request(RequestType.UPDATE_PRODUCT, new Gson().toJson(produce));
            }
            else
                request = new Request(RequestType.ADD_PRODUCT, new Gson().toJson(produce));
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            Response response = new Gson().fromJson(ClientSocket.getInstance().getInStream().readLine(), Response.class);
            labelMessage.setText(response.getResponseMessage());
            labelMessage.setVisible(true);
            Thread.sleep(500);
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Tables/ProduceTable.fxml"));
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            ClientSocket.getInstance().setProduceId(-1);

        } catch (Exception e) {
            labelMessage.setText("Произошла ошибка");
            labelMessage.setVisible(true);
        }

    }

    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Tables/ProduceTable.fxml"));
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
            GetService<Batch> batchGetService = new GetService<>(Batch.class);
            GetService<Standart> standartGetService = new GetService<>(Standart.class);
            GetService<Worker> workerGetService = new GetService<>(Worker.class);
            Type listType = new TypeToken<ArrayList<Batch>>() {
            }.getType();
            List<Batch> batches = new Gson().fromJson(batchGetService.GetEntities(RequestType.GETALL_BATCH), listType);
            listType = new TypeToken<ArrayList<Standart>>() {
            }.getType();
            List<Standart> standarts = new Gson().fromJson(standartGetService.GetEntities(RequestType.GETALL_STANDART), listType);
            listType = new TypeToken<ArrayList<Worker>>() {
            }.getType();
            List<Worker> workers = new Gson().fromJson(workerGetService.GetEntities(RequestType.GETALL_WORKER), listType);
            for (Worker worker :
                    workers) {
                choiceboxWorker.getItems().add(worker.getWorkerId());
            }
            for (Batch batch :
                    batches) {
                choiceboxBatch.getItems().add(batch.getSeriaId());
            }
            for (Standart standart :
                    standarts) {
                choiceboxStandart.getItems().add(standart.getBaseProductId());
            }
            if (ClientSocket.getInstance().getProduceId() != -1) {
                GetService<Produce> produceGetService = new GetService<>(Produce.class);
                Produce produce = produceGetService.GetEntity(RequestType.GET_PRODUCT, new Produce(ClientSocket.getInstance().getProduceId()));
                ProduceID = produce.getProductId();
                textfieldName.setText(produce.getPrName());
                valueAppearanceFactory.setValue(produce.getAppearance());
                spinnerAppearance.setValueFactory(valueAppearanceFactory);
                valueSizeFactory.setValue(produce.getSize());
                spinnerSize.setValueFactory(valueSizeFactory);
                valueStrengthFactory.setValue(produce.getStrength());
                spinnerStrength.setValueFactory(valueStrengthFactory);
                valueThicknessFactory.setValue(produce.getThickness());
                spinnerThickness.setValueFactory(valueThicknessFactory);
                valueWeightFactory.setValue(produce.getWeight());
                spinnerWeight.setValueFactory(valueWeightFactory);
                choiceboxWorker.setValue(produce.getSeriesWorkersWorkerId().getWorkerId());
                choiceboxStandart.setValue(produce.getBaseProductsBaseProductId().getBaseProductId());
                choiceboxBatch.setValue(produce.getSeriesSeriesId().getSeriaId());
            }
        } catch (Exception e) {

        }
    }
}
