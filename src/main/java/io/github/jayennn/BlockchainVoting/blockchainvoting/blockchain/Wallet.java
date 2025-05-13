package io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    /**
     *
     * @param privateKey
     * @param publicKey
     * */

    public Wallet(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
