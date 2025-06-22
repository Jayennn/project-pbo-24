package io.github.jayennn.BlockchainVoting.controller.login;

import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.utils.UpdatableBcrypt;
import io.github.jayennn.BlockchainVoting.view.common.Navigator;
import io.github.jayennn.BlockchainVoting.view.login.LoginView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginController {
  private final LoginView view;
  private final Navigator navigator;
  private final List<LoginListener> loginListeners = new ArrayList<>();

  public LoginController(LoginView view, Navigator navigator) {
    this.view = view;
    this.navigator = navigator;

    this.view.setLoginHandler(this::authenticate);
  }

  public void addLoginListener(LoginListener loginListener){
    loginListeners.add(loginListener);
  }

  public void addLoginListener(LoginListener... loginListeners){
      Collections.addAll(this.loginListeners, loginListeners);
  }

  public void authenticate(String username,String password) {
    EntityManager em = JpaManager.getInstance().getEM();
    em.getTransaction().begin();

    try {
      User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
          .setParameter("username", username)
          .getSingleResult();

      UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);
      boolean result = UBcrypt.verifyHash(password, user.getPassword());

      if (SessionManager.getInstance().getUser() == null && result) {
        SessionManager.getInstance().setUser(user);
        if (user.getRole().equals(Role.USER)) {
          navigator.showPanel("DashboardUserCard");
        } else {
          navigator.showPanel("DashboardAdminCard");
        }
        SessionManager.getInstance().setUser(user);
        notifyLoginListener();
      }else{
        throw new NoResultException();
      }
    } catch (NoResultException e) {
      view.displayError("Invalid username or password");
    }
    view.clearFields();
  }

  private void notifyLoginListener(){
    for (LoginListener controller : loginListeners ){
      controller.onLogin();
    }
  }
}
