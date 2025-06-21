package io.github.jayennn.BlockchainVoting.controller.dashboardUser;

import io.github.jayennn.BlockchainVoting.controller.dashboardUser.profile.ProfileController;
import io.github.jayennn.BlockchainVoting.controller.dashboardUser.sidebar.SidebarController;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.DashboardUserView;

import java.util.ArrayList;
import java.util.List;

public class DashboardUserController {
    private final DashboardUserView view;
    private final SidebarController sidebarController;
    private final ProfileController profileController;
    private final List<PostLogin> postLoginControllers = new ArrayList<>();

    public DashboardUserController(DashboardUserView view){
        this.view = view;
        this.sidebarController = new SidebarController(view.getSidebarView(),view);
        this.profileController = new ProfileController(view.getProfileView());

        this.postLoginControllers.add(profileController);
    }

    public List<PostLogin> getPostLoginControllers() {
        return postLoginControllers;
    }
}
