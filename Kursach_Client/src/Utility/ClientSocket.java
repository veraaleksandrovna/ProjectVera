package Utility;

import Models.Entities.User;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    private static final ClientSocket SINGLE_INSTANCE = new ClientSocket();

    private ClientSocket() {
        try {
            socket = new Socket("localhost", 5555);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
        }
    }

    private int UserId = -1, BatchId = -1, ProduceId = -1, WorkerId = -1, StandartId = -1;

    public static ClientSocket getInstance() {
        return SINGLE_INSTANCE;
    }

    private User user;
    private static Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Socket getSocket() {
        return socket;
    }


    public BufferedReader getInStream() {
        return in;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getBatchId() {
        return BatchId;
    }

    public void setBatchId(int batchId) {
        BatchId = batchId;
    }

    public int getProduceId() {
        return ProduceId;
    }

    public void setProduceId(int produceId) {
        ProduceId = produceId;
    }

    public int getWorkerId() {
        return WorkerId;
    }

    public void setWorkerId(int workerId) {
        WorkerId = workerId;
    }

    public int getStandartId() {
        return StandartId;
    }

    public void setStandartId(int standartId) {
        StandartId = standartId;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
