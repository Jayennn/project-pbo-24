package io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Blockchain {
    private static final Logger log = LogManager.getLogger(Blockchain.class);
    @JsonProperty("chain")
    private final List<Block> chain;
    private final boolean valid;


    public Blockchain() {
        this.chain = new ArrayList<>();
        this.valid = false;
        createGenesisBlock();
    }

    public void createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("genesis", UUID.randomUUID());
        Block genesis = new Block(0, "0", genesisTransaction);
        chain.add(genesis);
    }

    public boolean validateBlockchain() {

        for(int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if(!currentBlock.validateBlock(previousBlock)) {
                return false;
            }

//            if(!currentBlock.getTransaction().verifySignature(currentBlock.getTransaction().getPublicKey()));
        }
        return true;
    }

    @JsonIgnore
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Transaction transaction, PublicKey publicKey) {
        if(!transaction.verifySignature(publicKey)) {
            log.warn("Invalid signature for transaction: {}", transaction.getTransactionId());
            return;
        }

        String previousHash = chain.isEmpty() ? "0" : getLatestBlock().getHash();
        Block newBlock = new Block(chain.size(), previousHash, transaction);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(chain);
    }

}
