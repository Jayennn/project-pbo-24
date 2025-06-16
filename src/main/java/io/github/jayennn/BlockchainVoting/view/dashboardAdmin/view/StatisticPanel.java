package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatisticPanel extends JPanel {

  private final int[] candidateVotes = { 0, 0 }; // Sample data - can be updated dynamically
  private final String[] candidateNames = { "Candidate A", "Candidate B" };
  private final Color[] candidateColors = {
      new Color(64, 64, 64), // Dark gray for Candidate A
      new Color(160, 160, 160) // Light gray for Candidate B
  };

  public StatisticPanel() {
    initializeComponents();
  }

  private void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(new Color(248, 249, 250));
    setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    // Create 2x2 grid layout
    JPanel gridPanel = new JPanel(new GridLayout(2, 2, 20, 20));
    gridPanel.setBackground(new Color(248, 249, 250));

    // Top left - Voting Results with Donut Chart
    JPanel votingResultsCard = createVotingResultsCard();
    gridPanel.add(votingResultsCard);

    // Top right - Statistics with Bar Chart
    JPanel statisticsCard = createStatisticsCard();
    gridPanel.add(statisticsCard);

    // Bottom left - Placeholder
    JPanel bottomLeftCard = createPlaceholderCard();
    gridPanel.add(bottomLeftCard);

    // Bottom right - Placeholder
    JPanel bottomRightCard = createPlaceholderCard();
    gridPanel.add(bottomRightCard);

    add(gridPanel, BorderLayout.CENTER);
  }

  private JPanel createVotingResultsCard() {
    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

    // Header
    JLabel titleLabel = new JLabel("Voting Results");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setForeground(new Color(51, 51, 51));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    // Center panel with donut chart
    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.setBackground(Color.WHITE);

    DonutChartPanel donutChart = new DonutChartPanel();
    centerPanel.add(donutChart, BorderLayout.CENTER);

    // Legend panel
    JPanel legendPanel = createLegendPanel();
    centerPanel.add(legendPanel, BorderLayout.SOUTH);

    card.add(titleLabel, BorderLayout.NORTH);
    card.add(centerPanel, BorderLayout.CENTER);

    return card;
  }

  private JPanel createStatisticsCard() {
    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

    // Header
    JLabel titleLabel = new JLabel("Graphics Statistics");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setForeground(new Color(51, 51, 51));

    // Large number display
    JLabel numberLabel = new JLabel("00", SwingConstants.CENTER);
    numberLabel.setFont(new Font("Arial", Font.BOLD, 48));
    numberLabel.setForeground(new Color(51, 51, 51));
    numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

    // Bar chart panel
    BarChartPanel barChart = new BarChartPanel();

    // Legend for bar chart
    JPanel barLegendPanel = createBarLegendPanel();

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setBackground(Color.WHITE);
    topPanel.add(titleLabel, BorderLayout.NORTH);
    topPanel.add(numberLabel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.setBackground(Color.WHITE);
    bottomPanel.add(barChart, BorderLayout.CENTER);
    bottomPanel.add(barLegendPanel, BorderLayout.SOUTH);

    card.add(topPanel, BorderLayout.NORTH);
    card.add(bottomPanel, BorderLayout.CENTER);

    return card;
  }

  private JPanel createPlaceholderCard() {
    JPanel card = new JPanel();
    card.setBackground(new Color(220, 220, 220));
    card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    return card;
  }

  private JPanel createLegendPanel() {
    JPanel legendPanel = new JPanel(new GridBagLayout());
    legendPanel.setBackground(Color.WHITE);
    legendPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 0, 5, 0);

    int totalVotes = Arrays.stream(candidateVotes).sum();

    for (int i = 0; i < candidateNames.length; i++) {
      // Color indicator
      JPanel colorBox = new JPanel();
      colorBox.setBackground(candidateColors[i]);
      colorBox.setPreferredSize(new Dimension(12, 12));
      colorBox.setBorder(BorderFactory.createLineBorder(candidateColors[i]));

      // Candidate name and percentage
      int percentage = totalVotes > 0 ? (candidateVotes[i] * 100) / totalVotes : 0;
      JLabel candidateLabel = new JLabel(candidateNames[i]);
      candidateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      candidateLabel.setForeground(new Color(51, 51, 51));
      candidateLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

      JLabel percentageLabel = new JLabel(percentage + "%");
      percentageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
      percentageLabel.setForeground(new Color(102, 102, 102));

      gbc.gridx = 0;
      gbc.gridy = i;
      legendPanel.add(colorBox, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1.0;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      legendPanel.add(candidateLabel, gbc);

      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.fill = GridBagConstraints.NONE;
      legendPanel.add(percentageLabel, gbc);
    }

    return legendPanel;
  }

  private JPanel createBarLegendPanel() {
    JPanel legendPanel = new JPanel(new GridBagLayout());
    legendPanel.setBackground(Color.WHITE);
    legendPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(3, 0, 3, 0);

    int totalVotes = Arrays.stream(candidateVotes).sum();

    for (int i = 0; i < candidateNames.length; i++) {
      // Color indicator
      JPanel colorBox = new JPanel();
      colorBox.setBackground(candidateColors[i]);
      colorBox.setPreferredSize(new Dimension(12, 12));
      colorBox.setBorder(BorderFactory.createLineBorder(candidateColors[i]));

      // Candidate name and percentage
      int percentage = totalVotes > 0 ? (candidateVotes[i] * 100) / totalVotes : 0;
      JLabel candidateLabel = new JLabel(candidateNames[i]);
      candidateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
      candidateLabel.setForeground(new Color(51, 51, 51));
      candidateLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));

      JLabel percentageLabel = new JLabel(percentage + "%");
      percentageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
      percentageLabel.setForeground(new Color(102, 102, 102));

      gbc.gridx = 0;
      gbc.gridy = i;
      legendPanel.add(colorBox, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1.0;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      legendPanel.add(candidateLabel, gbc);

      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.fill = GridBagConstraints.NONE;
      legendPanel.add(percentageLabel, gbc);
    }

    return legendPanel;
  }

  // Donut Chart Panel
  private class DonutChartPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      int total = Arrays.stream(candidateVotes).sum();
      if (total == 0) {
        // Draw empty donut chart
        int size = Math.min(getWidth(), getHeight()) - 40;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        g2d.setColor(new Color(220, 220, 220));
        g2d.fillOval(x, y, size, size);

        int holeSize = size / 2;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + (size - holeSize) / 2, y + (size - holeSize) / 2, holeSize, holeSize);
        return;
      }

      int startAngle = 0;
      int size = Math.min(getWidth(), getHeight()) - 40;
      int x = (getWidth() - size) / 2;
      int y = (getHeight() - size) / 2;

      for (int i = 0; i < candidateVotes.length; i++) {
        int arcAngle = (int) Math.round(360.0 * candidateVotes[i] / total);
        g2d.setColor(candidateColors[i]);
        g2d.fillArc(x, y, size, size, startAngle, arcAngle);
        startAngle += arcAngle;
      }

      // Create donut hole
      int holeSize = size / 2;
      g2d.setColor(Color.WHITE);
      g2d.fillOval(x + (size - holeSize) / 2, y + (size - holeSize) / 2, holeSize, holeSize);
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(200, 200);
    }
  }

  // Bar Chart Panel
  private class BarChartPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      int maxVotes = Arrays.stream(candidateVotes).max().orElse(1);
      if (maxVotes == 0)
        maxVotes = 1; // Prevent division by zero

      int barWidth = 60;
      int barSpacing = 20;
      int totalWidth = candidateVotes.length * barWidth + (candidateVotes.length - 1) * barSpacing;
      int startX = (getWidth() - totalWidth) / 2;
      int maxBarHeight = getHeight() - 40;

      for (int i = 0; i < candidateVotes.length; i++) {
        int barHeight = candidateVotes[i] == 0 ? 20 : (candidateVotes[i] * maxBarHeight) / maxVotes;
        int x = startX + i * (barWidth + barSpacing);
        int y = getHeight() - barHeight - 20;

        g2d.setColor(candidateColors[i]);
        g2d.fillRect(x, y, barWidth, barHeight);

        // Draw value labels
        g2d.setColor(new Color(102, 102, 102));
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        String valueStr = "00";
        int textWidth = g2d.getFontMetrics().stringWidth(valueStr);
        g2d.drawString(valueStr, x + (barWidth - textWidth) / 2, y - 5);
      }
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(200, 120);
    }
  }

  // Method to update vote data (can be called from outside)
  public void updateVoteData(int[] newVotes) {
    if (newVotes.length == candidateVotes.length) {
      System.arraycopy(newVotes, 0, candidateVotes, 0, candidateVotes.length);
      repaint();
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Votes Statistic Dashboard");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 700);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(new StatisticPanel());
    frame.setVisible(true);
  }
}