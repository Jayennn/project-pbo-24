package io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;

public class SidebarController {
    private final DashboardUserView dashboardUserView;
    public SidebarController(DashboardUserView dashboardUserView){
        this.dashboardUserView = dashboardUserView;
        dashboardUserView.getSidebarView().setSwitchPanelhandler(this::switchPanel);
    }

    private void switchPanel(String name){
        dashboardUserView.switchRightPanel(name);
    }
}
