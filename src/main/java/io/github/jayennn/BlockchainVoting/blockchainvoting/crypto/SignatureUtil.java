package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import java.security.*;

public class SignatureUtil {
    public static byte[] sign(String data, PrivateKey privateKey) {
        try {
            Signature signatureAlgorithm = Signature.getInstance("SHA256withRSA");
            signatureAlgorithm.initSign(privateKey);
            signatureAlgorithm.update(data.getBytes());
            return signatureAlgorithm.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(String data, byte[] signature, PublicKey publicKey) {
        try {
            Signature signatureAlgorithm = Signature.getInstance("SHA256withRSA");
            signatureAlgorithm.initVerify(publicKey);
            signatureAlgorithm.update(data.getBytes());
            return signatureAlgorithm.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }
}
