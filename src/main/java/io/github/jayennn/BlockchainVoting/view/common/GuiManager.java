package io.github.jayennn.BlockchainVoting.view.common;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.github.jayennn.BlockchainVoting.controller.login.LoginController;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.DashboardAdmin;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUser;
import io.github.jayennn.BlockchainVoting.view.login.LoginGui;

public class GuiManager extends JFrame implements Navigator {
  private CardLayout cardLayout;
  private JPanel mainPanel;
  LoginGui loginGui;
  LoginController loginController;
  DashboardUser dashboardUser;

  public GuiManager() {
    initializeFrame();

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    add(mainPanel);

    // todo: change to interface later
    // loginGui = new LoginGui();
    // loginController = new LoginController(loginGui, this);
    // loginGui.setLoginController(loginController);

    // mainPanel.add(loginGui, "LoginCard");
    // cardLayout.show(mainPanel, "LoginCard");

    DashboardAdmin dashboardAdmin = new DashboardAdmin();
    mainPanel.add(dashboardAdmin, "DashboardAdminCard");
    cardLayout.show(mainPanel, "DashboardAdminCard");
    // cardLayout.show(dashboardAdmin,"DashboardAdminCard");

    InfoPanel infoPanel = new InfoPanel();
    mainPanel.add(infoPanel, "InfoCard");

  }

  @Override
  public void showPanel(String panelName) {
    cardLayout.show(mainPanel, panelName);
  }

  private void initializeFrame() {
    setTitle("Aplikasi Voting");
    setSize(1000, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GuiManager guiManager = new GuiManager();
      guiManager.setVisible(true);
    });
  }
}