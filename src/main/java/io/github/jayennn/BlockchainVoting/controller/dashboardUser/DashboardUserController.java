package io.github.jayennn.BlockchainVoting.controller.dashboardUser;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.election.ElectionController;
import io.github.jayennn.BlockchainVoting.controller.dashboardUser.profile.ProfileController;
import io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.SidebarController;
import io.github.jayennn.BlockchainVoting.controller.login.LoginController;
import io.github.jayennn.BlockchainVoting.controller.login.LoginListener;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;

public class DashboardUserController implements LoginListener {
    private final DashboardUserView view;
    private final ProfileController profileController;

    public DashboardUserController(DashboardUserView view, LoginController loginController){
        this.view = view;
        SidebarController sidebarController = new SidebarController(view);
        profileController = new ProfileController(view.getProfileView());
        ElectionController electionController = new ElectionController(view.getElectionView(),this);

        loginController.addLoginListener(this, profileController, electionController);
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    @Override
    public void onLogin() {
        view.getCardLayout().show(view.getContenPanel(),"profile");
    }

}
