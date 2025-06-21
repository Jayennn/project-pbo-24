package io.github.jayennn.BlockchainVoting.controller.dashboardUser.profile;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.PostLogin;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.entity.Vote;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.CardView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.List;

public class ProfileController implements PostLogin {
    private ProfileView view;
    private CardView personalInfoCard;
    private CardView votingHistoryCard;


    public ProfileController(ProfileView view){
       this.view = view;
       this.personalInfoCard = view.getInformationCard();
       this.votingHistoryCard = view.getVotingHistoryCard();
    }
    @Override
    public void initiate(){

        votingHistoryCard.clearContent();
        personalInfoCard.clearContent();
        updateProfileInfo();
        updateVotingHistory();
    }

    private void updateProfileInfo(){
        User user = SessionManager.getInstance().getUser();
        Voter voter = user.getVoter();
        personalInfoCard.addField("Name",voter.getName());
        personalInfoCard.addField("Birthday",voter.getDateOfBirth().toString());
        personalInfoCard.addField("Gender",voter.getGender().toString());
    }

    private void updateVotingHistory(){
        EntityManager em = JpaManager.getInstance().getEM();

        List<Vote> votes = em.createQuery("SELECT v FROM Vote v WHERE v.voter = :id",Vote.class)
                .setParameter("id",SessionManager.getInstance().getUser().getVoter())
                .getResultList();

        for (Vote vote : votes){
            String electionName = vote.getElection().getTitle();
            String candidateName = vote.getCandidate().getName();
            String dateTime = vote.getTimestamp().toString();
            votingHistoryCard.addVoteBox(electionName,candidateName,dateTime);
        }
    }

}
