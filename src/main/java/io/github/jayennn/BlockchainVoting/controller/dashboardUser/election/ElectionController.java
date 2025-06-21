package io.github.jayennn.BlockchainVoting.controller.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.PostLogin;
import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElectionController implements PostLogin {
    private ElectionView view;
    public ElectionController(ElectionView view){
        this.view = view;
    }

    @Override
    public void initiate() {
        getAllElection();
        System.out.println("election init");
    }

    public void getAllElection(){
        EntityManager em = JpaManager.getInstance().getEM();

        List<Election> elections = em.createQuery("SELECT e FROM Election e",Election.class)
                .getResultList();

        for(Election election:elections){
            System.out.println(election.getTitle());
            view.addElection(election.getTitle(),election.getDateStart().toString(),election.isActive()?"Active":"Upcoming");
        }

        ;

        view.updateElectionsPanel();
    }
}
