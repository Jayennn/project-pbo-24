package io.github.jayennn.BlockchainVoting.view.dashboardUser.election;

import io.github.jayennn.BlockchainVoting.entity.Election;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ElectionPanel extends JPanel implements ElectionView {

  private List<Election> elections;
  private JPanel electionsPanel;

  public ElectionPanel() {
    elections = new ArrayList<>();
    initializeComponents();
  }
  @Override
  public void addElection(Election election){
    elections.add(election);
  }

  @Override
  public void setElection(List<Election> elections) {
    this.elections = elections;
  }

  @Override
  public void updateElectionsPanel() {
    initializeComponents();

    System.out.println("total election = "+elections.size());
  }

  @Override
  public ElectionListView getElectionsView() {
    return (ElectionListView) electionsPanel;
  }

  private void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(new Color(248, 249, 250));
    setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    // Header
    JPanel headerPanel = createHeaderPanel();
    add(headerPanel, BorderLayout.NORTH);

    // Elections list
    electionsPanel = new ElectionListPanel();
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
}