package io.github.jayennn.BlockchainVoting.blockchainvoting;

import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.TransactionMap;

public class Test {
    public static void main(String[] args) {
        TransactionMap transactionMap = new TransactionMap();
        System.out.println("TransactionMap: ");
        transactionMap.getAllTransactions().forEach(System.out::println);
    }
}
