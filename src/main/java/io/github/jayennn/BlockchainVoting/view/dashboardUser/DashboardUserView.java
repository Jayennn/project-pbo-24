package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import javax.swing.*;
import java.awt.*;

public interface DashboardUserView {
    io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.View getSidebarView();
    void switchRightPanel(String panelName);
    CardLayout getCardLayout();
    JPanel getContenPanel();
}
