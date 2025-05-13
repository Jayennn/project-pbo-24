package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.LoggerUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class SignatureUtil {
    public static byte[] sign(String data, PrivateKey privateKey) {
        LoggerUtils.LogBuilder logger = new LoggerUtils.LogBuilder(LoggerUtils.LogLevel.INFO)
                .with("data", data);
        try {

            Signature signatureAlgorithm = Signature.getInstance("SHA256withRSA");
            signatureAlgorithm.initSign(privateKey);
            signatureAlgorithm.update(data.getBytes(StandardCharsets.UTF_8));

            byte[] signature = signatureAlgorithm.sign();
            logger.with("signature", signature);
            logger.log();

            return signature;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException("Error while signing the data", e);
        }
    }

    public static boolean verify(String data, byte[] signature, PublicKey publicKey) {
        try {
            Signature signatureAlgorithm = Signature.getInstance("SHA256withRSA");
            signatureAlgorithm.initVerify(publicKey);
            signatureAlgorithm.update(data.getBytes());
            return signatureAlgorithm.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException("Error while verifying the signature", e);
        }
    }
}
