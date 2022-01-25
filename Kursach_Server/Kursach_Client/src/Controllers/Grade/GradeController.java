package Controllers.Grade;

import Enums.RequestType;
import Models.Entities.Grade;
import Models.Entities.Produce;
import Models.TCP.Request;
import Models.TCP.Response;
import Models.TableEntities.TableGrade;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GradeController implements Initializable {
    public Button buttonBack;
    public Label labelMessage;
    public TableColumn<TableGrade, String> columnId;
    public TableView<TableGrade> tableview;
    public TableColumn<TableGrade, String> columnName;
    public TableColumn<TableGrade, String> columnWeight;
    public TableColumn<TableGrade, String> columnStrength;
    public TableColumn<TableGrade, String> columnSize;
    public TableColumn<TableGrade, String> columnThickness;
    public TableColumn<TableGrade, String> columnResult;
    public TableColumn<TableGrade, String> columnAppearance;
    public Button buttonReport;


    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = null;
        try {
            if (ClientSocket.getInstance().getUser().getRole().equals("Admin"))
                root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu.fxml"));
            else {
                root = FXMLLoader.load(getClass().getResource("/Menus/Auth.fxml"));
            }
        } catch (NullPointerException e) {
            root = FXMLLoader.load(getClass().getResource("/Menus/Auth.fxml"));
        } finally {
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnAppearance.setCellValueFactory(new PropertyValueFactory<>("Appearance"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnResult.setCellValueFactory(new PropertyValueFactory<>("Result"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        columnStrength.setCellValueFactory(new PropertyValueFactory<>("Strength"));
        columnThickness.setCellValueFactory(new PropertyValueFactory<>("Thickness"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        GetService<Produce> produceGetService = new GetService<Produce>(Produce.class);
        List<Produce> produces = new ArrayList<Produce>();
        Type listType = new TypeToken<ArrayList<Produce>>() {
        }.getType();
        produces = new Gson().fromJson(produceGetService.GetEntities(RequestType.GETALL_PRODUCT), listType);
        ObservableList<TableGrade> list = FXCollections.observableArrayList();
        int i = 0;
        Request request = new Request();
        request.setRequestMessage("");
        request.setRequestType(RequestType.GRADE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
        String answer = null;
        try {
            answer = ClientSocket.getInstance().getInStream().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Grade> entities = new ArrayList<Grade>();
        Response response = new Gson().fromJson(answer, Response.class);
        listType = new TypeToken<ArrayList<Grade>>() {
        }.getType();
        entities = new Gson().fromJson(response.getResponseData(), listType);
        for (Grade grade :
                entities) {
            TableGrade tableGrade = new TableGrade(produces.get(i).getProductId(), produces.get(i).getPrName(), String.valueOf(grade.checkStrength()), String.valueOf(grade.checkWeight()), String.valueOf(String.valueOf(grade.checkSize())), String.valueOf(grade.checkThickness()), String.valueOf(grade.checkAppearance()), String.valueOf(grade.getResult()));
            list.add(tableGrade);
            i++;
        }
        tableview.setItems((ObservableList<TableGrade>) list);
    }

    public void Report_Pressed(ActionEvent actionEvent) throws IOException {
        GetService<Produce> produceGetService = new GetService<Produce>(Produce.class);
        List<Produce> produces = new ArrayList<Produce>();
        Type listType = new TypeToken<ArrayList<Produce>>() {
        }.getType();
        produces = new Gson().fromJson(produceGetService.GetEntities(RequestType.GETALL_PRODUCT), listType);
        ObservableList<TableGrade> list = FXCollections.observableArrayList();
        int i = 0;
        Request request = new Request();
        request.setRequestMessage("");
        request.setRequestType(RequestType.GRADE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
        String answer = null;
        try {
            answer = ClientSocket.getInstance().getInStream().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Grade> entities = new ArrayList<Grade>();
        Response response = new Gson().fromJson(answer, Response.class);
        listType = new TypeToken<ArrayList<Grade>>() {
        }.getType();
        entities = new Gson().fromJson(response.getResponseData(), listType);
        String fileName = "Report_" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss").format(LocalDateTime.now()) + ".txt";
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String Head = "ID | Название | Прочность | Вес | Размер | Толщина | Внешний вид | Прошёл оценку качества \n";
        fileOutputStream.write(Head.getBytes());
        for (Grade grade :
                entities) {
            String string = "";
            string += produces.get(i).getProductId() + " | " + produces.get(i).getBaseProductsBaseProductId().getPrName() +
                    " | " + String.valueOf(grade.checkStrength()) +
                    " | " + String.valueOf(grade.checkWeight()) +
                    " | " + String.valueOf(String.valueOf(grade.checkSize())) +
                    " | " + String.valueOf(grade.checkThickness()) +
                    " | " + String.valueOf(grade.checkAppearance()) +
                    " | " + String.valueOf(grade.getResult())+"\n";
            fileOutputStream.write(string.getBytes());
            String line = "---------------------------------------------" + "\n";
            fileOutputStream.write(line.getBytes());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Уведомление");
        alert.setContentText("Отчёт об оценке качества продукции успешно создан. Файл отчёта "+fileName);
        alert.showAndWait();
        fileOutputStream.close();
    }
}
