package io.github.jayennn.BlockchainVoting.gui;

import io.github.jayennn.BlockchainVoting.gui.dashboardUser.DashboardUser;

import javax.swing.*;
import java.awt.*;

public class GuiManager extends JFrame {
    public GuiManager(){
        setTitle("Aplikasi Voting");
        setSize(1000,800);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showLogin();
    }

    public void showLogin(){
        getContentPane().removeAll();
        add(new LoginGui(this));
        revalidate();
        repaint();
    }

    public void showDashboardUser(){
        getContentPane().removeAll();
        add(new DashboardUser(this));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        GuiManager guiManager = new GuiManager();

    }
}


