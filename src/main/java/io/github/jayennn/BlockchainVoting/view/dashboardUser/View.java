package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import javax.swing.*;
import java.awt.*;

public interface View {
    io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.View getSidebarView();
    void switchRightPanel(String panelName);
    CardLayout getCardLayout();
    JPanel getContenPanel();
}
