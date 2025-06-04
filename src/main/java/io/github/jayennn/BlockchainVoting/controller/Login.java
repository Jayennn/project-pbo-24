package io.github.jayennn.BlockchainVoting.controller;

import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.gui.GuiManager;
import io.github.jayennn.BlockchainVoting.gui.dashboardUser.DashboardUser;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.utils.UpdatableBcrypt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class Login {
    public Login(){}

    public void validate(String username,String password,GuiManager guiManager){
        EntityManager em = JpaManager.getInstance().getEM();
        em.getTransaction().begin();

        try {
            User user = em.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username",username)
                    .getSingleResult();

            UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);
            boolean result = UBcrypt.verifyHash(password,user.getPassword());

            if (result){
                SessionManager.getInstance().setUser(user);
                if (user.getRole().equals(Role.USER)){
                    guiManager.showDashboardUser();
                }
            }
        }catch (NoResultException e){
            System.out.println("no result");
        }

    }
}
