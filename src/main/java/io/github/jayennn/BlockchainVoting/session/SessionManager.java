package io.github.jayennn.BlockchainVoting.session;

import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.entity.Voter;
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

    public Voter getVoter(){
        if (getUser().getVoter() == null){
            throw new RuntimeException("Current user is not applicable to be voter");
        }
        return getUser().getVoter();
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

    public String getVoterId(){
       return getVoter().getId();
    }
}
