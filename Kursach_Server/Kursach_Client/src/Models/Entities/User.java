package Models.Entities;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String login;
    private String password;
    private Integer role;


    public User() { }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String login, String password, int role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        if(role == 0) {
            return "Admin";
        }
        return "User";
    }


    public void setRole(Integer role) {
        this.role = role;
    }
}
