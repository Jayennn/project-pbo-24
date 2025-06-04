package io.github.jayennn.BlockchainVoting.session;

import io.github.jayennn.BlockchainVoting.entity.User;

public class SessionManager {
    private static SessionManager instance;
    private User user;

    private SessionManager(){

    }

    public static SessionManager getInstance(){
        if (instance == null) {
           instance = new SessionManager();
        }
        return instance;
    }

    public void setUser(User user) {
        user.setPassword(""); // Temp: not secure
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
