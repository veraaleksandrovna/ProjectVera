package Utility;

import Enums.ResponseStatus;
import Models.Entities.*;
import Models.TCP.Request;
import Models.TCP.Response;
import Services.*;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Request request;
    private UserService userService = new UserService();
    private List<User> users;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        users = userService.findAllEntities();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();
                request = gson.fromJson(message, Request.class);
                switch (request.getRequestType()) {
                    case REGISTER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().noneMatch(x -> x.getLogin().equals(user.getLogin()))) {
                            userService.saveEntity(user);
                            userService.findAllEntities();
                            User returnUser;
                            returnUser = userService.findEntity(user.getUserId());
                            //returnUser.setBatches(null);
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(returnUser));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такой пользователь уже существует!", "");
                        }
                        break;
                    }
                    case LOGIN: {
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> x.getLogin().equals(requestUser.getLogin())) && userService.findAllEntities().stream().anyMatch(x -> x.getPassword().equals(requestUser.getPassword()))) {
                            User user = userService.findAllEntities().stream().filter(x -> x.getLogin().equals(requestUser.getLogin())).findFirst().get();
                            user = userService.findEntity(user.getUserId());
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(user));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    }
                    case GRADE: {
                        List<Grade> grades = new ArrayList<>();
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        List<Produce> produces = entityService.GetEntities();
                        for (Produce produce :
                                produces) {
                            Grade grade = new Grade();
                            grade.setAppearance(grade.checkAppearance(produce.getAppearance(), produce.getAppearance()));
                            grade.setSize(grade.checkSize(produce.getSize(), produce.getSize()));
                            grade.setStrength(grade.checkStrength(produce.getStrength(), produce.getStrength()));
                            grade.setThickness(grade.checkThickness(produce.getThickness(), produce.getThickness()));
                            grade.setWeight(grade.checkWeight(produce.getWeight(), produce.getWeight()));
                            grade.getResult();
                            grades.add(grade);
                        }
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(grades));
                        break;
                    }
                    case ADD_PRODUCT: {
                        Produce produce = gson.fromJson(request.getRequestMessage(), Produce.class);
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        boolean isSuccess = entityService.AddEntity(produce);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case UPDATE_PRODUCT: {
                        Produce produce = gson.fromJson(request.getRequestMessage(), Produce.class);
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        boolean isSuccess = entityService.UpdateEntity(produce);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GET_PRODUCT: {
                        Produce produce = gson.fromJson(request.getRequestMessage(), Produce.class);
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        produce = entityService.GetEntity(produce.getProductId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(produce));
                        break;
                    }
                    case DELETE_PRODUCT: {
                        Produce produce = gson.fromJson(request.getRequestMessage(), Produce.class);
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        boolean isSuccess = entityService.DeleteEntity(produce);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GETALL_PRODUCT: {
                        EntityService<Produce> entityService = new EntityService<>(Produce.class);
                        List<Produce> produces = entityService.GetEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(produces));
                        break;
                    }
                    case ADD_USER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        EntityService<User> entityService = new EntityService<>(User.class);
                        boolean isSuccess = entityService.AddEntity(user);
                        users = userService.findAllEntities();
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case UPDATE_USER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        EntityService<User> entityService = new EntityService<>(User.class);
                        boolean isSuccess = entityService.UpdateEntity(user);
                        users = userService.findAllEntities();
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GET_USER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        EntityService<User> entityService = new EntityService<>(User.class);
                        user = entityService.GetEntity(user.getUserId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(user));
                        break;
                    }
                    case DELETE_USER: {
                        User user = gson.fromJson(request.getRequestMessage(), User.class);
                        EntityService<User> entityService = new EntityService<>(User.class);
                        System.out.println(user);
                        boolean isSuccess = entityService.DeleteEntity(user);
                        users = userService.findAllEntities();
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GETALL_USER: {
                        EntityService<User> entityService = new EntityService<>(User.class);
                        List<User> users = entityService.GetEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(users));
                        break;
                    }
                    case ADD_WORKER: {
                        Worker worker = gson.fromJson(request.getRequestMessage(), Worker.class);
                        EntityService<Worker> entityService = new EntityService<>(Worker.class);
                        boolean isSuccess = entityService.AddEntity(worker);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case UPDATE_WORKER: {
                        Worker worker = gson.fromJson(request.getRequestMessage(), Worker.class);
                        EntityService<Worker> entityService = new EntityService<>(Worker.class);
                        boolean isSuccess = entityService.UpdateEntity(worker);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GET_WORKER: {
                        Worker worker = gson.fromJson(request.getRequestMessage(), Worker.class);
                        EntityService<Worker> entityService = new EntityService<>(Worker.class);
                        worker = entityService.GetEntity(worker.getWorkerId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(worker));
                        break;
                    }
                    case DELETE_WORKER: {
                        Worker worker = gson.fromJson(request.getRequestMessage(), Worker.class);
                        EntityService<Worker> entityService = new EntityService<>(Worker.class);
                        boolean isSuccess = entityService.DeleteEntity(worker);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GETALL_WORKER: {
                        EntityService<Worker> entityService = new EntityService<>(Worker.class);
                        List<Worker> workers = entityService.GetEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(workers));
                        break;
                    }
                    case ADD_BATCH: {
                        Batch batch = gson.fromJson(request.getRequestMessage(), Batch.class);
                        EntityService<Batch> entityService = new EntityService<>(Batch.class);
                        boolean isSuccess = entityService.AddEntity(batch);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case UPDATE_BATCH: {
                        Batch batch = gson.fromJson(request.getRequestMessage(), Batch.class);
                        EntityService<Batch> entityService = new EntityService<>(Batch.class);
                        boolean isSuccess = entityService.UpdateEntity(batch);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GET_BATCH: {
                        Batch batch = gson.fromJson(request.getRequestMessage(), Batch.class);
                        EntityService<Batch> entityService = new EntityService<>(Batch.class);
                        batch = entityService.GetEntity(batch.getSeriaId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(batch));
                        break;
                    }
                    case DELETE_BATCH: {
                        Batch batch = gson.fromJson(request.getRequestMessage(), Batch.class);
                        EntityService<Batch> entityService = new EntityService<>(Batch.class);
                        boolean isSuccess = entityService.DeleteEntity(batch);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GETALL_BATCH: {
                        EntityService<Batch> entityService = new EntityService<>(Batch.class);
                        List<Batch> batches = entityService.GetEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(batches));
                        break;
                    }
                    case ADD_STANDART: {
                        Standart standart = gson.fromJson(request.getRequestMessage(), Standart.class);
                        EntityService<Standart> entityService = new EntityService<>(Standart.class);
                        boolean isSuccess = entityService.AddEntity(standart);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case UPDATE_STANDART: {
                        Standart standart = gson.fromJson(request.getRequestMessage(), Standart.class);
                        EntityService<Standart> entityService = new EntityService<>(Standart.class);
                        boolean isSuccess = entityService.UpdateEntity(standart);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GET_STANDART: {
                        Standart standart = gson.fromJson(request.getRequestMessage(), Standart.class);
                        EntityService<Standart> entityService = new EntityService<>(Standart.class);
                        standart = entityService.GetEntity(standart.getBaseProductId());
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(standart));
                        break;
                    }
                    case DELETE_STANDART: {
                        Standart standart = gson.fromJson(request.getRequestMessage(), Standart.class);
                        EntityService<Standart> entityService = new EntityService<>(Standart.class);
                        boolean isSuccess = entityService.DeleteEntity(standart);
                        if (isSuccess)
                            response = new Response(ResponseStatus.OK, "Готово!", "");
                        else
                            response = new Response(ResponseStatus.ERROR, "Ошибка!", "");
                        break;
                    }
                    case GETALL_STANDART: {
                        EntityService<Standart> entityService = new EntityService<>(Standart.class);
                        List<Standart> standarts = entityService.GetEntities();
                        response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(standarts));
                        break;
                    }
                }
                out.println(gson.toJson(response));
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + clientSocket.getInetAddress()+":"+clientSocket.getPort() + " закрыл соединение.");
            try {

                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
