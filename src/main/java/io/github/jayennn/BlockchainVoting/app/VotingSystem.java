package io.github.jayennn.BlockchainVoting.app;

import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Blockchain;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.KeyPairHolder;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Transaction;
import io.github.jayennn.BlockchainVoting.blockchainvoting.crypto.KeyGeneratorUtil;
import io.github.jayennn.BlockchainVoting.blockchainvoting.utils.JsonFileWriter;

import java.security.KeyPair;
import java.util.UUID;

public class VotingSystem {
   public static void main(String[] args) throws Exception {
      Blockchain blockchain = new Blockchain();
      UUID candidate1 = UUID.randomUUID();

      KeyPair voter1Key = KeyGeneratorUtil.generateKeyPair();
      KeyPair voter2Key = KeyGeneratorUtil.generateKeyPair();

      Transaction transaction1 = new Transaction("voter1", candidate1);
      transaction1.generateSignature(voter1Key.getPrivate());
      Transaction transaction2 = new Transaction("voter1", candidate1);
      transaction2.generateSignature(voter1Key.getPrivate());

      blockchain.addBlock(transaction1, voter1Key.getPublic());
      blockchain.addBlock(transaction2, voter1Key.getPublic());

      System.out.println("Is Blockchain Valid: " + blockchain.validateBlockchain());
      JsonFileWriter.JsonWritter(blockchain);
   }
}
