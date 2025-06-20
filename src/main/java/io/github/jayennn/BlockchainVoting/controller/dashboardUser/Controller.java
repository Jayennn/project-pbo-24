package io.github.jayennn.BlockchainVoting.controller.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.View;

public class Controller {
    private final View view;
    private final io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.Controller sidebarController;
    public Controller(View view){
        this.view = view;
        this.sidebarController = new io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.Controller(view.getSidebarView(),view);
    }


}
