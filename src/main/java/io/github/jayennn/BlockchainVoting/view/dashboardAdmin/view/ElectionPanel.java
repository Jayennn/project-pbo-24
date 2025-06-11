package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ElectionPanel extends JPanel {

  public ElectionPanel() {
    this.setLayout(new BorderLayout());
    JLabel label = new JLabel("Election ", SwingConstants.CENTER);
    label.setFont(new Font("arial", Font.BOLD, 24));
    this.add(label, BorderLayout.CENTER);

    // Additional components for managing elections can be added here
  }

}
