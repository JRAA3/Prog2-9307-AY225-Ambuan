import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Student Attendance System
 * @author John Rowelle Ambuan
 * @version 1.0
 */
public class AttendanceTracker extends JFrame {

    private JTextField nameField;
    private JTextField courseField;
    private JTextField timeInField;
    private JTextField eSignatureField;

    private JTextArea recordArea;

    public AttendanceTracker() {
        setTitle("Student Attendance System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("UPHSD Student Attendance System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Attendance Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20); // EMPTY on start
        formPanel.add(nameField, gbc);

        // Course
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Course / Year:"), gbc);

        gbc.gridx = 1;
        courseField = new JTextField(20); // EMPTY on start
        formPanel.add(courseField, gbc);

        // Time In (auto)
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Time In:"), gbc);

        gbc.gridx = 1;
        timeInField = new JTextField(20);
        timeInField.setEditable(false);
        formPanel.add(timeInField, gbc);

        // E-Signature (auto)
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("E-Signature:"), gbc);

        gbc.gridx = 1;
        eSignatureField = new JTextField(20);
        eSignatureField.setEditable(false);
        formPanel.add(eSignatureField, gbc);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel();

        JButton generateButton = new JButton("Generate Time & Signature");
        JButton saveButton = new JButton("Save Record");
        JButton viewButton = new JButton("View Records");

        generateButton.addActionListener(e -> generateAttendance());
        saveButton.addActionListener(e -> saveRecord());
        viewButton.addActionListener(e -> viewRecords());

        buttonPanel.add(generateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(viewButton);

        // ===== RECORD AREA =====
        recordArea = new JTextArea(8, 40);
        recordArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recordArea);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Auto-generate time & signature only
        generateAttendance();
    }

    // Generates time and UUID (unchanged)
    private void generateAttendance() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeInField.setText(now.format(formatter));
        eSignatureField.setText(UUID.randomUUID().toString());
    }

    // Saves record
    private void saveRecord() {
        String name = nameField.getText();
        String course = courseField.getText();

        if (name.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Name and Course.");
            return;
        }

        String record =
                "Name: " + name + "\n" +
                "Course: " + course + "\n" +
                "Time In: " + timeInField.getText() + "\n" +
                "E-Signature: " + eSignatureField.getText() + "\n" +
                "------------------------------\n";

        recordArea.append(record);

        JOptionPane.showMessageDialog(this, "Attendance Saved!");
    }

    // View records (already shown in text area)
    private void viewRecords() {
        JOptionPane.showMessageDialog(this, "Scroll down to see saved records.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceTracker());
    }
}
