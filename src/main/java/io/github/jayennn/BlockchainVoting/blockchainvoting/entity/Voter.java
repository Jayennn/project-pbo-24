package io.github.jayennn.BlockchainVoting.blockchainvoting.entity;

import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.KeyPairHolder;
import io.github.jayennn.BlockchainVoting.blockchainvoting.crypto.KeyGeneratorUtil;
import io.github.jayennn.BlockchainVoting.blockchainvoting.enums.Gender;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Base64;

public class Voter {
    private String voterId;
    private String name;
    private String studentId;
    private String dateOfBirth;
    private Gender gender;
    private KeyPair keyPair;

    public Voter(String id, String name, String studentId) throws Exception {
        this.voterId = id;
        this.name = name;
        this.studentId = studentId;
        this.dateOfBirth = LocalDate.now().toString();
        this.keyPair = KeyGeneratorUtil.generateKeyPair();
    }

    public PublicKey getVoterPublicKey() {
        return keyPair.getPublic();
    }

    public PrivateKey getVoterPrivateKey() {
        return keyPair.getPrivate();
    }

    public static void main(String[] args) throws Exception {
        Voter voter = new Voter("voter1", "jokowi", "11241033");
    }
}
