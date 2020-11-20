package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class FacultyFrame extends JFrame{
    // data variables
    protected JTable attendanceTable;// table

    private JLabel facultyName;
    private JLabel className;
    private JButton updatebtn;
    private GridBagConstraints gbc;
    private JTextField present;
    private JTextField absent;// late
    private JTextField excused;
    private JTextField unexcused;
    private String[] columnHeader;
    private JScrollPane tablepane;

    //models
    private ListSelectionModel listSelectionModel;
    private DefaultTableModel tableModel;


    protected int index;
    private JButton incAttendance;
    private Font mainFont;
    protected String[] months;


    protected JComboBox monthSelector;
    protected Professor professors;

    // menu variable
    JMenuBar menuBar;
    JMenu attendanceMenu, profileMenu;
    JMenuItem inc_ab, dec_attn, log_out, dec_ab;

    FacultyFrame(String username) {
        // initializing values and objects
        super("details");
        try {
            professors = new Professor(StudentAttendance.subjects[0],StudentAttendance.fileNames[1]);
        } catch (Exception e) {
            System.out.println(e);
        }
        mainFont = new Font("Helvetica",Font.BOLD,20);
        columnHeader = new String[]{"RollNo", "Name", "Present", "Late", "Excused","Unexcused", "Attendance percentage"};

        facultyName = new JLabel(username);
        facultyName.setBorder(new EmptyBorder(0,0,10,0));
        facultyName.setFont(mainFont);
        className = new JLabel("BE CSE G2");
        className.setBorder(new EmptyBorder(0,0,10,0));
        className.setFont(mainFont);
        updatebtn = new JButton("update");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gbc = new GridBagConstraints();
        present = new JTextField();
        absent = new JTextField();
        excused = new JTextField();
        unexcused = new JTextField();
        incAttendance = new JButton("increase attendance");
        monthSelector = new JComboBox(Student.fileNames);
        monthSelector.setSelectedIndex(1);


        attendanceTable = new JTable();// constructor of creating a table
        initTable(attendanceTable);// initialize table

        attendanceTable.setDefaultRenderer(Object.class, new CustomTableRenderer(6));// to set the renderer

        // models
        tablepane = new JScrollPane(attendanceTable);
        listSelectionModel = attendanceTable.getSelectionModel();// creating listener to listen to table selections
        listSelectionModel.addListSelectionListener(e -> {
            if(!listSelectionModel.isSelectionEmpty()) {
                index = listSelectionModel.getMinSelectionIndex();
                present.setText(attendanceTable.getValueAt(index, 2).toString());
                absent.setText(attendanceTable.getValueAt(index, 3).toString());
                excused.setText(attendanceTable.getValueAt(index, 4).toString());
                unexcused.setText(attendanceTable.getValueAt(index, 5).toString());
            }
        });

        //model for month selector
        monthSelector.addItemListener(e -> {
            int index = monthSelector.getSelectedIndex();
            try {
                professors = new Professor(StudentAttendance.subjects[0],StudentAttendance.fileNames[index]);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /* should be called to create and show the frame*/
    protected void readyFrame() {
        getFrameItems();
        addMenu();
        setNorthPanel();
        setMinimumSize(new Dimension(1100, 650));
        setVisible(true);
    }

    // this places all the components in the frame
    private void getFrameItems() {

        setLayout(new BorderLayout());

        add(tablepane, BorderLayout.CENTER);
        add(getWestPanel(),BorderLayout.WEST);

        // button listeners
        updatebtn.addActionListener(e -> {
            String rollNo = attendanceTable.getValueAt(index,0).toString();
            try {
                // modify in file
                professors.modify(Integer.parseInt(present.getText()),rollNo,'P');
                professors.modify(Integer.parseInt(absent.getText()),rollNo,'L');
                professors.modify(Integer.parseInt(excused.getText()),rollNo,'E');
                professors.modify(Integer.parseInt(unexcused.getText()),rollNo,'U');
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            present.setText("");
            absent.setText("");
            excused.setText("");
            unexcused.setText("");
        });


        incAttendance.addActionListener(e -> {
            try {
                professors.modifyall(1,'P',1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //attendanceTable.repaint();
            refreshTable(attendanceTable);
        });

    }

    protected void setNorthPanel() {
        // sets the north pane of the root pane
        JPanel northPanel = new JPanel();
        northPanel.add(monthSelector);
        add(northPanel, BorderLayout.NORTH);
    }


    // sets the components in the west pane
    private JPanel getWestPanel() {
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBorder(new EmptyBorder(10,10,10,20));
        //detailPanel.setBackground(Color.lightGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(new JLabel("Name : "), gbc);
        gbc.gridy = 1;
        detailPanel.add(facultyName, gbc);
        gbc.gridy = 2;
        detailPanel.add(new JLabel("Class :"), gbc);
        gbc.gridy = 3;
        detailPanel.add(className, gbc);
        gbc.gridy = 4;
        detailPanel.add(new JLabel("\n"), gbc);
        gbc.gridy = 6;
        detailPanel.add(new JLabel("present :"), gbc);
        gbc.gridy = 7;
        detailPanel.add(present, gbc);
        gbc.gridy = 8;
        detailPanel.add(new JLabel("Late :"), gbc);
        gbc.gridy = 9;
        detailPanel.add(absent, gbc);
        gbc.gridy = 10;
        detailPanel.add(new JLabel("Excused :"), gbc);
        gbc.gridy = 11;
        detailPanel.add(excused, gbc);
        gbc.gridy = 12;
        detailPanel.add(new JLabel("Unexcused :"), gbc);
        gbc.gridy = 13;
        detailPanel.add(unexcused, gbc);
        gbc.gridy = 14;
        detailPanel.add(updatebtn, gbc);
        gbc.gridy = 15;
        detailPanel.add(new JLabel(" "), gbc);
        gbc.gridy = 16;
        detailPanel.add(incAttendance, gbc);

        return detailPanel;
    }

    // adds menu bar to the frame
    public void addMenu() {

        menuBar = new JMenuBar();
        attendanceMenu = new JMenu("attendance");
        profileMenu = new JMenu("profile");
        log_out = new JMenuItem("log out");
        dec_attn = new JMenuItem("decrease present by 1");
        inc_ab = new JMenuItem("increase late by 1");
        dec_ab = new JMenuItem("decrease late by 1");

        // add listeners
        log_out.addActionListener(e -> {
            System.exit(1);
        });

        dec_attn.addActionListener(e -> {
            try {
                professors.modifyall(1,'P',2);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        inc_ab.addActionListener(e-> {
            try {
                professors.modifyall(1,'L',1);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        dec_ab.addActionListener(e-> {
            try {
                professors.modifyall(1,'L',2);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        profileMenu.add(log_out);
        attendanceMenu.add(dec_attn);
        attendanceMenu.add(inc_ab);
        attendanceMenu.add(dec_ab);
        menuBar.add(profileMenu);
        menuBar.add(attendanceMenu);
        setJMenuBar(menuBar);

    }

    // initiaizes the values for table using default table model
    private void initTable(JTable table) {
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setColumnIdentifiers(columnHeader);
        LinkedList ll;
        try {
            ll = professors.getsubjectattendance();
            Object[] row = new Object[columnHeader.length];
            for (Object o : ll) {
                row[0] = ((AttendanceData) o).getStudrno();
                row[1] = ((AttendanceData) o).getStudname();
                row[2] = ((AttendanceData) o).getPresent();
                row[3] = ((AttendanceData) o).getLate();
                row[4] = ((AttendanceData) o).getExcused();
                row[5] = ((AttendanceData) o).getUnexcused();
                row[6] = ((AttendanceData) o).getPercentage();
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // used to refresh the table after any updation
    protected void refreshTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }
        initTable(table);
    }

}

// custom table renderer to change colour in table if low percentage (< 75%)
class CustomTableRenderer extends DefaultTableCellRenderer {
    int col;
    CustomTableRenderer(int col) { this.col = col;}
    @Override
    public Component getTableCellRendererComponent(JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        double percentage = Double.parseDouble(table.getValueAt(row, col).toString());
        if(percentage < 75) {
            c.setForeground(Color.red);
            c.setFont(new Font("Dialog", Font.BOLD, 12));
        } else {
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Dialog", Font.PLAIN, 12));
        }
        return c;
    }

}
