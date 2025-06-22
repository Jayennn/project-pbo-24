package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.election.CastVoteHandler;
import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.entity.Vote;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.view.common.BasePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.card.ElectionCardPanel;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class ElectionListPanel extends BasePanel implements ElectionListView {
    GridBagConstraints gbc;
    CastVoteHandler castVoteHandler;

    public ElectionListPanel() {
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
    }

    @Override
    public void setCastVoteHandler(CastVoteHandler castVoteHandler) {
        this.castVoteHandler = castVoteHandler;
    }

    @Override
    public void refresh(List<Election> elections){
        reset();
        if (SessionManager.getInstance().getVotes()!=null){
            for (int i = 0; i < elections.size(); i++){
                Vote matchedVote = null;
                for (Vote vote: SessionManager.getInstance().getVotes()){
                    System.out.println(vote.getElection().toString());
                    System.out.println(elections.get(i).toString());
                    if(vote.getElection().getUUID().equals(elections.get(i).getUUID())){
                        matchedVote = vote;
                        break;
                    }
                }
                JPanel electionCard = new ElectionCardPanel(elections.get(i),castVoteHandler,matchedVote);
                System.out.println("matched ="+matchedVote);
                gbc.gridy = i;
                add(electionCard, gbc);
            }
        }

        gbc.gridy = elections.size();
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacer = new JPanel();
        spacer.setBackground(new Color(247, 249, 250));
        add(spacer, gbc);
    }

    public void reset(){
        removeAll();
        revalidate();
        repaint();
    }
}
