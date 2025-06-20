package io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.View;

public class SidebarController {
    private View view;
    private DashboardUserView userView;
    public SidebarController(View view, DashboardUserView userView){
        this.view = view;
        this.userView = userView;
        view.setSwitchPanelhandler(this::switchPanel);
    }

    private void switchPanel(String name){
        userView.switchRightPanel(name);
    }

}
