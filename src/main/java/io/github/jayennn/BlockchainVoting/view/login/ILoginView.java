package io.github.jayennn.BlockchainVoting.view.login;

import io.github.jayennn.BlockchainVoting.controller.login.LoginHandler;

public interface ILoginView {
    void displayError(String message);
    void clearFields();
    void setLoginHandler(LoginHandler loginHandler);

    String getUsername();
    String getPassword();
}
