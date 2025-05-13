package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import io.github.jayennn.blockchainvoting.blockchain.KeyPairHolder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 *
 * Key generator util buat generate keypair
 * */

public class KeyGeneratorUtil {

    public static KeyPairHolder generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            SecureRandom secureRandom = new SecureRandom();
            keyGen.initialize(2048, secureRandom);

            KeyPair keyPair = keyGen.generateKeyPair();
            return new KeyPairHolder(keyPair.getPublic(), keyPair.getPrivate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
