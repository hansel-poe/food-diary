package ui;

import model.FoodDiary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FoodDiaryUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private FoodDiary foodDiary;
    private JPanel firstScreen;
    private JLabel appTitle;

    public FoodDiaryUI() {
        //creates new Food diary app
        foodDiary = new FoodDiary();

        //displays title on top of the window

        appTitle = new JLabel();
        appTitle.setText("FoodDiary");
        appTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
        appTitle.setVisible(true);

        //creates first screen of the app
        firstScreen = new JPanel();
        firstScreen.setLayout(new BoxLayout(firstScreen, BoxLayout.PAGE_AXIS));

        JLabel label1 = new JLabel();
        label1.setText("Welcome to FoodDiary, would you like to start a new Diary or load an existing one?");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setPreferredSize(new Dimension(200, 100));
        label1.setFont(new Font("Helvetica", Font.PLAIN, 15));
        label1.setHorizontalAlignment(JLabel.CENTER);

        JButton newDiary = new JButton();
        newDiary.setText("New");
        newDiary.setAlignmentX(Component.CENTER_ALIGNMENT);
        newDiary.setPreferredSize(new Dimension(400, 100));
        newDiary.setVerticalAlignment(JButton.CENTER);
        newDiary.setHorizontalAlignment(JButton.CENTER);

        JButton loadDiary = new JButton();
        loadDiary.setText("Load");
        loadDiary.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadDiary.setPreferredSize(new Dimension(200, 100));
        loadDiary.setVerticalAlignment(JButton.CENTER);
        loadDiary.setHorizontalAlignment(JButton.CENTER);

        firstScreen.add(label1);
        firstScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        firstScreen.add(newDiary);
        firstScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        firstScreen.add(loadDiary);
        firstScreen.setBackground(Color.GRAY);
        firstScreen.setOpaque(true);

        setTitle("FoodDiary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        this.add(appTitle, BorderLayout.NORTH);
        this.add(firstScreen, BorderLayout.CENTER);
        this.pack();

        this.setVisible(true);
    }
}
