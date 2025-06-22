package io.github.jayennn.BlockchainVoting.controller.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.DashboardUserController;
import io.github.jayennn.BlockchainVoting.controller.login.LoginListener;
import io.github.jayennn.BlockchainVoting.entity.*;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionListView;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElectionController implements LoginListener {
    private final ElectionListView electionsView;
    private final DashboardUserController dashboardUserController;
    public ElectionController(ElectionView view, DashboardUserController dashboardUserController){
        this.electionsView = view.getElectionsView();
        this.dashboardUserController = dashboardUserController;
    }

    @Override
    public void onLogin() {
        System.out.println("election init");
        electionsView.setCastVoteHandler(this::castVote);
        electionsView.refresh(getAllElection());
    }

    public List<Election> getAllElection(){
        EntityManager em = JpaManager.getInstance().getEM();

        return em.createQuery("SELECT e FROM Election e",Election.class)
                .getResultList();
    }

    public void castVote(Election election, Candidate candidate){
        EntityManager em = JpaManager.getInstance().getEM();
        em.getTransaction().begin();

        VoteId voteId = new VoteId();
        voteId.setElectionUUID(election.getUUID());
        voteId.setVoterId(SessionManager.getInstance().getVoterId());

        Vote vote = new Vote(voteId,
                em.getReference(Voter.class,SessionManager.getInstance().getVoterId()
                ), em.getReference(Candidate.class,candidate.getUUID()), em.getReference(Election.class,election.getUUID()));

        em.persist(vote);
        em.getTransaction().commit();
        electionsView.refresh(getAllElection());

        dashboardUserController.getProfileController().refresh();
    }

}
