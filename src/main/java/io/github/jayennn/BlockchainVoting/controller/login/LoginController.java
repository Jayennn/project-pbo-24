package io.github.jayennn.BlockchainVoting.controller.login;

import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.view.common.GuiManager;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.utils.UpdatableBcrypt;
import io.github.jayennn.BlockchainVoting.view.common.Navigator;
import io.github.jayennn.BlockchainVoting.view.login.ILoginView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class LoginController {
    private ILoginView view;
    private Navigator navigator;
    public LoginController(ILoginView view,Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public boolean validate(){
        EntityManager em = JpaManager.getInstance().getEM();
        em.getTransaction().begin();

        try {
            User user = em.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username",view.getUsernameInput())
                    .getSingleResult();

            UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);
            boolean result = UBcrypt.verifyHash(view.getPasswordInput(),user.getPassword());

            if (result){
                SessionManager.getInstance().setUser(user);
                if (user.getRole().equals(Role.USER)){
                    navigator.showPanel("DashboardUser");
                }
            }
        }catch (NoResultException e){
            view.displayError("Invalid username or password");
        }
        return  true;
    }
}
