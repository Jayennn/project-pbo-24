package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import io.github.jayennn.BlockchainVoting.controller.admin.VoterController;
import io.github.jayennn.BlockchainVoting.entity.Gender;
import io.github.jayennn.BlockchainVoting.entity.Voter;

public class VotersPanel extends JPanel {
  private final VoterController voterController;

  private JTable table;
  private DefaultTableModel model;

  private JTextField searchField;
  private JPanel formPanel;

  private JTextField nameField;
  private JTextField studentIdField;
  private JComboBox<Gender> genderComboBox;
  private JDatePickerImpl dateofbirthPicker;

  private int editingRow = -1;

  public VotersPanel(VoterController voterController) {
    this.voterController = voterController;
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
    // searchButton.addActionListener(e -> searchElections());
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Action buttons
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    actionPanel.setBackground(Color.WHITE);
    JButton addButton = new JButton("Add");
    addButton.setPreferredSize(new Dimension(180, 30));

    addButton.addActionListener(e -> {
      studentIdField.setEnabled(true);
      LocalDate dateofbirth = LocalDate.of(2006, 1, 1);
      showForm("", "", dateofbirth, Gender.FEMALE, -1);
    });
    actionPanel.add(addButton);

    topPanel.add(searchPanel, BorderLayout.WEST);
    topPanel.add(actionPanel, BorderLayout.EAST);

    add(topPanel, BorderLayout.NORTH);
  }

  private void initTable() {
    String[] columnNames = { "Student ID", "Name", "Date of Birth", "Gender", "Actions" };

    model = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 4;
      }
    };

    table = new JTable(model);
    table.setRowHeight(35);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setShowGrid(false);

    TableColumn actionColumn = table.getColumnModel().getColumn(4);
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

    // Voter Student Id Field
    JPanel studentIdPanel = new JPanel(new BorderLayout(5, 5));
    studentIdPanel.setBackground(new Color(245, 245, 245));
    studentIdPanel.add(new JLabel("Student ID:"), BorderLayout.NORTH);
    studentIdField = new JTextField();
    studentIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    studentIdPanel.add(studentIdField, BorderLayout.CENTER);

    // Voter Name Field
    JPanel namePanel = new JPanel(new BorderLayout(5, 5));
    namePanel.setBackground(new Color(245, 245, 245));
    namePanel.add(new JLabel("Name:"), BorderLayout.NORTH);
    nameField = new JTextField();
    nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    namePanel.add(nameField, BorderLayout.CENTER);

    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");

    // Voter dob field
    UtilDateModel dateofbirthModel = new UtilDateModel();
    dateofbirthModel.setDate(2006, 1, 1);
    dateofbirthModel.setSelected(true);
    JDatePanelImpl dateofbirthPanel = new JDatePanelImpl(dateofbirthModel, p);
    dateofbirthPicker = new JDatePickerImpl(dateofbirthPanel, new DateLabelFormatter());

    JPanel dateofbirthFieldPanel = new JPanel(new BorderLayout(5, 5));
    dateofbirthFieldPanel.setBackground(new Color(245, 245, 245));
    dateofbirthFieldPanel.add(new JLabel("Date of Birth:"), BorderLayout.NORTH);
    dateofbirthFieldPanel.add(dateofbirthPicker, BorderLayout.CENTER);

    // Gender Field
    JPanel genderPanel = new JPanel(new BorderLayout(5, 5));
    genderPanel.setBackground(new Color(245, 245, 245));
    genderPanel.add(new JLabel("Gender:"), BorderLayout.NORTH);
    genderComboBox = new JComboBox<>(Gender.values());

    genderComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    genderPanel.add(genderComboBox, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    buttonPanel.setBackground(new Color(245, 245, 245));
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    saveButton.addActionListener(e -> onSubmit());
    cancelButton.addActionListener(e -> hideForm());

    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);

    formPanel.add(studentIdPanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(namePanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(genderPanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(dateofbirthFieldPanel);
    formPanel.add(Box.createVerticalStrut(10));
    formPanel.add(buttonPanel);

    formPanel.setVisible(false);

    add(formPanel, BorderLayout.SOUTH);
  }

  private void refreshTable() {
    SwingUtilities.invokeLater(() -> {
      model.setRowCount(0);

      List<Voter> voters = voterController.getVoters();

      for (Voter voter : voters) {

        model.addRow(new Object[] {
            voter.getId(),
            voter.getName(),
            voter.getDateOfBirth(),
            voter.getGender()
        });
      }

    });
  }

  private void showForm(String studentID, String name, LocalDate dateofbirth, Gender gender, int row) {
    studentIdField.setText(studentID);
    nameField.setText(name);
    genderComboBox.setSelectedItem(gender);
    editingRow = row;

    if (dateofbirth != null) {
      dateofbirthPicker.getModel().setDate(dateofbirth.getYear(), dateofbirth.getMonthValue() - 1,
          dateofbirth.getDayOfMonth());
      dateofbirthPicker.getModel().setSelected(true);
    }

    formPanel.setVisible(true);
    revalidate();
    repaint();
  }

  private void hideForm() {
    nameField.setText("");
    studentIdField.setText("");
    dateofbirthPicker.getModel().setSelected(false);
    formPanel.setVisible(false);
    revalidate();
    repaint();
  }

  private void onSubmit() {
    String studentId = studentIdField.getText().trim();
    String name = nameField.getText().trim();
    Gender gender = (Gender) genderComboBox.getSelectedItem();

    Date dateofbirthRaw = (Date) dateofbirthPicker.getModel().getValue();

    if (dateofbirthRaw == null) {
      JOptionPane.showMessageDialog(this, "Please select valid dates", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    LocalDate dateofbirth = dateofbirthRaw.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

    if (name.isEmpty() || dateofbirth == null || studentId.isEmpty() || gender == null) {
      JOptionPane.showMessageDialog(this,
          "Please fill in all fields and select an election.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (!studentId.matches("\\d{8}")) {
      JOptionPane.showMessageDialog(this, "NIM harus 8 digit angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
      return;
    }

    for (int i = 0; i < model.getRowCount(); i++) {
      if (i != editingRow && model.getValueAt(i, 1).toString().equals(studentId)) {
        JOptionPane.showMessageDialog(this, "NIM sudah terdaftar", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
      }
    }

    if (editingRow == -1) {
      voterController.addVoter(studentId, name, gender, dateofbirth);
    } else {
      System.out.println(studentId);
      voterController.editVoter(studentId, name, gender, dateofbirth);
      JOptionPane.showMessageDialog(this,
          "Candidate updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    hideForm();
    refreshTable();
  }

  public void reset() {
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

        String studentId = model.getValueAt(currentRow, 0).toString();
        String name = model.getValueAt(currentRow, 1).toString();
        String dateofbirthStr = model.getValueAt(currentRow, 2).toString();
        Gender gender = Gender.valueOf(model.getValueAt(currentRow, 3).toString());

        LocalDate dateofbirthDate = LocalDate.parse(dateofbirthStr);

        studentIdField.setEnabled(false);
        showForm(studentId, name, dateofbirthDate, gender, currentRow);
        fireEditingStopped();
      });

      deleteBtn.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
            panel,
            "Are you sure you want to delete this election?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
          String StudentId = model.getValueAt(currentRow, 0).toString();
          voterController.deleteVoter(StudentId);
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
