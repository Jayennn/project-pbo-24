package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SIdebarView;

import javax.swing.*;
import java.awt.*;

public interface DashboardUserView {
    SIdebarView getSidebarView();
    ProfileView getProfileView();
    void switchRightPanel(String panelName);
    CardLayout getCardLayout();
    JPanel getContenPanel();
}
