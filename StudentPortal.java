package ArrayPractice.Home.JAVAFX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Student {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Student(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String[] toArray() {
        return new String[]{String.valueOf(id), name, email, phone};
    }
}

public class StudentPortal extends JFrame {
    private List<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;

    public StudentPortal() {
        // Set up the frame
        setTitle("Student Portal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Add Student", createAddStudentPanel());
        tabbedPane.addTab("View Students", createViewStudentsPanel());

        // Add tabbed pane to the frame
        add(tabbedPane);
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Labels and Text Fields
        JLabel lblId = new JLabel("Student ID:");
        lblId.setBounds(50, 30, 100, 25);
        JTextField txtId = new JTextField();
        txtId.setBounds(150, 30, 200, 25);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 70, 100, 25);
        JTextField txtName = new JTextField();
        txtName.setBounds(150, 70, 200, 25);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 110, 100, 25);
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(150, 110, 200, 25);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(50, 150, 100, 25);
        JTextField txtPhone = new JTextField();
        txtPhone.setBounds(150, 150, 200, 25);

        // Add Button
        JButton btnAdd = new JButton("Add Student");
        btnAdd.setBounds(150, 200, 150, 30);
        btnAdd.setBackground(new Color(34, 139, 34));
        btnAdd.setForeground(Color.WHITE);

        // Add action listener
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String name = txtName.getText();
                    String email = txtEmail.getText();
                    String phone = txtPhone.getText();

                    // Add student to the list
                    students.add(new Student(id, name, email, phone));
                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                    
                    // Clear fields
                    txtId.setText("");
                    txtName.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");

                    // Update table
                    updateTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid data.");
                }
            }
        });

        // Add components to the panel
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPhone);
        panel.add(txtPhone);
        panel.add(btnAdd);

        return panel;
    }

    private JPanel createViewStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(student.toArray());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentPortal portal = new StudentPortal();
            portal.setVisible(true);
        });
    }
}

