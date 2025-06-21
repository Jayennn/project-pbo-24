package io.github.jayennn.BlockchainVoting.view.common;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.DashboardUserController;
import io.github.jayennn.BlockchainVoting.controller.login.LoginController;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.DashboardAdmin;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserPanel;
import io.github.jayennn.BlockchainVoting.view.login.LoginPanel;

public class GuiManager extends JFrame implements Navigator {
  private CardLayout cardLayout;
  private JPanel mainPanel;

  LoginPanel loginGui;
  LoginController loginController;
  DashboardUserPanel dashboardUser;
  DashboardUserController dashboardUserController;

  public GuiManager() {
    initializeFrame();
    SessionManager.getInstance().setGuiManager(this);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    add(mainPanel);


    dashboardUser = new DashboardUserPanel();
    dashboardUserController = new DashboardUserController(dashboardUser);
    mainPanel.add(dashboardUser, "DashboardUserCard");

    loginGui = new LoginPanel();
    loginController = new LoginController(loginGui, this,dashboardUserController);
    mainPanel.add(loginGui, "LoginCard");

    DashboardAdmin dashboardAdmin = new DashboardAdmin();
    mainPanel.add(dashboardAdmin, "DashboardAdminCard");

    InfoPanel infoPanel = new InfoPanel();
    mainPanel.add(infoPanel, "InfoCard");


    cardLayout.show(mainPanel,"LoginCard");
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