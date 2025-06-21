package io.github.jayennn.BlockchainVoting.view.dashboardUser.profile;

public interface CardView {
    void addField(String label, String value);
    void addVoteBox(String electionName,String candidateName,String dateTime);
    void clearContent();
}
