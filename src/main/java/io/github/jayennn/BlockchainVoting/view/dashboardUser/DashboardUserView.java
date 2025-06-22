package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SidebarView;

import javax.swing.*;
import java.awt.*;

public interface DashboardUserView {
    SidebarView getSidebarView();
    ProfileView getProfileView();
    ElectionView getElectionView();
    CardLayout getCardLayout();
    JPanel getContenPanel();
    void switchRightPanel(String panelName);
}
