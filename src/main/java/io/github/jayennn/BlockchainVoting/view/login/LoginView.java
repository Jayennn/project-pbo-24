package io.github.jayennn.BlockchainVoting.view.login;

import io.github.jayennn.BlockchainVoting.controller.login.LoginHandler;

public interface LoginView {
    String getUsername();
    String getPassword();
    void setLoginHandler(LoginHandler loginHandler);

    void displayError(String message);
    void clearFields();

}
