package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class VoteId implements Serializable {

    @Column (name = "election_uuid", columnDefinition = "binary(16)")
    private UUID electionUUID;

    @Column (name = "id", length = 10)
    private String voterId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return  true;
        if (!(obj instanceof VoteId that)) return false;
        return Objects.equals(electionUUID,that.electionUUID) &&
                Objects.equals(voterId,that.voterId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(electionUUID,voterId);
    }

    public void setElectionUUID(UUID electionUUID) {
        this.electionUUID = electionUUID;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
