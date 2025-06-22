package io.github.jayennn.BlockchainVoting.view.dashboardUser.election.card;

import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CandidateCardPanel extends JPanel {
    private ElectionCardPanel electionCardPanel;
    private JPanel thisPanel;
    public CandidateCardPanel(Candidate candidate,ElectionCardPanel electionCardPanel){
        thisPanel = this;
        this.electionCardPanel = electionCardPanel;
        initializeComponents(candidate);

    }
    private void initializeComponents(Candidate candidate){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(150, 200));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);

// Placeholder for image
        JPanel imageBox = new JPanel();
        imageBox.setPreferredSize(new Dimension(150, 100));
        imageBox.setBackground(new Color(230, 230, 230)); // light gray
        add(imageBox);

// Candidate name
        JLabel nameLabel = new JLabel(candidate.getName()); // or whatever getter
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(nameLabel);

// Vision
        JLabel visionLabel = new JLabel("<html><b>Vision:</b> " + candidate.getVision() + "</html>");
        visionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        visionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(visionLabel);

// Mission
        JLabel missionLabel = new JLabel("<html><b>Mission:</b> " + candidate.getMission() + "</html>");
        missionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        missionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(missionLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                electionCardPanel.setSelectedCandidate(candidate);
                electionCardPanel.highlightSelectedCard(thisPanel);
            }
        });

    }


}
