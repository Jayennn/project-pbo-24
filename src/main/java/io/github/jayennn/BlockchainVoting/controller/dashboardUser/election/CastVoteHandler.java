package io.github.jayennn.BlockchainVoting.controller.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.entity.Election;

@FunctionalInterface
public interface CastVoteHandler {
    public void handle(Election election, Candidate candidate);
}
