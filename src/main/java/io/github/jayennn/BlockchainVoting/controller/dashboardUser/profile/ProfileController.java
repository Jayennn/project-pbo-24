package io.github.jayennn.BlockchainVoting.controller.dashboardUser.profile;

import io.github.jayennn.BlockchainVoting.controller.login.LoginListener;
import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.Vote;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.CardView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;

public class ProfileController implements LoginListener {
    private final CardView personalInfoCard;
    private final CardView votingHistoryCard;

    public ProfileController(ProfileView view){
        this.personalInfoCard = view.getInformationCard();
        this.votingHistoryCard = view.getVotingHistoryCard();
    }
    @Override
    public void onLogin(){
        refresh();
    }

    public void refresh(){
        if (SessionManager.getInstance().getUser().getRole() == Role.USER) {
            votingHistoryCard.clearContent();
            personalInfoCard.clearContent();
            updateProfileInfo();
            updateVotingHistory();
        }
    }

    private void updateProfileInfo(){
        Voter voter = SessionManager.getInstance().getVoter();
        personalInfoCard.addField("Name",voter.getName());
        personalInfoCard.addField("Birthday",voter.getDateOfBirth().toString());
        personalInfoCard.addField("Gender",voter.getGender().toString());
    }

    private void updateVotingHistory(){
        if(SessionManager.getInstance().getVotes() != null){
            for (Vote vote : SessionManager.getInstance().getVotes()){
                String electionName = vote.getElection().getTitle();
                String candidateName = vote.getCandidate().getName();
                String dateTime = vote.getTimestamp().toString();
                votingHistoryCard.addVoteBox(electionName,candidateName,dateTime);
            }
        }
    }

}
