package io.github.jayennn.blockchainvoting.blockchain;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * Class ini bertujuan untuk menyimpan keypair public dan private
 * */
public class KeyPairHolder {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public KeyPairHolder(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
