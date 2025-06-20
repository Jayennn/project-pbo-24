package io.github.jayennn.BlockchainVoting.view.dashboardUser.profile;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 249, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Personal Information Card
        JPanel personalInfoCard = createCard("Personal Information", "Your personal details");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        add(personalInfoCard, gbc);

        // Voting History Card
        JPanel votingHistoryCard = createCard("Your Voting History", "A record of all elections you have participated in");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 1.0;
        add(votingHistoryCard, gbc);
    }

    private JPanel createCard(String title, String subtitle) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Header
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(102, 102, 102));

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.CENTER);

        // Content area (placeholder)
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Add some placeholder content for voting history
        if (title.equals("Your Voting History")) {
            content.setLayout(new BorderLayout());
            JLabel placeholder = new JLabel("Voting history content will be displayed here");
            placeholder.setHorizontalAlignment(SwingConstants.CENTER);
            placeholder.setFont(new Font("Arial", Font.ITALIC, 12));
            placeholder.setForeground(new Color(153, 153, 153));
            content.add(placeholder, BorderLayout.CENTER);
        }

        card.add(header, BorderLayout.NORTH);
        card.add(content, BorderLayout.CENTER);

        return card;
    }
}
