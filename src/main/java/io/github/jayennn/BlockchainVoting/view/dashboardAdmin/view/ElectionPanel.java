package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import io.github.jayennn.BlockchainVoting.controller.admin.ElectionController;
import io.github.jayennn.BlockchainVoting.entity.Election;

public class ElectionPanel extends JPanel {
  private final ElectionController electionController;

  private JTable table;
  private DefaultTableModel model;

  private JTextField searchField;
  private JPanel formPanel;
  private JTextField electionNameInput;
  private JDatePickerImpl startDatePicker, endDatePicker;

  private TableModelListener tableModelListener;
  private int editingRow = -1;

  public ElectionPanel(ElectionController electionController) {
    this.electionController = electionController;
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
    searchButton.addActionListener(e -> searchElections());
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Action buttons
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    actionPanel.setBackground(Color.WHITE);
    JButton addButton = new JButton("Add");
    addButton.setPreferredSize(new Dimension(180, 30));

    LocalDate startDate = LocalDate.of(2025, 6, 1);
    LocalDate endDate = LocalDate.of(2025, 6, 30);
    addButton.addActionListener(e -> showForm("", startDate, endDate, -1));
    actionPanel.add(addButton);

    topPanel.add(searchPanel, BorderLayout.WEST);
    topPanel.add(actionPanel, BorderLayout.EAST);

    add(topPanel, BorderLayout.NORTH);
  }

  private void initTable() {
    String[] columns = { "Status", "ID", "title", "Start Date", "End Date", "Actions" };

    model = new DefaultTableModel(columns, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 0 || column == 5;
      }

      @Override
      public Class<?> getColumnClass(int columnIndex) {
        // Kolom pertama (index 0) adalah Boolean → akan jadi checkbox
        if (columnIndex == 0)
          return Boolean.class;
        return String.class;
      }
    };

    tableModelListener = e -> {
      int row = e.getFirstRow();
      int column = e.getColumn();
      if (row >= 0 && column == 0) {
        String id = model.getValueAt(row, 1).toString();
        Boolean newValue = (Boolean) model.getValueAt(row, column);
        electionController.updateStatus(UUID.fromString(id), newValue);
        refreshTable();
      }
    };

    model.addTableModelListener(tableModelListener);

    table = new JTable(model);
    table.setRowHeight(35);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setShowGrid(false);

