package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;
import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.controller.admin.CandidateController;
import io.github.jayennn.BlockchainVoting.controller.admin.ElectionController;

public class CandidatePanel extends JPanel {
  private final CandidateController candidateController;
  private JTable table;
  private DefaultTableModel model;
  private JTextField searchField;
  private JPanel formPanel;
  private JTextField nameInput;
  private JTextArea visiInput;
  private JTextArea misiInput;

  private UUID electionId = null;

  private int editingRow = -1;

  public CandidatePanel(CandidateController candidateController) {
    this.candidateController = candidateController;
    initializeUI();
  }

  private void initializeUI() {
    setLayout(new BorderLayout(10, 10));
    setBorder(new EmptyBorder(10, 10, 10, 10));
    setBackground(Color.WHITE);

    initTopPanel();
    initTable();
    initFormPanel();

    refreshTable();
  }

  private void initTopPanel() {
    JPanel topPanel = new JPanel(new BorderLayout(10, 10));
    topPanel.setBackground(Color.WHITE);

    // Search panel
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    searchPanel.setBackground(Color.WHITE);
    searchField = new JTextField(25);
    searchField.setPreferredSize(new Dimension(250, 30));
    JButton searchButton = new JButton("Search");
    searchButton.addActionListener(e -> searchCandidates());
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Action buttons
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    actionPanel.setBackground(Color.WHITE);
    JButton addButton = new JButton("Add");
    addButton.setPreferredSize(new Dimension(180, 30));
    addButton.addActionListener(e -> showForm("", "", "", -1, null));
    actionPanel.add(addButton);

    topPanel.add(searchPanel, BorderLayout.WEST);
    topPanel.add(actionPanel, BorderLayout.EAST);

    add(topPanel, BorderLayout.NORTH);
  }

  private void initTable() {
    String[] columns = { "ID", "Name", "Vision", "Mission", "Election", "Actions" };
    model = new DefaultTableModel(columns, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 5; // Only action column is editable
      }
    };

    table = new JTable(model);
    table.setRowHeight(35);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setShowGrid(false);

