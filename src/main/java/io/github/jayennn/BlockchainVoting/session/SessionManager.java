package io.github.jayennn.BlockchainVoting.session;

import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.entity.Vote;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.view.common.GuiManager;
import jakarta.persistence.EntityManager;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;

import java.util.List;

public class SessionManager {
    private static SessionManager instance;
    private User user;
    private List<Vote> votes;
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

    public List<Vote> getVotes() {
        if (user.getRole() != Role.USER){
            return null;
        }
        updateVotes();
        return votes;
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

    private void updateVotes(){
        EntityManager em = JpaManager.getInstance().getEM();
        votes = em.createQuery("SELECT v FROM Vote v WHERE v.voter = :id",Vote.class)
                .setParameter("id",getVoter())
                .getResultList();
        System.out.println(votes.size());
    }
}
