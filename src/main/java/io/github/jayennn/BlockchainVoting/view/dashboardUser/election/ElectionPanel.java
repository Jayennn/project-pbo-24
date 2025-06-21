package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.entity.Candidate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ElectionPanel extends JPanel implements ElectionView{

  private List<StaticElection> elections;
  private JPanel electionsPanel;

  public ElectionPanel() {
    elections = new ArrayList<>();
  }

  private void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(new Color(248, 249, 250));
    setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    // Header
    JPanel headerPanel = createHeaderPanel();
    add(headerPanel, BorderLayout.NORTH);

    // Elections list
    electionsPanel = createElectionsPanel();
    JScrollPane scrollPane = new JScrollPane(electionsPanel);
    scrollPane.setBorder(null);
    scrollPane.setBackground(new Color(248, 249, 250));
    scrollPane.getViewport().setBackground(new Color(248, 249, 250));
    add(scrollPane, BorderLayout.CENTER);
  }

  private JPanel createHeaderPanel() {
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new BorderLayout());
    headerPanel.setBackground(new Color(248, 249, 250));
    headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    JLabel titleLabel = new JLabel("Election");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(new Color(51, 51, 51));

    JLabel subtitleLabel = new JLabel("All ongoing elections");
    subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    subtitleLabel.setForeground(new Color(102, 102, 102));

    JPanel titleContainer = new JPanel();
    titleContainer.setLayout(new BorderLayout());
    titleContainer.setBackground(new Color(248, 249, 250));
    titleContainer.add(titleLabel, BorderLayout.NORTH);
    titleContainer.add(subtitleLabel, BorderLayout.CENTER);

    headerPanel.add(titleContainer, BorderLayout.WEST);

    return headerPanel;
  }


  private JPanel createElectionsPanel() {
    JPanel electionsPanel = new JPanel();
    electionsPanel.setLayout(new GridBagLayout());
    electionsPanel.setBackground(new Color(248, 249, 250));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 0, 15, 0);
    gbc.weightx = 1.0;
    gbc.gridx = 0;

    // Add election cards
    for (int i = 0; i < elections.size(); i++) {
      StaticElection election = elections.get(i);
      JPanel electionCard = createElectionCard(election);
      gbc.gridy = i;
      electionsPanel.add(electionCard, gbc);
    }

    // Add empty space at the bottom
    gbc.gridy = elections.size();
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    JPanel spacer = new JPanel();
    spacer.setBackground(new Color(248, 249, 250));
    electionsPanel.add(spacer, gbc);

    return electionsPanel;
  }

  @Override
  public void addElection(String title, String date,String status){
    elections.add(new StaticElection(title,date,status));
  }

  @Override
  public void updateElectionsPanel() {
    initializeComponents();

    System.out.println("total election = "+elections.size());
  }

  private JPanel createElectionCard(StaticElection election) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
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

    JLabel dateLabel = new JLabel(election.getDate());
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    dateLabel.setForeground(new Color(102, 102, 102));
    dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

    leftSection.add(titleLabel, BorderLayout.NORTH);
    leftSection.add(dateLabel, BorderLayout.CENTER);

    // Right side - Vote button
    JPanel rightSection = new JPanel();
    rightSection.setLayout(new BorderLayout());
    rightSection.setBackground(Color.WHITE);

    JButton voteButton = createVoteButton(election);
    rightSection.add(voteButton, BorderLayout.EAST);

    headerSection.add(leftSection, BorderLayout.CENTER);
    headerSection.add(rightSection, BorderLayout.EAST);

    // Content section (placeholder for election details)
    JPanel contentSection = new JPanel();
    contentSection.setLayout(new BorderLayout());
    contentSection.setBackground(Color.WHITE);
    contentSection.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    contentSection.setPreferredSize(new java.awt.Dimension(0, 200));

    // Add placeholder content
    JLabel placeholderLabel = new JLabel("Election details and candidates will be displayed here");
    placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
    placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 12));
    placeholderLabel.setForeground(new Color(153, 153, 153));
    contentSection.add(placeholderLabel, BorderLayout.CENTER);

    card.add(headerSection, BorderLayout.NORTH);
    card.add(contentSection, BorderLayout.CENTER);

    return card;
  }

  private JButton createVoteButton(StaticElection election) {
    JButton button = new JButton("Vote");
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setForeground(new Color(51, 51, 51));
    button.setBackground(Color.WHITE);
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(8, 20, 8, 20)));
    button.setFocusPainted(false);
    button.setPreferredSize(new java.awt.Dimension(80, 35));

    // Enable/disable based on election status
    if (!election.getStatus().equals("Active")) {
      button.setEnabled(false);
      button.setForeground(new Color(153, 153, 153));
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
        handleVoteClick(election);
      }
    });

    return button;
  }

  private void handleVoteClick(StaticElection election) {
    // Handle vote button click
    System.out.println("Vote clicked for: " + election.getTitle());
    // Here you would typically open a voting dialog or navigate to voting page
  }

  // Inner class to represent an Election
  private static class StaticElection {
    private String title;
    private String date;
    private String status;
    private List<Candidate> candidates;

    public StaticElection(String title, String date, String status) {
      this.title = title;
      this.date = date;
      this.status = status;
    }

    public String getTitle() {
      return title;
    }

    public String getDate() {
      return date;
    }

    public String getStatus() {
      return status;
    }

    public List<Candidate> getCandidates(){
      return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
      this.candidates = candidates;
    }
  }
}