package io.github.jayennn.BlockchainVoting.view.dashboardUser.election.card;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.election.CastVoteHandler;
import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.entity.Vote;
import io.github.jayennn.BlockchainVoting.view.common.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ElectionCardPanel extends BasePanel implements ElectionCardView{
    private final Election election;
    private final Vote matchedVote;
    private final List<CandidateCardPanel> candidatePanels;
    private Candidate selectedCandidate;
    private CastVoteHandler castVoteHandler;

    public ElectionCardPanel(Election election, CastVoteHandler castVoteHandler, Vote matchedVote){
        this.election = election;
        this.castVoteHandler = castVoteHandler;
        this.matchedVote = matchedVote;
        candidatePanels = new ArrayList<>();
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));

        // Header section with title, date, and vote button
        JPanel headerSection = new JPanel();
        headerSection.setLayout(new BorderLayout());
        headerSection.setBackground(Color.WHITE);

        // Left side - Title and date
        JPanel leftSection = new JPanel();
        leftSection.setLayout(new BorderLayout());
        leftSection.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(election.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));

        JLabel dateLabel = new JLabel(election.getDateStart().toString());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(102, 102, 102));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        leftSection.add(titleLabel, BorderLayout.NORTH);
        leftSection.add(dateLabel, BorderLayout.CENTER);

        JPanel contentSection = new JPanel();
        contentSection.setLayout(new BorderLayout());
        contentSection.setBackground(Color.WHITE);
        contentSection.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        contentSection.setPreferredSize(new java.awt.Dimension(0, 200));

        contentSection.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        for (Candidate candidate:election.getCandidateList()){
            CandidateCardPanel candidateCardPanel = new CandidateCardPanel(candidate,this);
            candidatePanels.add(candidateCardPanel);
            contentSection.add(candidateCardPanel);
        }
        // Right side - Vote button
        JPanel rightSection = new JPanel();
        rightSection.setLayout(new BorderLayout());
        rightSection.setBackground(Color.WHITE);

        JButton voteButton = createVoteButton(election);
        rightSection.add(voteButton, BorderLayout.EAST);

        headerSection.add(leftSection, BorderLayout.CENTER);
        headerSection.add(rightSection, BorderLayout.EAST);

        add(headerSection, BorderLayout.NORTH);
        add(contentSection, BorderLayout.CENTER);

    }

    @Override
    public void setCastVoteHandler(CastVoteHandler castVoteHandler) {
        this.castVoteHandler = castVoteHandler;
    }

    private JButton createVoteButton(Election election) {
        JButton button = new JButton("Vote");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(new Color(51, 51, 51));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)));
        button.setFocusPainted(false);
        button.setPreferredSize(new java.awt.Dimension(80, 35));

        if (!election.isActive()) {
            button.setEnabled(false);
            button.setForeground(new Color(153, 153, 153));
        }
        if (matchedVote!=null){
//            button.setEnabled(false);
            button.setForeground(new Color(153, 153, 153));
            for(CandidateCardPanel panel:candidatePanels){
                System.out.println(panel.getCandidate().getUUID());
                System.out.println(matchedVote.getCandidate().getUUID());
                if (panel.getCandidate().getUUID().equals(matchedVote.getCandidate().getUUID())){
                    highlightSelectedCard(panel);
                    System.out.println("matched candidate");
                }
                panel.setActive(false);
            }
            System.out.println("panel mathced");
        }


        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(new Color(245, 245, 245));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        // Add action listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                castVoteHandler.handle(election,selectedCandidate);
            }
        });

        return button;
    }

    public void setSelectedCandidate(Candidate selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
        System.out.println("Election "+election.getTitle()+"\nCandidate "+selectedCandidate.getName());
    }

    public void highlightSelectedCard(JPanel selectedCard) {
        for (JPanel panel : candidatePanels) {
            panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }

        selectedCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
}
