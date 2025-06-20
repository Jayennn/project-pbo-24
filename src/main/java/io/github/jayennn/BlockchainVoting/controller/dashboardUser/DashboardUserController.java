package io.github.jayennn.BlockchainVoting.controller.dashboardUser;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.SidebarController;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;

public class DashboardUserController {
    private final DashboardUserView view;
    private final SidebarController sidebarController;
    public DashboardUserController(DashboardUserView view){
        this.view = view;
        this.sidebarController = new SidebarController(view.getSidebarView(),view);
    }


}
