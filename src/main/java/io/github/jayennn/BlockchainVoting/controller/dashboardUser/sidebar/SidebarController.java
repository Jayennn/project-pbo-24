package io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SIdebarView;

public class SidebarController {
    private SIdebarView view;
    private DashboardUserView userView;
    public SidebarController(SIdebarView view, DashboardUserView userView){
        this.view = view;
        this.userView = userView;
        view.setSwitchPanelhandler(this::switchPanel);
    }

    private void switchPanel(String name){
        userView.switchRightPanel(name);
    }

}
