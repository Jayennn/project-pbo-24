package io.github.jayennn.BlockchainVoting.controller.dashboardUser.election;

import com.mysql.cj.Session;
import io.github.jayennn.BlockchainVoting.controller.dashboardUser.PostLogin;
import io.github.jayennn.BlockchainVoting.entity.*;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionsView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.card.ElectionCardView;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElectionController implements PostLogin {
    private ElectionView view;
    private ElectionsView electionsView;
    public ElectionController(ElectionView view){
        this.view = view;
        this.electionsView = view.getElectionsView();
    }

    @Override
    public void onLogin() {
        System.out.println("election init");
        view.setElection(getAllElection());
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
        voteId.setElectionUUID(election.getUuid());
        voteId.setVoterId(SessionManager.getInstance().getVoterId());

        Vote vote = new Vote(voteId,
                em.getReference(Voter.class,SessionManager.getInstance().getVoterId()
                ), em.getReference(Candidate.class,candidate.getUuid()), em.getReference(Election.class,election.getUuid()));

        em.persist(vote);
        em.getTransaction().commit();
        electionsView.refresh(getAllElection());
    }

}
