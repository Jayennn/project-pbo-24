package io.github.jayennn.blockchainvoting;

import io.github.jayennn.blockchainvoting.blockchain.Blockchain;
import io.github.jayennn.blockchainvoting.blockchain.Transaction;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        UUID candidate1 = UUID.randomUUID();
        UUID candidate2 = UUID.randomUUID();

        blockchain.addBlock(new Transaction("voter1", candidate1, null, null));
        blockchain.addBlock(new Transaction("voter2", candidate2, null, null));
        blockchain.addBlock(new Transaction("voter3", candidate1, null, null));

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

    }
}
