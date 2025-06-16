package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.List;
import java.util.UUID;

import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.controller.admin.CandidateController;

public class CandidatePanel extends JPanel {
  private final CandidateController candidateController;

  private JTable table;
  private DefaultTableModel tableModel;

  private JTextField searchField;

  private JButton addButton;

  public CandidatePanel(CandidateController candidateController) {
    this.candidateController = candidateController;
    initializeUI();
  }

  private void initializeUI() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    initTable();
    initTopPanel();
  }

  private void initTopPanel() {
    searchField = new JTextField(20);
    addButton = new JButton("Add Candidate");

    handleCandidateActions();

    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setBackground(Color.WHITE);
    topPanel.add(searchField);
    topPanel.add(addButton);

    add(topPanel, BorderLayout.NORTH);
  }

  private JTable initTable() {
    String[] columnNames = { "Candidate Id", "Name", "Vission", "Mission", "Election ID", "Actions" };
    tableModel = new DefaultTableModel(columnNames, 0);

    // TODO: Replace with actual data from controller
    // model.addRow(new Object[] { "Candidate 1", "Vision 1", "Mission 1", "Election
    // 1" });
    // model.addRow(new Object[] { "Candidate 2", "Vision 2", "Mission 2", "Election
    // 2" });

    table = new JTable(tableModel);
    table.setRowHeight(30);
    table.getColumnModel().getColumn(5).setCellRenderer(new ActionButtonRenderer());
    table.getColumnModel().getColumn(5).setCellEditor(new ActionButtonEditor(new JCheckBox()));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    add(scrollPane, BorderLayout.CENTER);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    handleTableClick();

    refreshTable();
    return table;
  }

  private void handleTableClick() {
    table.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        loadSelectedRowData();
      }
    });
  }

  private void loadSelectedRowData() {
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
      return; // No row selected
    }

    System.out.println("Selected Row: " + selectedRow);
  }

  private void refreshTable() {
    tableModel.setRowCount(0); // Clear existing rows

    try {

      List<Candidate> candidates = candidateController.getCandidates();
      // Fetch candidates from the controller and populate the table
      if (candidates.isEmpty()) {
        System.out.println("No candidates found.");
        return;
      }

      for (Candidate candidate : candidates) {
        tableModel.addRow(new Object[] {
            candidate.getUuid(),
            candidate.getName(),
            candidate.getVission(),
            candidate.getMission(),
            candidate.getElection() != null ? candidate.getElection().getTitle() : "-", // safer display
            "" // Action buttons handled by custom renderer/editor
        });
      }

    } catch (Exception e) {
      JOptionPane.showMessageDialog(this,
          "Error loading candidates: " + e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  private void handleCandidateActions() {
    addButton.addActionListener(e -> {
      // TODO: Implement the logic to add a new candidate
      // This is a placeholder for the actual implementation
      // Election id
      candidateController.addCandidate("New Candidate", "New Vision", "New Mission");
      JOptionPane.showMessageDialog(this, "Add Candidate clicked");

      refreshTable();
    });

  }

  // -------------------------
  // Renderer & Editor classes
  // -------------------------

  private class ActionButtonRenderer extends JPanel implements TableCellRenderer {
    public ActionButtonRenderer() {
      setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
      setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
      removeAll();
      add(createIconButton("✏️"));
      add(createIconButton("🗑️"));
      return this;
    }
  }

  private class ActionButtonEditor extends DefaultCellEditor {
    private final JPanel panel;
    private final JButton btnEdit;
    private final JButton btnDelete;

    public ActionButtonEditor(JCheckBox checkBox) {
      super(checkBox);
      panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
      panel.setBackground(Color.WHITE);

      btnEdit = createIconButton("✏️");
      btnDelete = createIconButton("🗑️");

      panel.add(btnEdit);
      panel.add(btnDelete);

      btnEdit.addActionListener(e -> {
        stopCellEditing();
        JOptionPane.showMessageDialog(null, "Edit clicked");
      });

      btnDelete.addActionListener(e -> {
        String candidateId = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
        candidateController.deleteCandidate(UUID.fromString(candidateId));
        JOptionPane.showMessageDialog(null, "Delete clicked");

        refreshTable();
      });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
      return panel;
    }

    @Override
    public Object getCellEditorValue() {
      return "";
    }
  }

  // -------------------------
  // Utility method
  // -------------------------

  private JButton createIconButton(String label) {
    JButton button = new JButton(label);
    button.setMargin(new Insets(2, 4, 2, 4));
    button.setFocusable(false);
    button.setBackground(Color.WHITE);
    return button;
  }
}
