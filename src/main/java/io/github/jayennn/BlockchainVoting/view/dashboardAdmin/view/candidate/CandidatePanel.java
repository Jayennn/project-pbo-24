package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.candidate;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.github.jayennn.BlockchainVoting.controller.admin.CandidateController;

public class CandidatePanel extends JPanel {
  private CandidateController candidateController;

  public CandidatePanel(CandidateController candidateController) {
    this.setLayout(new BorderLayout());
    JLabel label = new JLabel("Election ", SwingConstants.CENTER);
    label.setFont(new Font("arial", Font.BOLD, 24));
    this.add(label, BorderLayout.CENTER);

    this.candidateController = candidateController;
    System.out.println("CandidatePanel initialized with CandidateController");
  }

  public void testing() {
    System.out.println("Testing CandidatePanel functionality");
    // You can add more test logic here if needed
  }
}
