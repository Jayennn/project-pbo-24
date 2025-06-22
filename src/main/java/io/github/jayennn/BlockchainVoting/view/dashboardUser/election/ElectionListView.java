package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.election.CastVoteHandler;
import io.github.jayennn.BlockchainVoting.entity.Election;

import java.util.List;

public interface ElectionListView {
    void setCastVoteHandler(CastVoteHandler castVoteHandler);
    void refresh(List<Election> elections);
}
