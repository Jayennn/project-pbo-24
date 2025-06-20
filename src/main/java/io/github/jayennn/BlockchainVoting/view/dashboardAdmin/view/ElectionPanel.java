package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class ElectionPanel extends JPanel {
  private JTable electionTable;
  private JTextField searchField;
  private JPanel formPanel;
  private JTextField nameField;
  private JDatePickerImpl startDatePicker, endDatePicker;
  private int editingRow = -1;
  private JButton saveButton, cancelButton;
  private DefaultTableModel model;

  public ElectionPanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    initTopPanel();
    initTablePanel();
    initFormPanel();

    add(new JScrollPane(electionTable), BorderLayout.CENTER);
    add(formPanel, BorderLayout.SOUTH);
  }

  private void initTopPanel() {
    JPanel topPanel = new JPanel(new BorderLayout(10, 10));
    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    topPanel.setBackground(Color.WHITE);

    JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
    leftTop.setOpaque(false);

    JButton filterButton = new JButton("Search");
    searchField = new JTextField(20);
    leftTop.add(filterButton);
    leftTop.add(searchField);

    JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    rightTop.setOpaque(false);
    JButton addButton = new JButton("Add");
    rightTop.add(addButton);

    addButton.addActionListener(e -> showForm("", "", "", -1));

    topPanel.add(leftTop, BorderLayout.WEST);
    topPanel.add(rightTop, BorderLayout.EAST);

    add(topPanel, BorderLayout.NORTH);
  }

  private void initFormPanel() {
    formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    formPanel.setBackground(new Color(245, 245, 245));

    nameField = new JTextField();

    // Initialize Date Pickers
    SqlDateModel startModel = new SqlDateModel();
    SqlDateModel endModel = new SqlDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");

    JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
    JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);

    startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
    endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

    saveButton = new JButton("Save");
    cancelButton = new JButton("Cancel");

    formPanel.add(new JLabel("Election Name:"));
    formPanel.add(nameField);
    formPanel.add(new JLabel("Start Date:"));
    formPanel.add(startDatePicker);
    formPanel.add(new JLabel("End Date:"));
    formPanel.add(endDatePicker);
    formPanel.add(saveButton);
    formPanel.add(cancelButton);

    formPanel.setVisible(false);

    saveButton.addActionListener(e -> {
      String name = nameField.getText().trim();
      java.sql.Date start = (java.sql.Date) startDatePicker.getModel().getValue();
      java.sql.Date end = (java.sql.Date) endDatePicker.getModel().getValue();

      if (name.isEmpty() || start == null || end == null) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (editingRow == -1) {
        model.addRow(new Object[] { name, start.toString(), end.toString(), "" });
      } else {
        model.setValueAt(name, editingRow, 0);
        model.setValueAt(start.toString(), editingRow, 1);
        model.setValueAt(end.toString(), editingRow, 2);
      }

      hideForm();
    });

    cancelButton.addActionListener(e -> hideForm());
  }

  private void showForm(String name, String start, String end, int row) {
    System.out.println("open");
    nameField.setText(name);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    startDatePicker.getModel().setSelected(false);
    endDatePicker.getModel().setSelected(false);

    try {
      if (start != null && !start.trim().isEmpty()) {
        java.util.Date startDate = sdf.parse(start);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        startDatePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
        startDatePicker.getModel().setSelected(true);
      }

      if (end != null && !end.trim().isEmpty()) {
        java.util.Date endDate = sdf.parse(end);
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        endDatePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
        endDatePicker.getModel().setSelected(true);
      }

    } catch (ParseException ex) {
      ex.printStackTrace();
    }

    editingRow = row;
    formPanel.setVisible(true);
    revalidate();
    repaint();
  }

  private void hideForm() {
    formPanel.setVisible(false);
    editingRow = -1;
  }

  private void initTablePanel() {
    String[] columns = { "Election Name", "Start Date", "End Date", "Actions" };
    Object[][] data = { { "Pemilihan Presiden", "2025-03-01", "2025-03-02", "" } };

    model = new DefaultTableModel(data, columns) {
      public boolean isCellEditable(int row, int column) {
        return column == 3;
      }
    };

    electionTable = new JTable(model);
    electionTable.setRowHeight(30);

    electionTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
    electionTable.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

    JScrollPane scrollPane = new JScrollPane(electionTable);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(scrollPane, BorderLayout.CENTER);
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

  class ButtonRenderer extends JPanel implements TableCellRenderer {
    public ButtonRenderer() {
      setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {
      removeAll();
      add(createButton("Edit"));
      add(createButton("Delete"));
      return this;
    }

    private JButton createButton(String text) {
      JButton btn = new JButton(text);
      btn.setMargin(new Insets(2, 4, 2, 4));
      btn.setFocusable(false);
      btn.setBackground(Color.WHITE);
      return btn;
    }
  }

  class ButtonEditor extends DefaultCellEditor {
    private int currentRow = -1;
    protected JPanel panel;
    protected JButton btnEdit, btnDelete;

    public ButtonEditor(JCheckBox checkBox) {
      super(checkBox);
      panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
      btnEdit = createButton("Edit");
      btnDelete = createButton("Delete");

      panel.add(btnEdit);
      panel.add(btnDelete);

      btnEdit.addActionListener(e -> {
        DefaultTableModel model = (DefaultTableModel) electionTable.getModel();
        String name = (String) model.getValueAt(currentRow, 0);
        String start = model.getValueAt(currentRow, 1).toString();
        String end = model.getValueAt(currentRow, 2).toString();
        showForm(name, start, end, currentRow);
      });

      btnDelete.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data?", "Konfirmasi",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          if (electionTable.isEditing()) {
            electionTable.getCellEditor().stopCellEditing();
          }
          ((DefaultTableModel) electionTable.getModel()).removeRow(currentRow);
        }
      });
    }

    private JButton createButton(String text) {
      JButton btn = new JButton(text);
      btn.setMargin(new Insets(2, 4, 2, 4));
      btn.setFocusable(false);
      btn.setBackground(Color.WHITE);
      return btn;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      currentRow = row;
      return panel;
    }

    @Override
    public Object getCellEditorValue() {
      return "";
    }
  }
}
