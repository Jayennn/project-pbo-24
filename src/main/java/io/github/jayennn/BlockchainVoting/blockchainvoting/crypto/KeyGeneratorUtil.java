package io.github.jayennn.BlockchainVoting.blockchainvoting.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * Kelas utilitas untuk menghasilkan pasangan kunci (key pair) yang digunakan dalam sistem kriptografi.
 *
 * Kelas ini menyediakan metode untuk menghasilkan pasangan kunci RSA yang dapat digunakan untuk
 * tanda tangan digital dan enkripsi dalam konteks sistem voting berbasis blockchain.
 */
public class KeyGeneratorUtil {

    /**
     * Menghasilkan pasangan kunci RSA dengan ukuran 2048 bit.
     *
     * @return KeyPair pasangan kunci yang terdiri dari kunci publik dan kunci pribadi.
     * @throws Exception jika terjadi kesalahan saat menghasilkan pasangan kunci.
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            // Membuat instance KeyPairGenerator untuk algoritma RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            // Menggunakan SecureRandom untuk menghasilkan nilai acak yang aman
            SecureRandom secureRandom = new SecureRandom();
            keyGen.initialize(2048, secureRandom); // Menginisialisasi dengan ukuran kunci 2048 bit

            // Menghasilkan pasangan kunci
            KeyPair keyPair = keyGen.generateKeyPair();
            return keyPair; // Mengembalikan pasangan kunci
        } catch (Exception e) {
            throw new RuntimeException(e); // Menangani kesalahan dengan melempar RuntimeException
        }
    }
}
