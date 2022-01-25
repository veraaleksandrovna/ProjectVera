package Utility;

import Enums.RequestType;
import Models.Entities.*;
import Models.TCP.Request;
import Models.TCP.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetService<T> {
    final Class<T> ClassType;

    public GetService(Class<T> classType) {
        ClassType = classType;
    }

    public String GetEntities(RequestType requestType) {
        Request request = new Request();
        request.setRequestMessage("");
        request.setRequestType(requestType);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
        String answer = null;
        try {
            answer = ClientSocket.getInstance().getInStream().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new Gson().fromJson(answer, Response.class);
        return response.getResponseData();
    }

    public T GetEntity(RequestType requestType, T entity) throws IOException {
        Request request = new Request();

        request.setRequestMessage(new Gson().toJson(entity));
        request.setRequestType(requestType);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
        String answer;
        answer = ClientSocket.getInstance().getInStream().readLine();
        Response response = new Gson().fromJson(answer, Response.class);
        return entity = new Gson().fromJson(response.getResponseData(), ClassType);
    }
}
