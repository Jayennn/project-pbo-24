package io.github.jayennn.BlockchainVoting.blockchainvoting;

import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Blockchain;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Transaction;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.JsonFileWriter;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

import static io.github.jayennn.BlockchainVoting.blockchainvoting.crypto.KeyGeneratorUtil.generateKeyPair;

public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = new Blockchain();

        UUID candidate1 = UUID.randomUUID();
        UUID candidate2 = UUID.randomUUID();

        KeyPair keyPairGenerator = generateKeyPair();
        PrivateKey privateKey = keyPairGenerator.getPrivate();
        PublicKey publicKey = keyPairGenerator.getPublic();


        Transaction transaction1 = new Transaction("voter1", candidate1);
        transaction1.generateSignature(privateKey);  // Menandatangani transaksi dengan private key

        Transaction transaction2 = new Transaction("voter2", candidate2);
        transaction2.generateSignature(privateKey);

        Transaction transaction3 = new Transaction("voter3", candidate1);
        transaction3.generateSignature(privateKey);


        blockchain.addBlock(transaction1, publicKey);
        blockchain.addBlock(transaction2, publicKey);
        blockchain.addBlock(transaction3, publicKey);

        System.out.println("\nBlockchain contents:");

        blockchain.getChain().forEach(block -> {
            System.out.printf(
                    "Block %d: %s -> %s (Tx: %s)%n",
                    block.getIndex(),
                    block.getPreviousHash(),
                    block.getHash(),
                    block.getData().getTransactionId()
            );
        });

        JsonFileWriter.JsonWritter(blockchain);

    }
}
