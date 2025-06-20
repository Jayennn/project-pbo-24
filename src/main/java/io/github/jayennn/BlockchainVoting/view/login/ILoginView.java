package io.github.jayennn.BlockchainVoting.view.login;

public interface ILoginView {
    void displayError(String message);
    void clearInput();

    String getUsernameInput();
    String getPasswordInput();
}
