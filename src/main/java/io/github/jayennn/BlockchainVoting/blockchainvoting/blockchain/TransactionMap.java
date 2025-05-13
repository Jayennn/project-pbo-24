package io.github.jayennn.blockchainvoting.blockchain;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionMap {
    private final Map<String, List<Transaction>> transactions;

    public TransactionMap() {
        this.transactions = new HashMap<>();
    }

    public List<Transaction> getTransactions(String transactionId) {
        return transactions.get(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();

        // Iterate through each entry in the map and add all transactions to the list
        for (List<Transaction> transactionList : transactions.values()) {
            allTransactions.addAll(transactionList);
        }

        return allTransactions;
    }

    public void addTransaction(Transaction transaction) {
        String transactionId = transaction.getTransactionId();

        if(!transactions.containsKey(transactionId)) {
            transactions.put(transactionId, new ArrayList<>());
        }

        transactions.get(transactionId).add(transaction);
    }

}
