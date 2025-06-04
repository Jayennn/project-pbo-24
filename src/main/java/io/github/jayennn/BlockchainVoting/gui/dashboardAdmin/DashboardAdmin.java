package io.github.jayennn.BlockchainVoting.gui.dashboardAdmin;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardAdmin {
    static JButton activeteButton = null;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 300, 700);
        sidebar.setBackground(Color.WHITE);

        ImageIcon logoIcon = new ImageIcon("foto/logo-itk 1.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(scaledLogo);

        JLabel logoLabel = new JLabel(resizedLogo);
        logoLabel.setBounds(50, 20, 200, 80); 
        sidebar.add(logoLabel);

        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBounds(300, 0, 700, 700);

        JPanel statisticPanel = createContentPanel("halaman vote statistic");
        JPanel candidatesPanel = createContentPanel("halaman manage candidate");
        JPanel electionPanel = createContentPanel("halaman manage acara");
        JPanel votersPanel = createContentPanel("halaman manage voters");
        
        contentPanel.add(statisticPanel, "statistic");
        contentPanel.add(candidatesPanel, "candidates");
        contentPanel.add(electionPanel, "election");
        contentPanel.add(votersPanel, "voters");

        JButton btnStatistic = createMenuButton("✓ Votes Statistic", true);
        btnStatistic.setBounds(25, 150, 250, 40);
        sidebar.add(btnStatistic);

        JButton btnCandidate = createMenuButton("👥 Manage Candidates", false);
        btnCandidate.setBounds(25, 200, 250, 40);
        sidebar.add(btnCandidate);

        JButton btnElection = createMenuButton("👥 Manage Election", false);
        btnElection.setBounds(25, 250, 250, 40);
        sidebar.add(btnElection);

        JButton btnVoters = createMenuButton("≡ Manage Voters", false);
        btnVoters.setBounds(25, 300, 250, 40);
        sidebar.add(btnVoters);

        activeteButton = btnStatistic;
        setActiveStyle(btnStatistic);

        JButton btnLogout = new JButton("⏻ Logout");
        btnLogout.setBounds(25, 600, 250, 40);
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setBorderPainted(false);
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
        sidebar.add(btnLogout);

        CardLayout c1 = (CardLayout) contentPanel.getLayout();

         btnStatistic.addActionListener(e -> {
            c1.show(contentPanel, "statistic");
            switchActiveButton(btnStatistic);
        });

        btnCandidate.addActionListener(e -> {
            c1.show(contentPanel, "candidates");
            switchActiveButton(btnCandidate);
        });

        btnElection.addActionListener(e -> {
            c1.show(contentPanel, "election");
            switchActiveButton(btnElection);
        });

        btnVoters.addActionListener(e -> {
            c1.show(contentPanel, "voters");
            switchActiveButton(btnVoters);
        });

        frame.add(sidebar);
        frame.add(contentPanel);
        frame.setVisible(true);
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
