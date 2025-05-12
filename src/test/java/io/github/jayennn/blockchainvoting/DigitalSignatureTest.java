package io.github.jayennn.blockchainvoting;

import io.github.jayennn.blockchainvoting.blockchain.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

public class DigitalSignatureTest {
    @Test
    public void digitalSignatureTest() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        UUID candidate1 = UUID.randomUUID();
        Transaction transaction = new Transaction("voter1", candidate1);

        transaction.generateSignature(privateKey);

        boolean isVerified = transaction.verifySignature(publicKey);
        System.out.println(isVerified);
        Assertions.assertTrue(isVerified, "The Signature should be verified");
    }

    @Test
    public void testInvalidSignature() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        UUID candidate1 = UUID.randomUUID();
        Transaction transaction = new Transaction("voter1", candidate1);

        transaction.generateSignature(privateKey);

        KeyPair keyPair2 = keyPairGenerator.generateKeyPair();
        PublicKey publicKey2 = keyPair2.getPublic();

        boolean isVerified = transaction.verifySignature(publicKey2);
        System.out.println(isVerified);
        Assertions.assertFalse(isVerified, "The Signature should be invalid");
    }
}
