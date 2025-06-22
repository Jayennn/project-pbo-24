package io.github.jayennn.BlockchainVoting.view.dashboardUser.election.card;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.election.CastVoteHandler;
import io.github.jayennn.BlockchainVoting.entity.Election;

import java.util.List;

public interface ElectionCardView {
    void setCastVoteHandler(CastVoteHandler castVoteHandler);
}
