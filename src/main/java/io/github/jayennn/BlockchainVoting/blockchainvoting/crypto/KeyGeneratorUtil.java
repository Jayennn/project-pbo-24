package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 *
 * Key generator util buat generate keypair
 * */

public class KeyGeneratorUtil {

    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            SecureRandom secureRandom = new SecureRandom();
            keyGen.initialize(2048, secureRandom);

            KeyPair keyPair = keyGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
