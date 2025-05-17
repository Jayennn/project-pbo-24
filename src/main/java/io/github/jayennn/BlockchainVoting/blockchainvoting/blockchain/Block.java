package io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.StringUtil;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;

public class Block implements Serializable {
    @JsonProperty("index")
    private final int index;

    @JsonProperty("timestamp")
    private final long timestamp;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("previousHash")
    private String previousHash;

    @JsonProperty("transaction")
    private Transaction transaction;
    @JsonProperty("nonce")
    private int nonce;

    private Boolean valid;

    public Block(int index, String previousHash, Transaction transaction) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.transaction = transaction;
        this.hash = calculateHash();
        this.nonce = 0;
    }

    public String getHash() {
        return hash;
    }

    public String calculateHash() {
        /* Function to calculated hash */
        return StringUtil.applySha256(previousHash + Long.toString(timestamp) + Integer.toString(this.index)  + this.nonce + transaction.getTransactionId());
    }

    public boolean validateBlock(Block previousBlock) {
        if(!previousHash.equals(previousBlock.getHash())) {
            return false;
        }

        String calculatedHash = calculateHash();
        return hash.equals(calculatedHash);
    }

    public int getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getTransactionId() {
        return transaction.getTransactionId();
    }

}
