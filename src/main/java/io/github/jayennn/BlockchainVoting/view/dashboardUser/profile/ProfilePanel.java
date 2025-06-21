package io.github.jayennn.BlockchainVoting.view.dashboardUser.profile;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProfilePanel extends JPanel implements ProfileView {
    private JPanel personalInfoCard;
    private JPanel votingHistoryCard;
    public ProfilePanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        personalInfoCard = new CardPanel("Personal Information", "Your personal details");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        add(personalInfoCard, gbc);

        votingHistoryCard = new CardPanel("Your Voting History", "A record of all elections you have participated in");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 1.0;
        add(votingHistoryCard, gbc);
    }

    @Override
    public CardView getInformationCard() {
        return (CardView) personalInfoCard;
    }

    @Override
    public CardView getVotingHistoryCard() {
        return (CardView) votingHistoryCard;
    }
}
