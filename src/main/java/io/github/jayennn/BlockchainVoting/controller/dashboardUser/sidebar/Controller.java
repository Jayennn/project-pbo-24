package io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.View;

public class Controller {
    private View view;
    private io.github.jayennn.BlockchainVoting.view.dashboardUser.View userView;
    public Controller(View view, io.github.jayennn.BlockchainVoting.view.dashboardUser.View userView){
        this.view = view;
        this.userView = userView;
        view.setSwitchPanelhandler(this::switchPanel);
    }

    private void switchPanel(String name){
        userView.switchRightPanel(name);
    }

}
