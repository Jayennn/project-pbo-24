package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
public class ElectionPanel extends JPanel {
    private JTable electionTable;
    private JTextField searchField;
    private JPanel formPanel;
    private JTextField nameField;
    private JDatePickerImpl startdatePicker, endDatePicker;
    private  int editingRow = -1;
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

        JButton filterButton = new JButton("Seacrh");
        searchField = new JTextField(20);
        leftTop.add(filterButton);
        leftTop.add(searchField);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTop.setOpaque(false);
        JButton addButton = new JButton("add");
        rightTop.add(addButton);

        addButton.addActionListener(e -> showForm("", "", "", -1));

        topPanel.add(leftTop, BorderLayout.WEST);
        topPanel.add(rightTop, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

// atur data
    private void initFormPanel() {
        formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));

        nameField = new JTextField();
        
        SqlDateModel startModel = new  SqlDateModel();
        SqlDateModel endModel = new SqlDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        
        startdatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        formPanel.add(new JLabel("Election Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Start Date:"));
        formPanel.add(startdatePicker);
        formPanel.add(new JLabel("End Date:"));
        formPanel.add(endDatePicker);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        formPanel.setVisible(false);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama pemilu tidak boleh kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.sql.Date start = (java.sql.Date) startdatePicker.getModel().getValue();
            java.sql.Date end = (java.sql.Date) endDatePicker.getModel().getValue();

            if (start == null || end == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Mulai dan Akhir harus dipilih", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (start.after(end)) {
                JOptionPane.showMessageDialog(this, "Tangal mulai harus sebelum atau sama dengan tanggal selesai", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (editingRow == -1) {
                model.addRow(new Object[]{name, start.toString(), end.toString(), ""});
            } else {
                model.setValueAt(name, editingRow, 0);
                model.setValueAt(start, editingRow, 1);
                model.setValueAt(end, editingRow, 2);
            }
            hideForm();
        });
        cancelButton.addActionListener(e -> hideForm());
    }

    private void showForm(String name, String start, String end, int row) {
        nameField.setText(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        startdatePicker.getModel().setSelected(false);
        endDatePicker.getModel().setSelected(false);

        try {
            if (start != null && !start.trim().isEmpty()) {
                java.util.Date startDate = sdf.parse(start);
                Calendar startCal = Calendar.getInstance();
                startCal.setTime(startDate);
                startdatePicker.getModel().setDate(
                    startCal.get(Calendar.YEAR),
                    startCal.get(Calendar.MONTH),
                    startCal.get(Calendar.DAY_OF_MONTH)
                );
                startdatePicker.getModel().setSelected(true);
            }

            if (end != null && !end.trim().isEmpty()) {
                java.util.Date endDate = sdf.parse(end);
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(endDate);
                endDatePicker.getModel().setDate(
                    endCal.get(Calendar.YEAR),
                    endCal.get(Calendar.MONTH),
                    endCal.get(Calendar.DAY_OF_MONTH)
                );
                endDatePicker.getModel().setSelected(true);
            }
        } catch (ParseException e) {
            e.printStackTrace();

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
        String[] columns = {"Election Name", "Start Date", "End Date", "Actions"};
        Object[][] data = {{"Pemilihan Presidem", "2025-03-01", "2025-03-02", ""}};
    
        model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        electionTable = new JTable(model);
        electionTable.setRowHeight(30);

        electionTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        electionTable.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane (electionTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(scrollPane, BorderLayout.CENTER);
    }

    class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateformatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateformatter.format(cal.getTime());
            }
            return "";
        }
    }
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer (){
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            removeAll();
            add(createIconButton("Edit"));
            add(createIconButton("Delate"));
            // add(createIconButton("Shared"));
            return this;  
        }

        private JButton createIconButton(String text) {
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
        protected JButton btnEdit, btnDelete; //btnShare;

        public ButtonEditor(JCheckBox checkbox) {
            super(checkbox);
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
            btnEdit = createButton("Edit");
            btnDelete = createButton("Delate");
            // btnShare = createButton("Share");

            panel.add(btnEdit);
            panel.add(btnDelete);
            // panel.add(btnShare);

            btnEdit.addActionListener(e ->{
                DefaultTableModel model = (DefaultTableModel) electionTable.getModel();

                String name = (String) model.getValueAt(currentRow, 0);
                String start = model.getValueAt(currentRow, 1).toString();
                String end = model.getValueAt(currentRow, 2).toString();
                showForm(name, start, end, currentRow);
            });

            btnDelete.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_NO_OPTION) {
                    if (electionTable.isEditing()) {
                        electionTable.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel model = (DefaultTableModel) electionTable.getModel();
                    model.removeRow(currentRow);
                }
            });
            // btnShare.addActionListener(e -> JOptionPane.showMessageDialog(null, "Share clicked"));

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
            currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    
    }
}
