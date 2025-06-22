package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.entity.Election;

import java.util.List;

public interface ElectionView {
    void addElection(Election election);

    void setElection(List<Election> elections);
    void updateElectionsPanel();
    ElectionListView getElectionsView();
}