    TableColumn actionColumn = table.getColumnModel().getColumn(5);
    actionColumn.setCellRenderer(new ActionButtonRenderer());
    actionColumn.setCellEditor(new ActionButtonEditor(new JCheckBox()));
    actionColumn.setPreferredWidth(120);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(scrollPane, BorderLayout.CENTER);
  }

  private void initFormPanel() {
    formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Election Form"),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    formPanel.setBackground(new Color(245, 245, 245));
    formPanel.setVisible(false);

    // Election Name field
    JPanel electionNamePanel = new JPanel(new BorderLayout(5, 5));
    electionNamePanel.setBackground(new Color(245, 245, 245));
    electionNamePanel.add(new JLabel("Title:"), BorderLayout.NORTH);
    electionNameInput = new JTextField();
    electionNameInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    electionNamePanel.add(electionNameInput, BorderLayout.CENTER);

    // Shared properties for date pickers
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");

    // Start Date field
    UtilDateModel startDateModel = new UtilDateModel();
    startDateModel.setDate(1990, 8, 24);
    startDateModel.setSelected(true);
    JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel, p);
    startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

    JPanel startDateFieldPanel = new JPanel(new BorderLayout(5, 5));
    startDateFieldPanel.setBackground(new Color(245, 245, 245));
    startDateFieldPanel.add(new JLabel("Start Date:"), BorderLayout.NORTH);
    startDateFieldPanel.add(startDatePicker, BorderLayout.CENTER);

    // End Date field
    UtilDateModel endDateModel = new UtilDateModel();
    endDateModel.setDate(1990, 8, 24);
    endDateModel.setSelected(true);
    JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel, p);
    endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

    JPanel endDateFieldPanel = new JPanel(new BorderLayout(5, 5));
    endDateFieldPanel.setBackground(new Color(245, 245, 245));
    endDateFieldPanel.add(new JLabel("End Date:"), BorderLayout.NORTH);
    endDateFieldPanel.add(endDatePicker, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    buttonPanel.setBackground(new Color(245, 245, 245));
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    saveButton.addActionListener(e -> onSubmit());
    cancelButton.addActionListener(e -> hideForm());

    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);

    // Tambahkan ke form panel
    formPanel.add(electionNamePanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(startDateFieldPanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(endDateFieldPanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(buttonPanel);

    add(formPanel, BorderLayout.SOUTH);
  }

  private void searchElections() {
    String searchTerm = searchField.getText().trim();
    if (searchTerm.isEmpty()) {
      refreshTable();
      return;
    }

    List<Election> elections = electionController.getElections();

    model.removeTableModelListener(tableModelListener);
    model.setRowCount(0);

    for (Election election : elections) {
      if (election.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
        model.addRow(new Object[] {
            election.isActive(),
            election.getUUID(),
            election.getTitle(),
            election.getDateStart(),
            election.getDateEnd()
        });
      }
    }

    model.addTableModelListener(tableModelListener);
  }

  private void refreshTable() {
    SwingUtilities.invokeLater(() -> {
      model.setRowCount(0);

      List<Election> elections = electionController.getElections();

      for (Election election : elections) {

        model.addRow(new Object[] {
            election.isActive(),
            election.getUUID(),
            election.getTitle(),
            election.getDateStart(),
            election.getDateEnd()
        });
      }

      model.addTableModelListener(tableModelListener);
    });
  }

  private void showForm(String title, LocalDate startDate, LocalDate endDate, int row) {
    electionNameInput.setText(title);
    editingRow = row;

    // Set start date ke date picker
    if (startDate != null) {
      startDatePicker.getModel().setDate(
          startDate.getYear(), startDate.getMonthValue() - 1, startDate.getDayOfMonth());
      startDatePicker.getModel().setSelected(true);
    }

    // Set end date ke date picker
    if (endDate != null) {
      endDatePicker.getModel().setDate(
          endDate.getYear(), endDate.getMonthValue() - 1, endDate.getDayOfMonth());
      endDatePicker.getModel().setSelected(true);
    }

    formPanel.setVisible(true);
    revalidate();
    repaint();
  }

  private void hideForm() {
    electionNameInput.setText("");
    startDatePicker.getModel().setSelected(false);
    endDatePicker.getModel().setSelected(false);
    formPanel.setVisible(false);
    revalidate();
    repaint();
  }

  private void onSubmit() {
    String electionName = electionNameInput.getText().trim();
    Date startRaw = (Date) startDatePicker.getModel().getValue();
    Date endRaw = (Date) endDatePicker.getModel().getValue();

    if (startRaw == null || endRaw == null) {
      JOptionPane.showMessageDialog(this, "Please select valid dates", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    LocalDate startDate = startRaw.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    LocalDate endDate = endRaw.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

    if (electionName.isEmpty() || startDate == null || endDate == null) {
      JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (endDate.isBefore(startDate)) {
      JOptionPane.showMessageDialog(this, "End date cannot be before start date.", "Invalid Date",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("Start Date: " + dtf.format(startDate));
    System.out.println("End Date: " + dtf.format(endDate));

    if (editingRow == -1) {
      // Add new
      electionController.addElection(electionName, startDate, endDate, false);
    } else {
      // Update existing
      String id = model.getValueAt(editingRow, 1).toString();
      electionController.editElection(UUID.fromString(id), electionName, startDate, endDate);
      JOptionPane.showMessageDialog(this,
          "Candidate updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    hideForm();
    refreshTable();
  }

  class ActionButtonRenderer extends JPanel implements TableCellRenderer {
    private final JButton editBtn;
    private final JButton deleteBtn;

    public ActionButtonRenderer() {
      setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
      setBackground(Color.WHITE);

      editBtn = new JButton("Edit");
      editBtn.setBackground(new Color(70, 130, 180));
      editBtn.setForeground(Color.WHITE);
      editBtn.setFocusPainted(false);

      deleteBtn = new JButton("Delete");
      deleteBtn.setBackground(new Color(220, 53, 69));
      deleteBtn.setForeground(Color.WHITE);
      deleteBtn.setFocusPainted(false);

      add(editBtn);
      add(deleteBtn);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      return this; // Tidak perlu buat ulang komponen
    }
  }

  class ActionButtonEditor extends DefaultCellEditor {
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

        String title = model.getValueAt(currentRow, 2).toString();
        String startDateStr = model.getValueAt(currentRow, 3).toString();
        String endDateStr = model.getValueAt(currentRow, 4).toString();

        LocalDate startDate = LocalDate.parse(startDateStr); // Format: yyyy-MM-dd
        LocalDate endDate = LocalDate.parse(endDateStr);

        showForm(title, startDate, endDate, currentRow);
        fireEditingStopped();
      });

      deleteBtn.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
            panel,
            "Are you sure you want to delete this election?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
          String id = model.getValueAt(currentRow, 1).toString();
          electionController.deleteElection(UUID.fromString(id));
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

  class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object stringToValue(String text) throws ParseException {
      return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) {
      if (value != null) {
        Calendar cal = (Calendar) value;
        return dateFormatter.format(cal.getTime());
      }
      return "";
    }
  }
}