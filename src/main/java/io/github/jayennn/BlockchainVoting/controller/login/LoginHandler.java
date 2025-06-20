package io.github.jayennn.BlockchainVoting.controller.login;

@FunctionalInterface
public interface LoginHandler {
    void handle(String username,String password);
}
