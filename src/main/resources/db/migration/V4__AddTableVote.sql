CREATE TABLE votes (
    election_id BINARY(16) NOT NULL,
    candidate_id BINARY(16) NOT NULL,
    voter_id VARCHAR(10) NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (election_id, voter_id),
    FOREIGN KEY (election_id) REFERENCES elections(uuid),
    FOREIGN KEY (candidate_id) REFERENCES candidates(uuid),
    FOREIGN KEY (voter_id) REFERENCES voters(id)
);