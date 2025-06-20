package io.github.jayennn.BlockchainVoting.view.dashboardUser;

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

public class ElectionHistory extends JPanel {

  private List<HistoricalElection> historicalElections;

  public ElectionHistory() {
    initializeHistoricalElections();
    initializeComponents();
  }

  private void initializeHistoricalElections() {
    historicalElections = new ArrayList<>();
    // Sample historical election data
    historicalElections
        .add(new HistoricalElection("Pemilihan Ketua BEM", "11/7/2025", "Completed", "Ahmad Rizki", 245, 189));
    historicalElections
        .add(new HistoricalElection("Pemilihan Wakil Ketua", "05/6/2025", "Completed", "Sarah Putri", 198, 156));
    historicalElections
        .add(new HistoricalElection("Pemilihan Sekretaris", "20/5/2025", "Completed", "Budi Santoso", 167, 134));
  }

  private void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(new Color(248, 249, 250));
    setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

    // Main container
    JPanel mainContainer = new JPanel();
    mainContainer.setLayout(new BorderLayout());
    mainContainer.setBackground(Color.WHITE);
    mainContainer.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(30, 30, 30, 30)));

    // Header section
    JPanel headerSection = createHeaderSection();
    mainContainer.add(headerSection, BorderLayout.NORTH);

    // Elections list section
    JPanel electionsSection = createElectionsSection();
    JScrollPane scrollPane = new JScrollPane(electionsSection);
    scrollPane.setBorder(null);
    scrollPane.setBackground(Color.WHITE);
    scrollPane.getViewport().setBackground(Color.WHITE);
    mainContainer.add(scrollPane, BorderLayout.CENTER);

    add(mainContainer, BorderLayout.CENTER);
  }

  private JPanel createHeaderSection() {
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

    JLabel titleLabel = new JLabel("Election History");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
    titleLabel.setForeground(new Color(51, 51, 51));

    JLabel subtitleLabel = new JLabel("Browse past elections and their results.");
    subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    subtitleLabel.setForeground(new Color(102, 102, 102));
    subtitleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

    JPanel titleContainer = new JPanel();
    titleContainer.setLayout(new BorderLayout());
    titleContainer.setBackground(Color.WHITE);
    titleContainer.add(titleLabel, BorderLayout.NORTH);
    titleContainer.add(subtitleLabel, BorderLayout.CENTER);

    headerPanel.add(titleContainer, BorderLayout.WEST);

    return headerPanel;
  }

  private JPanel createElectionsSection() {
    JPanel electionsPanel = new JPanel();
    electionsPanel.setLayout(new GridBagLayout());
    electionsPanel.setBackground(Color.WHITE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(0, 0, 20, 0);
    gbc.weightx = 1.0;
    gbc.gridx = 0;

    // Add historical election cards
    for (int i = 0; i < historicalElections.size(); i++) {
      HistoricalElection election = historicalElections.get(i);
      JPanel electionCard = createHistoricalElectionCard(election);
      gbc.gridy = i;
      electionsPanel.add(electionCard, gbc);
    }

    // Add empty space at the bottom
    gbc.gridy = historicalElections.size();
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    JPanel spacer = new JPanel();
    spacer.setBackground(Color.WHITE);
    electionsPanel.add(spacer, gbc);

    return electionsPanel;
  }

  private JPanel createHistoricalElectionCard(HistoricalElection election) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
        BorderFactory.createEmptyBorder(25, 25, 25, 25)));

    // Header section with title, date, and details button
    JPanel headerSection = new JPanel();
    headerSection.setLayout(new BorderLayout());
    headerSection.setBackground(Color.WHITE);
    headerSection.setPreferredSize(new java.awt.Dimension(0, 80));

    // Left side - Election info
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    leftPanel.setBackground(Color.WHITE);

    JLabel electionTitle = new JLabel(election.getTitle());
    electionTitle.setFont(new Font("Arial", Font.BOLD, 20));
    electionTitle.setForeground(new Color(51, 51, 51));

    JLabel electionDate = new JLabel(election.getDate());
    electionDate.setFont(new Font("Arial", Font.PLAIN, 14));
    electionDate.setForeground(new Color(102, 102, 102));
    electionDate.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

    leftPanel.add(electionTitle, BorderLayout.NORTH);
    leftPanel.add(electionDate, BorderLayout.CENTER);

    // Right side - Details button
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    rightPanel.setBackground(Color.WHITE);

    JButton detailsButton = createDetailsButton(election);
    rightPanel.add(detailsButton, BorderLayout.EAST);

    headerSection.add(leftPanel, BorderLayout.CENTER);
    headerSection.add(rightPanel, BorderLayout.EAST);

    // Content section (placeholder for election results)
    JPanel contentSection = new JPanel();
    contentSection.setLayout(new BorderLayout());
    contentSection.setBackground(Color.WHITE);
    contentSection.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)),
        BorderFactory.createEmptyBorder(20, 0, 0, 0)));
    contentSection.setPreferredSize(new java.awt.Dimension(0, 300));

    // Add election results preview
    JPanel resultsPreview = createResultsPreview(election);
    contentSection.add(resultsPreview, BorderLayout.CENTER);

    card.add(headerSection, BorderLayout.NORTH);
    card.add(contentSection, BorderLayout.CENTER);

    return card;
  }

  private JButton createDetailsButton(HistoricalElection election) {
    JButton button = new JButton("Details");
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setForeground(new Color(51, 51, 51));
    button.setBackground(Color.WHITE);
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
        BorderFactory.createEmptyBorder(10, 25, 10, 25)));
    button.setFocusPainted(false);
    button.setPreferredSize(new java.awt.Dimension(100, 40));

    // Hover effect
    button.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setBackground(new Color(240, 240, 240));
      }

      @Override
      public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(Color.WHITE);
      }
    });

    // Click handler
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleDetailsClick(election);
      }
    });

    return button;
  }

  private JPanel createResultsPreview(HistoricalElection election) {
    JPanel resultsPanel = new JPanel();
    resultsPanel.setLayout(new GridBagLayout());
    resultsPanel.setBackground(Color.WHITE);
    resultsPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 20, 10, 20);

    // Winner info
    JLabel winnerLabel = new JLabel("Winner: " + election.getWinner());
    winnerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    winnerLabel.setForeground(new Color(34, 139, 34));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    resultsPanel.add(winnerLabel, gbc);

    // Vote counts
    JLabel voteCountLabel = new JLabel(
        "Total Votes: " + election.getTotalVotes() + " | Participation: " + election.getParticipation());
    voteCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    voteCountLabel.setForeground(new Color(102, 102, 102));
    gbc.gridy = 1;
    resultsPanel.add(voteCountLabel, gbc);

    // Status
    JLabel statusLabel = new JLabel("Status: " + election.getStatus());
    statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    statusLabel.setForeground(new Color(51, 51, 51));
    gbc.gridy = 2;
    resultsPanel.add(statusLabel, gbc);

    // Placeholder for detailed results
    JLabel placeholderLabel = new JLabel("Click 'Details' to view complete election results and statistics");
    placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
    placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 12));
    placeholderLabel.setForeground(new Color(153, 153, 153));
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.insets = new Insets(20, 20, 10, 20);
    resultsPanel.add(placeholderLabel, gbc);

    return resultsPanel;
  }

  private void handleDetailsClick(HistoricalElection election) {
    System.out.println("Details clicked for: " + election.getTitle());
    // Here you would typically open a detailed results dialog or navigate to
    // results page
  }

  // Inner class to represent a Historical Election
  private static class HistoricalElection {
    private String title;
    private String date;
    private String status;
    private String winner;
    private int totalVotes;
    private int participation;

    public HistoricalElection(String title, String date, String status, String winner, int totalVotes,
        int participation) {
      this.title = title;
      this.date = date;
      this.status = status;
      this.winner = winner;
      this.totalVotes = totalVotes;
      this.participation = participation;
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

    public String getWinner() {
      return winner;
    }

    public int getTotalVotes() {
      return totalVotes;
    }

    public int getParticipation() {
      return participation;
    }
  }
}