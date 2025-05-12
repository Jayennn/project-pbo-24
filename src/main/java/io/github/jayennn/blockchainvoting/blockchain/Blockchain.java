package io.github.jayennn.blockchainvoting.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blockchain {
    @JsonProperty("chain")
    private final List<Block> chain;

    private boolean valid;

    public Blockchain() {
        this.chain = new ArrayList<>();
        createGenesisBlock();
    }

    public void createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("0", null, "genesis", null);
        Block genesis = new Block(0, "0", genesisTransaction);
        chain.add(genesis);
    }

    @JsonIgnore
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Transaction data) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(), data);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(chain);
    }

    public boolean isValid() {
        return valid;
    }
}
