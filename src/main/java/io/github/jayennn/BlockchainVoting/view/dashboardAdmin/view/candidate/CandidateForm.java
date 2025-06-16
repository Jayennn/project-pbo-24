package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.candidate;

import io.github.jayennn.BlockchainVoting.controller.admin.CandidateController;
import io.github.jayennn.BlockchainVoting.entity.Candidate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.UUID;

public class CandidateForm extends JPanel {
  // Controller
  private final CandidateController candidateController;

  // Form position
  public enum FormPosition {
    TOP, BOTTOM
  }

  // Form components
  private JTextField nameField;
  private JTextArea visionArea;
  private JTextArea missionArea;
  private JButton submitButton;
  private JButton cancelButton;

  // For edit mode
  private UUID editingCandidateId = null;

  // Callback for refreshing the table after form submission
  private Runnable refreshTableCallback;

  public CandidateForm(CandidateController candidateController, Runnable refreshTableCallback) {
    this.candidateController = candidateController;
    this.refreshTableCallback = refreshTableCallback;

    initializeUI();
  }

  private void initializeUI() {
    // Set layout and styling
    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            "Candidate Information",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 14),
            new Color(70, 70, 70)),
        new EmptyBorder(10, 15, 10, 15)));
    setBackground(Color.WHITE);

    // Create form panel
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridBagLayout());
    formPanel.setBackground(Color.WHITE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    // Name field
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.2;
    formPanel.add(createLabel("Name:"), gbc);

    gbc.gridx = 1;
    gbc.weightx = 0.8;
    nameField = createTextField(20);
    formPanel.add(nameField, gbc);

    // Vision field
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0.2;
    formPanel.add(createLabel("Vision:"), gbc);

    gbc.gridx = 1;
    gbc.weightx = 0.8;
    visionArea = createTextArea(3, 20);
    JScrollPane visionScroll = new JScrollPane(visionArea);
    formPanel.add(visionScroll, gbc);

    // Mission field
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0.2;
    formPanel.add(createLabel("Mission:"), gbc);

    gbc.gridx = 1;
    gbc.weightx = 0.8;
    missionArea = createTextArea(3, 20);
    JScrollPane missionScroll = new JScrollPane(missionArea);
    formPanel.add(missionScroll, gbc);

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.setBackground(Color.WHITE);

    cancelButton = new JButton("Cancel");
    styleButton(cancelButton, new Color(240, 240, 240), new Color(70, 70, 70));
    cancelButton.addActionListener(e -> resetForm());

    submitButton = new JButton("Add Candidate");
    styleButton(submitButton, new Color(66, 133, 244), Color.WHITE);
    submitButton.addActionListener(e -> submitForm());

    buttonPanel.add(cancelButton);
    buttonPanel.add(submitButton);

    // Add components to main panel
    add(formPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 12));
    return label;
  }

  private JTextField createTextField(int columns) {
    JTextField field = new JTextField(columns);
    field.setFont(new Font("Arial", Font.PLAIN, 12));
    return field;
  }

  private JTextArea createTextArea(int rows, int columns) {
    JTextArea area = new JTextArea(rows, columns);
    area.setFont(new Font("Arial", Font.PLAIN, 12));
    area.setLineWrap(true);
    area.setWrapStyleWord(true);
    return area;
  }

  private void styleButton(JButton button, Color background, Color foreground) {
    button.setBackground(background);
    button.setForeground(foreground);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setFont(new Font("Arial", Font.BOLD, 12));
    button.setPreferredSize(new Dimension(120, 30));
  }

  private boolean validateForm() {
    StringBuilder errors = new StringBuilder();

    if (nameField.getText().trim().isEmpty()) {
      errors.append("- Name is required\n");
    }

    if (visionArea.getText().trim().isEmpty()) {
      errors.append("- Vision is required\n");
    }

    if (missionArea.getText().trim().isEmpty()) {
      errors.append("- Mission is required\n");
    }

    if (errors.length() > 0) {
      JOptionPane.showMessageDialog(this,
          "Please correct the following errors:\n" + errors.toString(),
          "Validation Error",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    return true;
  }

  private void submitForm() {
    if (!validateForm()) {
      return;
    }

    String name = nameField.getText().trim();
    String vision = visionArea.getText().trim();
    String mission = missionArea.getText().trim();

    try {
      // Add new candidate - note that your controller doesn't have an update method
      // so we're always adding a new candidate
      candidateController.addCandidate(name, mission, vision);

      JOptionPane.showMessageDialog(this,
          "Candidate added successfully!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);

      // Reset form and refresh table
      resetForm();
      if (refreshTableCallback != null) {
        refreshTableCallback.run();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this,
          "Error saving candidate: " + e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  public void resetForm() {
    nameField.setText("");
    visionArea.setText("");
    missionArea.setText("");
    editingCandidateId = null;
    submitButton.setText("Add Candidate");
  }

  public void loadCandidateForEdit(String name, String vision, String mission) {
    nameField.setText(name);
    visionArea.setText(vision);
    missionArea.setText(mission);

    // Note: Since your controller doesn't have an update method,
    // we'll just add a new candidate with the edited information
    submitButton.setText("Add Candidate");
  }
}