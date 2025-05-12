package io.github.jayennn.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jayennn.blockchainvoting.crypto.SignatureUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
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

    public Transaction(String voterId, UUID candidateId) {
        this.voterId = voterId;
        this.candidateId = candidateId;
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

    public void generateSignature(PrivateKey privateKey) {
        String data = voterId + candidateId + transactionId;
        this.signature = SignatureUtil.sign(data, privateKey);
    }

    public boolean verifySignature(PublicKey publicKey) {
        String data = voterId + candidateId+ transactionId;
        return SignatureUtil.verify(data, signature, publicKey);
    }

    public String getVoterId() {
        return voterId;
    }

    public String getCandidateId() {
        return candidateId.toString();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public byte[] getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "Transaction { " +
                "voterId='" + voterId + '\'' +
                ", candidateId=" + candidateId +
                ", transactionId='" + transactionId + '\'' +
                ", signature=" + (signature != null ? "Exists" : "None") +
                ", }";
    }
}
