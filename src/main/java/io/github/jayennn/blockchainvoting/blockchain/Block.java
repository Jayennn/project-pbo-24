package io.github.jayennn.blockchainvoting.blockchain;

import io.github.jayennn.blockchainvoting.utils.StringUtil;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block implements Serializable {
    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private Transaction data;
    private Boolean valid;

    public Block(int index, String previousHash, Transaction data) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.data = data;
        this.hash = calculateHash();
    }

    public String getHash() {
        return hash;
    }

    public String calculateHash() {
        /* Function to calculated hash */
        return StringUtil.applySha256(
                previousHash + Long.toString(timestamp) + Integer.toString(this.index) + data.getTransactionId()
        );
    }

    public int getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Transaction getData() {
        return data;
    }

}
