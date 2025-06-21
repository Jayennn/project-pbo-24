package io.github.jayennn.BlockchainVoting.view.dashboardAdmin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mysql.cj.Session;
import io.github.jayennn.BlockchainVoting.controller.admin.CandidateController;
import io.github.jayennn.BlockchainVoting.controller.admin.ElectionController;
import io.github.jayennn.BlockchainVoting.controller.admin.VoterController;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.StatisticPanel;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.CandidatePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.ElectionPanel;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.VotersPanel;

public class DashboardAdmin extends JPanel {

  private CandidateController candidateController;
  private ElectionController electionController;
  private VoterController voterController;

  private VotersPanel votersPanel;

  static JButton activeteButton = null;

  public DashboardAdmin() {
    this.candidateController = new CandidateController();
    this.electionController = new ElectionController();
    this.voterController = new VoterController();
    initializeComponents();
  }

  public void initializeComponents() {
    setLayout(new BorderLayout());

    JPanel sidebar = new JPanel();
    sidebar.setLayout(null);
    sidebar.setPreferredSize(new Dimension(300, 700));
    sidebar.setBackground(Color.WHITE);

    ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/itk.png")));
    Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
    ImageIcon resizedLogo = new ImageIcon(scaledLogo);

    JLabel logoLabel = new JLabel(resizedLogo);
    logoLabel.setBounds(50, 20, 200, 80);
    sidebar.add(logoLabel);

    JPanel contentPanel = getJPanel();

    JButton btnStatistic = createMenuButton("votes Statistic", true);
    btnStatistic.setBounds(25, 150, 250, 40);
    sidebar.add(btnStatistic);

    // set the active button
    activeteButton = btnStatistic;

    JButton btnCandidate = createMenuButton("Manage Candidate", false);
    btnCandidate.setBounds(25, 200, 250, 40);
    sidebar.add(btnCandidate);

    JButton btnelection = createMenuButton("Manage Election", false);
    btnelection.setBounds(25, 250, 250, 40);
    sidebar.add(btnelection);

    JButton btnVoter = createMenuButton("Manage Voters", false);
    btnVoter.setBounds(25, 300, 250, 40);
    sidebar.add(btnVoter);


    JButton btnLogout = createMenuButton("Logout", false);
    btnLogout.setBounds(25, 600, 250, 40);
    btnLogout.setFocusPainted(false);
    btnLogout.setBackground(Color.WHITE);
    btnLogout.setBorderPainted(false);
    btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
    btnLogout.addActionListener(e -> {
      SessionManager.getInstance().logout();
    });
    sidebar.add(btnLogout);
    add(sidebar, BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);

    CardLayout c1 = (CardLayout) contentPanel.getLayout();

    btnStatistic.addActionListener(e -> {
      c1.show(contentPanel, "statistic");
      switchActiveButton(btnStatistic);
    });

    btnCandidate.addActionListener(e -> {
      c1.show(contentPanel, "candidate");
      switchActiveButton(btnCandidate);
    });

    btnelection.addActionListener(e -> {
      c1.show(contentPanel, "election");
      switchActiveButton(btnelection);
    });

    btnVoter.addActionListener(e -> {
      votersPanel.reset();
      c1.show(contentPanel, "voters");
      switchActiveButton(btnVoter);
    });

  }

  private JPanel getJPanel() {
    JPanel contentPanel = new JPanel(new CardLayout());

    StatisticPanel statisticPanel = new StatisticPanel();
    contentPanel.add(statisticPanel, "statistic");

    CandidatePanel candidatePanel = new CandidatePanel(candidateController);
    contentPanel.add(candidatePanel, "candidate");

    ElectionPanel electionPanel = new ElectionPanel(electionController);
    contentPanel.add(electionPanel, "election");

    votersPanel = new VotersPanel(voterController);
    contentPanel.add(votersPanel, "voters");

    return contentPanel;
  }

  private static JButton createMenuButton(String text, boolean active) {
    JButton button = new JButton(text);
    button.setFocusPainted(false);
    button.setHorizontalAlignment(SwingConstants.LEFT);
    button.setBackground(active ? Color.LIGHT_GRAY : Color.WHITE);
    button.setBorderPainted(false);
    return button;
  }

  private static void switchActiveButton(JButton newButton) {
    if (activeteButton != null) {
      setInactiveStyle(activeteButton);
    }
    activeteButton = newButton;
    setActiveStyle(activeteButton);
  }

  private static void setActiveStyle(JButton button) {
    button.setBackground(Color.LIGHT_GRAY);
    button.setFont(button.getFont().deriveFont(Font.BOLD));
  }

  private static void setInactiveStyle(JButton button) {
    button.setBackground(Color.white);
    button.setFont(button.getFont().deriveFont(Font.PLAIN));
  }
}