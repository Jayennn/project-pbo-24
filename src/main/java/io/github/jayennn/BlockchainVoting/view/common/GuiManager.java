package io.github.jayennn.BlockchainVoting.view.common;

import java.awt.*;

import javax.swing.*;

import io.github.jayennn.BlockchainVoting.controller.login.LoginController;
import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.DashboardAdmin;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUser;
import io.github.jayennn.BlockchainVoting.view.login.ILoginView;
import io.github.jayennn.BlockchainVoting.view.login.LoginGui;

public class GuiManager extends JFrame implements Navigator{
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

        loginGui = new LoginGui();
        loginController= new LoginController(loginGui,this);
        loginGui.setLoginController(loginController);

        mainPanel.add(loginGui, "Login");
        cardLayout.show(mainPanel,"Login");
//        dashboardUser = new DashboardUser(this);

//        mainPanel.add(dashboardUser,"DashboardUser");
//        cardLayout.show(mainPanel,"DashboardUser");

    }

    @Override
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel,panelName);
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
//        add(new LoginGui(this, loginController));
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