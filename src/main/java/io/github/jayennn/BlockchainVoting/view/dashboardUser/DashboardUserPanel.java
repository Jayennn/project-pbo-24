package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.common.BasePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionPanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.ElectionView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.manage.Manage;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfilePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SidebarView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SidebarPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class DashboardUserPanel extends BasePanel implements DashboardUserView {

  private CardLayout cardLayout;
  private JPanel contentPanel;
  private JPanel profilePanel;
  private JPanel electionPanel;
  private JPanel manage;
  private SidebarPanel sidebar;

  public DashboardUserPanel() {}

  @Override
  public SidebarView getSidebarView() {
    return sidebar;
  }

  @Override
  public ProfileView getProfileView() {
    return (ProfileView) profilePanel;
  }

  @Override
  public void switchRightPanel(String panelName) {
    cardLayout.show(contentPanel,panelName);
  }

  @Override
  public CardLayout getCardLayout() {
    return cardLayout;
  }

  @Override
  public JPanel getContenPanel() {
    return contentPanel;
  }

  @Override
  public ElectionView getElectionView() {
    return (ElectionView) electionPanel;
  }

  @Override
  protected void initComponents() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    sidebar = new SidebarPanel();

    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(new Color(248, 249, 250));

    profilePanel = new ProfilePanel();
    electionPanel = new ElectionPanel();
    manage = new Manage();
    contentPanel.add(profilePanel, "profile");
    contentPanel.add(electionPanel, "election");
    contentPanel.add(manage, "manage");

    cardLayout.show(contentPanel, "profile");

    add(sidebar, BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);
  }
}