package frame;

import data.Record;
import generator.PasswordGenerator;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MyJFrame extends JFrame {
    private int counter = 1;
    private final List<Record> records = Util.showRecordsFromFile(new File("records.txt"));
    private int rows = records.size() + 2;

    public MyJFrame() {
        super("Password Reminder JFrame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 2 - 250,dimension.height/2 - 150,700,400);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(rows,4));

        JButton addRecordButton = new JButton("Add new record");
        addRecordButton.addActionListener(e -> new AddRecordJFrame(this, container));

        container.add(addRecordButton);
        container.add(new JLabel());
        container.add(new JLabel());
        container.add(new JLabel());

        container.add(new JLabel("№"));
        container.add(new JLabel("Resource"));
        container.add(new JLabel("Login"));
        container.add(new JLabel("Password"));

        for (Record record : records){
            JLabel numberOfRecordLabel = new JLabel();
            numberOfRecordLabel.setText(String.valueOf(counter));
            container.add(numberOfRecordLabel);

            JLabel resourceOfRecordLabel = new JLabel();
            resourceOfRecordLabel.setText(record.getResource());
            container.add(resourceOfRecordLabel);

            JLabel loginOfRecordLabel = new JLabel();
            loginOfRecordLabel.setText(record.getLogin());
            container.add(loginOfRecordLabel);

            JLabel passwordOfRecordLabel = new JLabel();
            passwordOfRecordLabel.setText(record.getPassword());
            container.add(passwordOfRecordLabel);

            counter++;
        }

        this.setVisible(true);
    }
    private class AddRecordJFrame extends JFrame {
        AddRecordJFrame(MyJFrame myJFrame, Container myJFrameContainer){
            super("Add Record JFrame");

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            this.setBounds(dimension.width / 2 - 250,dimension.height/2 - 150,500,350);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(4,2));

            //  Resource
            JLabel resourceLabel = new JLabel("Resource:");
            container.add(resourceLabel);
            JTextField resourceField = new JTextField("");
            container.add(resourceField);

            // Login
            JLabel loginLabel = new JLabel("Login:");
            container.add(loginLabel);
            JTextField loginField = new JTextField("");
            container.add(loginField);

            // Password
            JLabel passwordLabel = new JLabel("Password:");
            container.add(passwordLabel);
            JTextField passwordField = new JTextField("");
            container.add(passwordField);

            JButton generatePasswordButton = new JButton("Generate Password");
            generatePasswordButton.addActionListener(e -> {
                String generatedPassword = PasswordGenerator.generatePassword();
                passwordField.setText(generatedPassword);
            });
            container.add(generatePasswordButton);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                if(!loginField.getText().isEmpty() && !passwordField.getText().isEmpty() && !resourceField.getText().isEmpty()) {
                    String login = loginField.getText();
                    String password = passwordField.getText();
                    String resource = resourceField.getText();

                    Record record = new Record(login, password, resource);
                    Util.addRecord(record);

                    loginField.setText("");
                    passwordField.setText("");
                    resourceField.setText("");

                    myJFrameContainer.setLayout(new GridLayout(++rows, 4));
                    myJFrameContainer.add(new JLabel(String.valueOf(counter)));
                    myJFrameContainer.add(new JLabel(resource));
                    myJFrameContainer.add(new JLabel(login));
                    myJFrameContainer.add(new JLabel(password));

                    myJFrame.revalidate();

                    counter++;
                }
            });
            container.add(saveButton);

            this.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new MyJFrame();
    }
}