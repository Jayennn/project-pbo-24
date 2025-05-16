package io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Blockchain {
    @JsonProperty("chain")
    private final List<Block> chain;

//    private boolean valid;

    public Blockchain() {
        this.chain = new ArrayList<>();
        createGenesisBlock();
    }

    public void createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("genesis", UUID.randomUUID());
        Block genesis = new Block(0, "0", genesisTransaction);
        chain.add(genesis);
    }

    @JsonIgnore
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Transaction transaction, PublicKey publicKey) {
        if(!transaction.verifySignature(publicKey)) {
            System.out.println("Invalid signature for transaction: " + transaction.getTransactionId());
        }

        String previousHash = chain.isEmpty() ? "0" : getLatestBlock().getHash();

        Block newBlock = new Block(chain.size(), previousHash, transaction);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(chain);
    }

//    public boolean isValid() {
//        return valid;
//    }
}