    // Custom renderer and editor for action column
    TableColumn actionColumn = table.getColumnModel().getColumn(5);
    actionColumn.setCellRenderer(new ActionButtonRenderer());
    actionColumn.setCellEditor(new ActionButtonEditor(new JCheckBox()));
    actionColumn.setPreferredWidth(120);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    add(scrollPane, BorderLayout.CENTER);
  }

  private void initFormPanel() {
    formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Candidate Form"),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    formPanel.setBackground(new Color(245, 245, 245));
    formPanel.setVisible(false);

    // Name field
    JPanel namePanel = new JPanel(new BorderLayout(5, 5));
    namePanel.setBackground(new Color(245, 245, 245));
    namePanel.add(new JLabel("Name:"), BorderLayout.NORTH);
    nameInput = new JTextField();
    nameInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    namePanel.add(nameInput, BorderLayout.CENTER);

    // Vision field
    JPanel visiPanel = new JPanel(new BorderLayout(5, 5));
    visiPanel.setBackground(new Color(245, 245, 245));
    visiPanel.add(new JLabel("Vision:"), BorderLayout.NORTH);
    visiInput = new JTextArea(3, 20);
    visiInput.setLineWrap(true);
    visiInput.setWrapStyleWord(true);
    JScrollPane visiScroll = new JScrollPane(visiInput);

    // Mission field
    JPanel misiPanel = new JPanel(new BorderLayout(5, 5));
    misiPanel.setBackground(new Color(245, 245, 245));
    misiPanel.add(new JLabel("Mission:"), BorderLayout.NORTH);
    misiInput = new JTextArea(3, 20);
    misiInput.setLineWrap(true);
    misiInput.setWrapStyleWord(true);
    JScrollPane misiScroll = new JScrollPane(misiInput);

    // Election field
    ElectionController electionController = new ElectionController();
    List<Election> elections = electionController.getElections();

    JPanel electionPanel = new JPanel(new BorderLayout(5, 5));
    electionPanel.setBackground(new Color(245, 245, 245));
    electionPanel.add(new JLabel("Election:"), BorderLayout.NORTH);
    JComboBox<Election> electionComboBox = new JComboBox<>();
    for (Election election : elections) {
      electionComboBox.addItem(election);
    }

    electionComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    electionPanel.add(electionComboBox, BorderLayout.CENTER);

    electionComboBox.addActionListener(e -> {
      Election selectedElection = (Election) electionComboBox.getSelectedItem();
      if (selectedElection != null) {
        this.electionId = selectedElection.getUuid();
      }
    });

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    buttonPanel.setBackground(new Color(245, 245, 245));
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    saveButton.addActionListener(e -> onSubmit());
    cancelButton.addActionListener(e -> hideForm());

    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);

    // Add components to form panel
    formPanel.add(namePanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(visiPanel);
    formPanel.add(visiScroll);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(misiPanel);
    formPanel.add(misiScroll);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(electionPanel);
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(buttonPanel);

    add(formPanel, BorderLayout.SOUTH);
  }

  private void searchCandidates() {
    String searchTerm = searchField.getText().trim();
    if (searchTerm.isEmpty()) {
      refreshTable();
      return;
    }

    List<Candidate> candidates = candidateController.getCandidates();
    model.setRowCount(0);

    for (Candidate candidate : candidates) {
      if (candidate.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
          candidate.getVission().toLowerCase().contains(searchTerm.toLowerCase()) ||
          candidate.getMission().toLowerCase().contains(searchTerm.toLowerCase())) {

        model.addRow(new Object[] {
            candidate.getUuid(),
            candidate.getName(),
            candidate.getVission(),
            candidate.getMission(),
            candidate.getElection() != null ? candidate.getElection().getTitle() : "-",
            ""
        });
      }
    }
  }

  private void refreshTable() {
    SwingUtilities.invokeLater(() -> {
      model.setRowCount(0);
      List<Candidate> candidates = candidateController.getCandidates();

      for (Candidate candidate : candidates) {
        model.addRow(new Object[] {
            candidate.getUuid(),
            candidate.getName(),
            candidate.getVission(),
            candidate.getMission(),
            candidate.getElection() != null ? candidate.getElection().getTitle() : "-",
            ""
        });
      }
    });
  }

  private void showForm(String name, String visi, String misi, int row, String id) {
    nameInput.setText(name);
    visiInput.setText(visi);
    misiInput.setText(misi);
    editingRow = row;
    formPanel.setVisible(true);
    revalidate();
    repaint();
  }

  private void hideForm() {
    nameInput.setText("");
    visiInput.setText("");
    misiInput.setText("");
    editingRow = -1;
    formPanel.setVisible(false);
    revalidate();
    repaint();
  }

  private void onSubmit() {
    String name = nameInput.getText().trim();
    String visi = visiInput.getText().trim();
    String misi = misiInput.getText().trim();

    if (name.trim().isEmpty() || visi.trim().isEmpty() || misi.trim().isEmpty() || electionId == null) {
      JOptionPane.showMessageDialog(this, "Nama, Visi dan Misi tidak boleh kosong", "Warning",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    if (name.length() > 100 || visi.length() > 500 || misi.length() > 500) {
      JOptionPane.showMessageDialog(this, "Teks terlalu panjang. Maksimal: Nama (100), Visi/Misi (500)", "Warning",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    for (int i = 0; i < model.getRowCount(); i++) {
      if (i != editingRow && name.equalsIgnoreCase(model.getValueAt(i, 0).toString().trim())) {
        JOptionPane.showMessageDialog(this, "Nama kandidat sudah terdaftar.", "Warning",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

    }

    ElectionController electionController = new ElectionController();
    Election election = electionController.getElectionById(electionId);

    if (election == null) {
      JOptionPane.showMessageDialog(this,
          "Invalid election selected.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (editingRow == -1) {
      // Add new candidate
      candidateController.addCandidate(name, misi, visi, election);
      JOptionPane.showMessageDialog(this,
          "Candidate added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
      // Update existing candidate
      String id = model.getValueAt(editingRow, 0).toString();
      candidateController.editCandidate(UUID.fromString(id), name, misi, visi, election);
      JOptionPane.showMessageDialog(this,
          "Candidate updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    hideForm();
    refreshTable();
  }

  // Action button renderer and editor
  private class ActionButtonRenderer extends JPanel implements TableCellRenderer {
    public ActionButtonRenderer() {
      setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
      setBackground(Color.WHITE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      removeAll();

      JButton editBtn = new JButton("Edit");
      editBtn.setBackground(new Color(70, 130, 180));
      editBtn.setForeground(Color.WHITE);
      editBtn.setFocusPainted(false);

      JButton deleteBtn = new JButton("Delete");
      deleteBtn.setBackground(new Color(220, 53, 69));
      deleteBtn.setForeground(Color.WHITE);
      deleteBtn.setFocusPainted(false);

      add(editBtn);
      add(deleteBtn);

      return this;
    }
  }

  private class ActionButtonEditor extends DefaultCellEditor {
    private final JPanel panel;
    private final JButton editBtn;
    private final JButton deleteBtn;
    private int currentRow;

    public ActionButtonEditor(JCheckBox checkBox) {
      super(checkBox);
      panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
      panel.setBackground(Color.WHITE);

      editBtn = new JButton("Edit");
      editBtn.setBackground(new Color(70, 130, 180));
      editBtn.setForeground(Color.WHITE);
      editBtn.setFocusPainted(false);

      deleteBtn = new JButton("Delete");
      deleteBtn.setBackground(new Color(220, 53, 69));
      deleteBtn.setForeground(Color.WHITE);
      deleteBtn.setFocusPainted(false);

      editBtn.addActionListener(e -> {
        int currentRow = table.getSelectedRow();

        if (currentRow == -1) {
          JOptionPane.showMessageDialog(panel, "Please select a candidate to edit.",
              "No Selection", JOptionPane.WARNING_MESSAGE);
          return;
        }

        String id = model.getValueAt(currentRow, 0).toString();
        String name = model.getValueAt(currentRow, 1).toString();
        String visi = model.getValueAt(currentRow, 2).toString();
        String misi = model.getValueAt(currentRow, 3).toString();

        showForm(name, visi, misi, currentRow, id);
        fireEditingStopped();
      });

      deleteBtn.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
            panel,
            "Are you sure you want to delete this candidate?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
          String id = model.getValueAt(currentRow, 0).toString();
          candidateController.deleteCandidate(UUID.fromString(id));
          refreshTable();
        }
        fireEditingStopped();
      });

      panel.add(editBtn);
      panel.add(deleteBtn);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
      currentRow = row;
      return panel;
    }

    @Override
    public Object getCellEditorValue() {
      return "";
    }
  }
}