package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Desktop.Action;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ManageElectionPanel extends JPanel {
  private JTable table;
  private JTextField searchField;

  public ManageElectionPanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    initTopPanel();
    initTable();
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
    // searchButton.addActionListener(e -> searchCandidates());
    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Action buttons
    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    actionPanel.setBackground(Color.WHITE);
    JButton addButton = new JButton("Add");
    addButton.setPreferredSize(new Dimension(180, 30));
    // addButton.addActionListener(e -> showForm("", "", "", -1, null));
    actionPanel.add(addButton);

    topPanel.add(searchPanel, BorderLayout.WEST);
    topPanel.add(actionPanel, BorderLayout.EAST);

    add(topPanel, BorderLayout.NORTH);
  }

  private void initTable() {
    String[] columns = { "", "Election Name", "Title", "Start date", "end Date", "isActive", "actions" };

    Object[][] data = {
        { false, "Elections 2025", "Prisiden", "2025-03-01", "2025-03-02", "Active", "" }
    };

    DefaultTableModel model = new DefaultTableModel(data, columns) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 0 || column == 6;
      }

    };

    table = new JTable(model);
    table.setRowHeight(35);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setIntercellSpacing(new Dimension(0, 0));
    table.setShowGrid(false);

    table.getColumn("actions").setCellRenderer(new ActionButtonRenderer());
    table.getColumn("actions").setCellEditor(new ActionButtonEditor(new JCheckBox()));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(scrollPane, BorderLayout.CENTER);
  }

  private void showForm(){
    
  }

  class ActionButtonRenderer extends JPanel implements TableCellRenderer {
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

      editBtn.addActionListener(e -> System.out.println("click"));
      deleteBtn.addActionListener(e -> System.out.println("click"));

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