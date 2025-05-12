package io.github.jayennn.blockchainvoting.blockchain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private boolean valid;
    private Block genesisBlock;

    public Blockchain() {
        this.chain = new ArrayList<>();
        createGenesisBlock();
    }

    public void createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("0", null, "genesis", null);
        Block genesis = new Block(0, "0", genesisTransaction);
        chain.add(genesis);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Transaction data) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(),  data);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(chain);
    }
}
