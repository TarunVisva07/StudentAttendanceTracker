package com.company;

import javax.swing.*;
import java.awt.*;

public class TutorFrame extends FacultyFrame {
    private JComboBox subjectSelector;


    TutorFrame(String username) {
        super(username);
        subjectSelector = new JComboBox(StudentAttendance.subjects);
        subjectSelector.setSelectedIndex(0);
        index = monthSelector.getSelectedIndex();

        // an option for selecting month is added
        monthSelector.addItemListener(e -> {
            int index = monthSelector.getSelectedIndex();
            try {
                professors = new Professor(StudentAttendance.subjects[subjectSelector.getSelectedIndex()],
                        StudentAttendance.fileNames[index]);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // setting the listener for subjectSelector JComboBox
        subjectSelector.addActionListener(e -> {
            try {
                professors = new Professor(StudentAttendance.subjects[subjectSelector.getSelectedIndex()],
                        StudentAttendance.fileNames[monthSelector.getSelectedIndex()]);
                refreshTable(attendanceTable);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    protected void setNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.add(monthSelector);
        northPanel.add(subjectSelector);// subject selector is added for the tutor
        add(northPanel, BorderLayout.NORTH);
    }

}
