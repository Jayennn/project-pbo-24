package io.github.jayennn.BlockchainVoting.view.dashboardAdmin;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.github.jayennn.BlockchainVoting.gui.dashboardAdmin.view.StatisticPanel;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.CandidatePanel;

public class DashboardAdmin extends JPanel {
    static JButton activeteButton = null;

    public DashboardAdmin() {
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setPreferredSize(new Dimension(300, 700));
        sidebar.setBackground(Color.WHITE);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/assets/itk.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(scaledLogo);

        JLabel logoLabel = new JLabel(resizedLogo);
        logoLabel.setBounds(50, 20, 200, 80);
        sidebar.add(logoLabel);

        JPanel contentPanel = new JPanel(new CardLayout());

        StatisticPanel statisticPanel = new StatisticPanel();
        contentPanel.add(statisticPanel, "statistic");

        CandidatePanel candidatePanel = new CandidatePanel();
        contentPanel.add(candidatePanel, "candidate");

        JPanel electionPanel = createContentPanel("Halaman manage election");
        contentPanel.add(electionPanel, "election");

        JPanel votersPanel = createContentPanel("Halaman manage vote");
        contentPanel.add(votersPanel, "voters");

        JButton btnStatistic = createMenuButton("votes Statistic", true);
        btnStatistic.setBounds(25, 150, 250, 40);
        sidebar.add(btnStatistic);
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

        });
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
            c1.show(contentPanel, "voters");
            switchActiveButton(btnVoter);
        });

    }


    private static JPanel createContentPanel(String labelTex) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(labelTex, SwingConstants.CENTER);
        label.setFont(new Font("arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
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