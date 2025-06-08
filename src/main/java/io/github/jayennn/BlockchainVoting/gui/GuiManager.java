package io.github.jayennn.BlockchainVoting.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import io.github.jayennn.BlockchainVoting.controller.Login;
import io.github.jayennn.BlockchainVoting.gui.dashboardAdmin.DashboardAdmin;

public class GuiManager extends JFrame {
    private final Login loginController;

    public GuiManager() {
        this.loginController = new Login();
        initializeFrame();
        showLogin();
    }

    private void initializeFrame() {
        setTitle("Aplikasi Voting");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void showLogin() {
        getContentPane().removeAll();
        add(new LoginGui(this, loginController));
        revalidate();
        repaint();
    }

    public void showDashboardAdmin() {
        getContentPane().removeAll();
        add(new DashboardAdmin(this));
        revalidate();
        repaint();
    }

    // public void showDashboardUser() {
    //     getContentPane().removeAll();
    //     add(new DashboardUser(this));
    //     revalidate();
    //     repaint();
    // }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuiManager guiManager = new GuiManager();
            guiManager.setVisible(true);
        });
    }
}