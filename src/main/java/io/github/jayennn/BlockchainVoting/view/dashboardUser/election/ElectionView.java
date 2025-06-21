package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

public interface ElectionView {
    void addElection(String title,String data,String status);

    void updateElectionsPanel();
}
