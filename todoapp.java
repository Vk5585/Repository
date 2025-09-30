import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class todoapp extends JFrame {
    private JTextField taskField;
    private JButton addButton;
    private JPanel taskPanel;
    private ArrayList<JPanel> taskRows;

    public todoapp() {
        taskRows = new ArrayList<>();

        setTitle("ToDo App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel();
        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        topPanel.add(taskField);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        add(scrollPane, BorderLayout.CENTER);

        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskText = taskField.getText();
                if (!taskText.isEmpty()) {
                    addTask(taskText);
                    taskField.setText("");
                }
            }
        });

        setVisible(true);
    }

    private void addTask(String taskText) {
        JPanel row = new JPanel(new BorderLayout());
        JLabel label = new JLabel(taskText);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            taskPanel.remove(row);
            taskRows.remove(row);
            taskPanel.revalidate();
            taskPanel.repaint();
        });

        row.add(label, BorderLayout.CENTER);
        row.add(deleteButton, BorderLayout.EAST);

        taskPanel.add(row);
        taskRows.add(row);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        new todoapp();
    }
}