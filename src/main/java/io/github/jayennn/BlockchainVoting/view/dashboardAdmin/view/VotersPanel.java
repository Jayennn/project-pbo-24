package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class VotersPanel  extends JPanel {
    private JTable votersTable;
    private JTextField searchField;
    private JPanel formPanel;
    private JTextField nimField;
    private JTextField nameField;
    private JTextField titleField;
    private JButton saveButton, cancelButton;
    private int editingRow = -1;
    private DefaultTableModel model;

    public VotersPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initTopPanel();
        initTablePanel();
        initFormPanel();
        add(formPanel, BorderLayout.SOUTH);
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTop.setOpaque(false);
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        leftTop.add(searchField);
        leftTop.add(searchButton);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTop.setOpaque(false);
        JButton addButton = new JButton ("+ Add Voter");
        rightTop.add(addButton);

        addButton.addActionListener(e -> showForm("", "", "", -1));

        topPanel.add(leftTop, BorderLayout.WEST);
        topPanel.add(rightTop, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
    }

    private void initTablePanel() {
        String[] columnNames = {"Nama", "NIM", "Title", "Action"};
        Object [][] data = {
            {"Kiana Kaslana", "11241099", "Mahasiswa", ""}
        };

        model = new DefaultTableModel(data, columnNames) {
        @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        votersTable = new JTable (model);
        votersTable.setRowHeight(40);
        votersTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        votersTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(votersTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initFormPanel() {
        formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));

        nameField = new JTextField(10);
        nimField = new JTextField(10);
        titleField = new JTextField(10);
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("NIM:"));
        formPanel.add(nimField);
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        formPanel.setVisible(false);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String title = titleField.getText();

            if (name.trim().isEmpty() || nim.trim().isEmpty() || title.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua Field Harus diisi.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!nim.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "NIM harus 8 digit angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                if (i != editingRow && model.getValueAt(i, 1).toString().equals(nim)) {
                    JOptionPane.showMessageDialog(this, "NIM sudah terdaftar", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (editingRow == -1) {
                model.addRow(new Object[]{name, nim, title, ""});
            } else {
                model.setValueAt(name, editingRow, 0);
                model.setValueAt(nim, editingRow, 1);
                model.setValueAt(title, editingRow, 2);
            }

            hideForm();
        });

        cancelButton.addActionListener(e -> hideForm());
    }

    private void showForm(String name, String nim, String title, int row) {
        nameField.setText(name);
        nimField.setText(nim);
        titleField.setText(title);
        editingRow = row;
        formPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void hideForm() {
        formPanel.setVisible(false);
        nameField.setText("");
        nimField.setText("");
        titleField.setText("");
        editingRow = -1;
    }

    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
            setBackground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
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
                String name = (String) model.getValueAt(votersTable.getSelectedRow(), 0);
                String nim = (String) model.getValueAt(votersTable.getSelectedRow(), 1);
                String title = (String) model.getValueAt(votersTable.getSelectedRow(), 2);
                showForm(name, nim, title, votersTable.getSelectedRow());
            });

            btnDelete.addActionListener(e -> {
                fireEditingStopped();
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda Yakin ingin Menghapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.removeRow(votersTable.getSelectedRow());
                }
            });
        }

        private JButton createButton(String text) {
            JButton btn = new JButton (text);
            btn.setMargin(new Insets(2, 4, 2, 4));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);
            return btn;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}
