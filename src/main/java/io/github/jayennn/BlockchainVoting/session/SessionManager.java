package io.github.jayennn.BlockchainVoting.session;

import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.view.common.GuiManager;

public class SessionManager {
    private static SessionManager instance;
    private User user;
    private GuiManager guiManager;

    private SessionManager(){

    }

    public static SessionManager getInstance(){
        if (instance == null) {
           instance = new SessionManager();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void logout(){
        if (guiManager != null){
            user = null;
            guiManager.showPanel("LoginCard");
        }

    }

    public void setGuiManager(GuiManager guiManager) {
        this.guiManager = guiManager;
    }
}
