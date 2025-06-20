package io.github.jayennn.BlockchainVoting.view.common;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.Controller;
import io.github.jayennn.BlockchainVoting.session.SessionManager;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.DashboardAdmin;
import io.github.jayennn.BlockchainVoting.view.login.Panel;

public class GuiManager extends JFrame implements Navigator {
  private CardLayout cardLayout;
  private JPanel mainPanel;

  Panel loginGui;
  io.github.jayennn.BlockchainVoting.controller.login.Controller loginController;
  io.github.jayennn.BlockchainVoting.view.dashboardUser.Panel dashboardUser;
  Controller dashboardUserController;

  public GuiManager() {
    initializeFrame();
    SessionManager.getInstance().setGuiManager(this);

    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    add(mainPanel);

    loginGui = new Panel();
    loginController = new io.github.jayennn.BlockchainVoting.controller.login.Controller(loginGui, this);
    mainPanel.add(loginGui, "LoginCard");

    dashboardUser = new io.github.jayennn.BlockchainVoting.view.dashboardUser.Panel();
    dashboardUserController = new Controller(dashboardUser);
    mainPanel.add(dashboardUser, "DashboardUserCard");

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