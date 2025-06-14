ALTER TABLE `votes` CHANGE `election_id` `election_uuid` BINARY(16) NOT NULL;
ALTER TABLE `votes` CHANGE `candidate_id` `candidate_uuid` BINARY(16) NOT NULL;