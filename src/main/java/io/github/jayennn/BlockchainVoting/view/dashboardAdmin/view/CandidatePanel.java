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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
public class CandidatePanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private JButton addButton;

    public CandidatePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchField = new JTextField("search");
        addButton = new JButton("+ add");
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        String[] columnNames = {"Name", "Visi", "Misi", "Actions"};
        Object[][] data = {
            {"Nama Kandidat", "Visi", "Misi", "Edit"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3;
        }
    };

    table = new JTable(model);
    table.setRowHeight(40);

    table.getColumn("Actions").setCellRenderer(new ButtonRenderer());
    table.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

    JScrollPane scrollPane = new JScrollPane(table);

    add(topPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);

    }
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
            setBackground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            removeAll();
            add(createIconButton("✏️"));
            add(createIconButton("🗑️"));
            //add(createIconButton("🔗"));
            return this;
        }
    
        private JButton createIconButton(String text) {
            JButton button = new JButton(text);
            button.setMargin(new Insets(2, 4, 2, 4));
            button.setFocusable((false));
            button.setBackground(Color.WHITE);
            return button;
        }
    }
    class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton btnEdit, btnDelete;//, btnShare;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            btnEdit = new JButton("✏️");
            btnDelete = new JButton("🗑️");
            //btnShare = new JButton("🔗");

            for (JButton btn : new JButton[]{btnEdit, btnDelete, }) {  //btnShare
                btn.setMargin(new Insets(2, 4, 2,4));
                btn.setFocusable(false);
                btn.setBackground(Color.WHITE);
                panel.add(btn);
            }
            btnEdit.addActionListener(e -> JOptionPane.showMessageDialog(null, "edit clicked"));
            btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(null, "delate clicked"));
            //btnShare.addActionListener(e -> JOptionPane.showMessageDialog(null, "share clicked"));
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
