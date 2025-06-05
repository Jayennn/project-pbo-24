package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.LoggerUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * Kelas utilitas untuk menangani tanda tangan digital menggunakan algoritma SHA256 dengan RSA.
 * Kelas ini menyediakan metode untuk menandatangani data dan memverifikasi tanda tangan digital.
 */
public class SignatureUtil {

    /**
     * Menandatangani data menggunakan kunci pribadi.

     * @param data Data yang akan ditandatangani.
     * @param privateKey Kunci pribadi yang digunakan untuk menandatangani data.
     * @return Tanda tangan digital dalam bentuk byte array.
     */
    public static byte[] sign(String data, PrivateKey privateKey) {
        LoggerUtils.LogBuilder logger = LoggerUtils.createLogger(LoggerUtils.LogLevel.INFO)
                .with("message", data);
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

    /**
     * Memverifikasi tanda tangan digital dari data menggunakan kunci publik.
     *
     * @param data Data yang ditandatangani.
     * @param signature Tanda tangan digital yang akan diverifikasi.
     * @param publicKey Kunci publik yang digunakan untuk memverifikasi tanda tangan.
     * @return true jika tanda tangan valid, false jika tidak.
     */
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
