package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn; 

public class CandidatePanel extends JPanel {
    private JTable candidateTable;
    private JTextField searchField;
    private JPanel formPanel;
    private JTextField nameField;
    private JTextArea visiArea;
    private JTextArea misiArea;
    private int editingRow = -1;
    private JButton saveButton, cancelButton;
    private DefaultTableModel model;

    public CandidatePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initTopPanel();
        initTablePanel();
        initFormPanel();

        add(new JScrollPane(candidateTable), BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    private void initTopPanel(){
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTop.setOpaque(false);

        JButton searchButton = new JButton("Search");
        searchField = new JTextField(20);
        leftTop.add(searchButton);
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
        formPanel = new JPanel(new GridLayout(4,2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));

        nameField = new JTextField();
        visiArea = new JTextArea(3, 10);
        visiArea.setLineWrap(true);
        visiArea.setWrapStyleWord(true);
        JScrollPane visiScrollPane = new JScrollPane(visiArea);

        misiArea = new JTextArea(3, 20);
        misiArea.setLineWrap(true);
        misiArea.setWrapStyleWord(true);
        JScrollPane misiScrollPane = new JScrollPane(misiArea);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        formPanel.add(new JLabel("Candidate Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Visi:"));
        formPanel.add(visiScrollPane);
        formPanel.add(new JLabel("Misi:"));
        formPanel.add(misiScrollPane);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        formPanel.setVisible(false);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String visi = visiArea.getText();
            String misi = misiArea.getText();

            if (name.isEmpty() || visi.isEmpty() || misi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama, Visi dan Misi tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (editingRow == -1) {
                model.addRow(new Object[]{name, visi, misi, ""});
            } else {
                model.setValueAt(name, editingRow, 0);
                model.setValueAt(visi, editingRow, 1);
                model.setValueAt(misi, editingRow, 2);
            }
            hideForm();
        });
        cancelButton.addActionListener(e -> hideForm());
    }

    private void showForm(String name, String visi, String misi, int row) {
        nameField.setText(name);
        visiArea.setText(visi);
        misiArea.setText(misi);
        editingRow = row;
        formPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void hideForm() {
        formPanel.setVisible(false);
        nameField.setText("");
        visiArea.setText("");
        misiArea.setText("");
        editingRow = -1;
        revalidate();
        repaint();
    }

    private void initTablePanel() {
        String[] columns = {"Name", "Visi", "Misi", "Actions"};
        Object[][] data = new  Object[][] {
            {"", "", "", ""}
        };

        model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        candidateTable = new JTable(model);
        candidateTable.setRowHeight(40);

        candidateTable.getColumn("Visi").setCellRenderer(new MultiLineTableCellRenderer ());
        candidateTable.getColumn("Misi").setCellRenderer(new MultiLineTableCellRenderer ());

        candidateTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        candidateTable.getColumn("Actions").setCellEditor((TableCellEditor) new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(candidateTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
            setBackground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(table.getBackground());
            removeAll();
            add(createIconButton("Edit"));
            add(createIconButton("Hapus"));
            return this;
        }

        private JButton createIconButton(String text) {
            JButton button = new JButton(text);
            button.setMargin(new Insets(2, 4, 2, 4));
            button.setFocusable(false);
            button.setBackground(Color.WHITE);
            return button;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private int currentRow = -1;
        protected JPanel panel;
        protected JButton btnEdit, btnDelete;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            panel.setBackground(Color.WHITE);
        
            btnEdit = createButton("Edit");
            btnDelete = createButton("Delete");

            panel.add(btnEdit);
            panel.add(btnDelete);

            btnEdit.addActionListener(e -> {
                fireEditingStopped();
                DefaultTableModel tableModel = (DefaultTableModel) candidateTable.getModel();
                String name = (String) tableModel.getValueAt(currentRow, 0);
                String visi = (String) tableModel.getValueAt(currentRow, 1);
                String misi = (String) tableModel.getValueAt(currentRow, 2);
                showForm(name, visi, misi, currentRow);
            });
            btnDelete.addActionListener(e -> {
                fireEditingStopped();
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda takin ingin Menghapus?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel tableModel = (DefaultTableModel) candidateTable.getModel();
                    tableModel.removeRow(currentRow);
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

class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
    public MultiLineTableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());

        if (isSelected) {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        setFont(table.getFont());
        Insets insets = getInsets();

        int columnWidth = table.getColumnModel().getColumn(column).getWidth();
        int availableWidth = columnWidth - insets.left - insets.right;

        setSize(new Dimension(availableWidth, Short.MAX_VALUE));
        doLayout();

        int preferredHeight = (int) getPreferredSize().getHeight();
        int rowMargin = table.getRowMargin();


        if (table.getRowHeight(row) != preferredHeight + rowMargin) {
            table.setRowHeight(row, preferredHeight + rowMargin);
        }
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
