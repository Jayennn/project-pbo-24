package io.github.jayennn.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class Transaction {
    @JsonProperty("voterId")
    private String voterId;

    @JsonProperty("candidateId")
    private UUID candidateId;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("signature")
    private byte[] signature;

    public Transaction(String voterId, UUID candidateId, String transactionId, byte[] signature) {
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.signature = signature;
        this.transactionId = calculateTransactionId();
    }

    private String calculateTransactionId() {
        String data = voterId + candidateId;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public byte[] getSignature() {
        return signature;
    }
}
