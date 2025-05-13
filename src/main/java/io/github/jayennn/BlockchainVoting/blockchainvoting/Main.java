package io.github.jayennn.BlockchainVoting.blockchainvoting;

import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Blockchain;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Transaction;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.JsonFileWriter;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.KeyPairHolder;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.TransactionMap;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.KeyPair;
import java.util.Base64;
import java.util.UUID;

import static io.github.jayennn.BlockchainVoting.blockchainvoting.crypto.KeyGeneratorUtil.generateKeyPair;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = new Blockchain();
        TransactionMap transactionMap = new TransactionMap();

        UUID candidate1 = UUID.randomUUID();
        UUID candidate2 = UUID.randomUUID();

        KeyPair voter1Key = generateKeyPair();
        KeyPair voter2Key = generateKeyPair();


        Transaction transaction1 = new Transaction("voter1", candidate1);
        transaction1.generateSignature(voter1Key.getPrivate());

        Transaction transaction2 = new Transaction("voter2", candidate2);
        transaction2.generateSignature(voter2Key.getPrivate());


        logger.info("Blockchain voting started");

        blockchain.addBlock(transaction1, voter1Key.getPublic());
        blockchain.addBlock(transaction2, voter2Key.getPublic());

        logger.info("Blockchain has been created");
        blockchain.getChain().forEach(block -> {
           logger.info(
                   "Block {}: Hash={} PrevHash={}, TxID={}",
                   block.getIndex(),
                   block.getHash(),
                   block.getPreviousHash(),
                   block.getTransactionId());
        });

        JsonFileWriter.JsonWritter(blockchain);

    }
}
