package io.github.jayennn.BlockchainVoting.gui.dashboardUser;
import io.github.jayennn.BlockchainVoting.gui.GuiManager;

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

public class DashboardUser extends JPanel{
    public DashboardUser(GuiManager guiManager){
        JFrame frame = guiManager;
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0, 0, 300, 700);
        sidebar.setBackground(Color.WHITE);

        ImageIcon logoIcon = new ImageIcon("foto/itk.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        ImageIcon resizeLogo = new ImageIcon(scaledLogo);

        JLabel logoLabel = new JLabel(resizeLogo);
        logoLabel.setBounds(50, 20, 200, 80);
        sidebar.add(logoLabel);

        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBounds(300, 0, 700, 700);

        JPanel profilePanel = createContentPanel("halaman profil");
        contentPanel.add(profilePanel, "profil");

        JPanel electionPanel = createContentPanel("halaman pilihan");
        contentPanel.add(electionPanel, "pilihan");

        JPanel historyJPanel = createContentPanel("halaman riwayat");
        contentPanel.add(historyJPanel, "riwayat");

        JButton btnProfil = createMeButton("🧍 My Profile", true);
        btnProfil.setBounds(25, 200, 250, 40);
        sidebar.add(btnProfil);

        JButton btnElection = createMeButton("🗳️ Election", false);
        btnElection.setBounds(25, 250, 250, 40);
        sidebar.add(btnElection);

        JButton btnHistory = createMeButton("📜 History", false);
        btnHistory.setBounds(25, 300, 250, 40);
        sidebar.add(btnHistory);

        activeteButton = btnProfil;
        setActiveStyle(btnProfil);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(25, 600, 250, 40);
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(Color.white);
        btnLogout.setBorderPainted(false);
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
        sidebar.add(btnLogout);

        CardLayout c1 = (CardLayout) contentPanel.getLayout();

        btnProfil.addActionListener(e -> {
            c1.show(contentPanel, "profil");
            switchActiveButton(btnProfil);
        });

        btnElection.addActionListener(e -> {
            c1.show(contentPanel, "pilihan");
            switchActiveButton(btnElection);
        });

        btnHistory.addActionListener(e -> {
            c1.show(contentPanel, "riwayat");
            switchActiveButton(btnHistory);
        });


        frame.add(sidebar);
        frame.add(contentPanel);
        frame.setVisible(true);;
    }

    JButton activeteButton = null;

    private JPanel createContentPanel(String labelTex) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new  JLabel(labelTex, SwingConstants.CENTER);
        label.setFont(new Font("arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private static JButton createMeButton(String Text, boolean active) {
        JButton button = new JButton(Text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(active ? Color.LIGHT_GRAY : Color.WHITE);
        button.setBorderPainted(false);
        return button;
    }

    private void  switchActiveButton(JButton neButton) {
        if (activeteButton != null) {
            setInactiveStyle(activeteButton);
        }
        activeteButton = neButton;
        setActiveStyle(activeteButton);
    }

    private void setActiveStyle(JButton button) {
        button.setBackground(Color.lightGray);
        button.setFont(button.getFont().deriveFont(Font.BOLD));
    }

    private void setInactiveStyle(JButton button) {
        button.setBackground(Color.WHITE);
        button.setFont(button.getFont().deriveFont(Font.PLAIN));
    }
    
}
