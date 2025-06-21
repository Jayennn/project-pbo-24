package io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.SwitchPanelHandler;
import io.github.jayennn.BlockchainVoting.session.SessionManager;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel implements SIdebarView {
    private JButton activeButton;
    private SwitchPanelHandler switchPanelHandler;

    public SidebarPanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new java.awt.Dimension(280, 700));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 230, 230)));

        // Top section with logo
        JPanel topSection = new JPanel();
        topSection.setLayout(new BorderLayout());
        topSection.setBackground(Color.WHITE);
        topSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));

        // Logo
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/assets/itk.png"));
            Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
            ImageIcon resizedLogo = new ImageIcon(scaledLogo);
            JLabel logoLabel = new JLabel(resizedLogo);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            topSection.add(logoLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            // Fallback if logo not found
            JLabel logoLabel = new JLabel("LOGO", SwingConstants.CENTER);
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            logoLabel.setPreferredSize(new java.awt.Dimension(200, 80));
            topSection.add(logoLabel, BorderLayout.CENTER);
        }

        // Navigation section
        JPanel navSection = new JPanel();
        navSection.setLayout(new GridBagLayout());
        navSection.setBackground(Color.WHITE);
        navSection.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.weightx = 1.0;

        // Navigation buttons
        JButton profileBtn = createMenuButton("👤 My Profile", true);
        JButton electionBtn = createMenuButton("🗳️ Election", false);
        JButton manageBtn = createMenuButton("⚙️ Manage Election", false);

        profileBtn.addActionListener(e -> {
            switchActiveButton(profileBtn);
            if(switchPanelHandler!=null){switchPanelHandler.handle("profile");}
        });

        electionBtn.addActionListener(e -> {
            switchActiveButton(electionBtn);
            if(switchPanelHandler!=null){switchPanelHandler.handle("election");}
        });

        manageBtn.addActionListener(e -> {
            switchActiveButton(manageBtn);
            if(switchPanelHandler!=null){switchPanelHandler.handle("manage");}
        });

        // Set initial active button
        activeButton = profileBtn;

        gbc.gridy = 0;
        navSection.add(profileBtn, gbc);
        gbc.gridy = 1;
        navSection.add(electionBtn, gbc);
        gbc.gridy = 2;
        navSection.add(manageBtn, gbc);

        // Bottom section with logout
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BorderLayout());
        bottomSection.setBackground(Color.WHITE);
        bottomSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));

        JButton logoutBtn = createMenuButton("📚 Logout", false);
        logoutBtn.addActionListener(e -> {
            SessionManager.getInstance().logout();
        });
        bottomSection.add(logoutBtn, BorderLayout.SOUTH);

        add(topSection, BorderLayout.NORTH);
        add(navSection, BorderLayout.CENTER);
        add(bottomSection, BorderLayout.SOUTH);
    }

    @Override
    public void setSwitchPanelhandler(SwitchPanelHandler switchPanelhandler) {
        this.switchPanelHandler = switchPanelhandler;
    }

    private JButton createMenuButton(String text, boolean active) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new java.awt.Dimension(240, 45));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        if (active) {
            setActiveStyle(button);
        } else {
            setInactiveStyle(button);
        }

        return button;
    }

    private void switchActiveButton(JButton newButton) {
        if (activeButton != null) {
            setInactiveStyle(activeButton);
        }
        activeButton = newButton;
        setActiveStyle(activeButton);
    }

    private void setActiveStyle(JButton button) {
        button.setBackground(new Color(230, 230, 230));
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        button.setOpaque(true);
    }

    private void setInactiveStyle(JButton button) {
        button.setBackground(Color.WHITE);
        button.setFont(button.getFont().deriveFont(Font.PLAIN));
        button.setOpaque(false);
    }

}
