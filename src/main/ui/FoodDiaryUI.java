package ui;

import model.FoodDiary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FoodDiaryUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private FoodDiary foodDiary;
    private JLabel appTitle;
    private ProfileUI profileScreen;

    private JPanel startScreen;
    private JPanel mainMenu;
    private JPanel setupScreen;
    private JPanel profile;


    public FoodDiaryUI() {
        //creates new Food diary app
        foodDiary = new FoodDiary();

        //displays title on top of the window
        appTitle = new JLabel();
        appTitle.setText("FoodDiary");
        appTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
        appTitle.setBackground(Color.LIGHT_GRAY);

        //creates start screen of the app
        startScreen = new JPanel();
        startScreen.setLayout(new BoxLayout(startScreen, BoxLayout.PAGE_AXIS));
        startScreen.setBackground(Color.LIGHT_GRAY);
        startScreen.setOpaque(true);

        //creates main menu window
        mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.PAGE_AXIS));
        mainMenu.setBackground(Color.LIGHT_GRAY);
        mainMenu.setOpaque(true);

        profile = new JPanel();

        addStartScreenComponents();
        addMainMenuComponents();

        setTitle("FoodDiary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        this.add(appTitle, BorderLayout.NORTH);
        this.add(startScreen, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void addStartScreenComponents() {
        JLabel startLabel = new JLabel("Welcome to FoodDiary,\n would you like to start a new Diary or load an existing one?");
        startLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JButton newDiary = new JButton("New");
        newDiary.setAlignmentX(Component.CENTER_ALIGNMENT);
        newDiary.setMnemonic(KeyEvent.VK_N);
        newDiary.setActionCommand("New");

        JButton loadDiary = new JButton("Load");
        loadDiary.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadDiary.setMnemonic(KeyEvent.VK_L);
        loadDiary.setActionCommand("Load");

        newDiary.addActionListener(new FirstScreenOptions());
        loadDiary.addActionListener(new FirstScreenOptions());

        startScreen.add(startLabel);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(newDiary);
        startScreen.add(Box.createRigidArea(new Dimension(0, 10)));
        startScreen.add(loadDiary);
    }

    private void setSetupScreen() {
        JLabel setupLabel = new JLabel("Enter the following information");

        JLabel nameLabel = new JLabel("Name:    ");
        JTextField name = new JTextField();

    }
    private void addMainMenuComponents() {
        JLabel menuLabel = new JLabel("Please select an option: ");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JButton addEntry = new JButton("Add Entry");
        addEntry.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEntry.setMnemonic(KeyEvent.VK_A);
        addEntry.setActionCommand("Add Entry");

        JButton viewProfile = new JButton("View Profile");
        viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewProfile.setMnemonic(KeyEvent.VK_P);
        viewProfile.setActionCommand("View Profile");

        JButton viewEntries = new JButton("View Entries");
        viewEntries.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEntries.setMnemonic(KeyEvent.VK_E);
        viewEntries.setActionCommand("View Entries");

        JButton quit = new JButton("Quit");
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setActionCommand("Quit");

        mainMenu.add(menuLabel);
        mainMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenu.add(addEntry);
        mainMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenu.add(viewProfile);
        mainMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenu.add(viewEntries);
        mainMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        mainMenu.add(quit);
        addSexRadioButtons(mainMenu);
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        add(appTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        System.out.println("Panel Changed");
        this.revalidate();
        this.repaint();
    }

    public void addSexRadioButtons(JPanel panel) {
        JLabel sexLabel = new JLabel("Please select your sex: ");

        JRadioButton male = new JRadioButton("Male");
        male.setMnemonic(KeyEvent.VK_M);
        male.setActionCommand("Male");
        male.setSelected(true);

        JRadioButton female = new JRadioButton("Female");
        female.setMnemonic(KeyEvent.VK_F);
        female.setActionCommand("Female");
        female.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        panel.add(male);
        panel.add(female);
    }

    private class FirstScreenOptions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New")) {
                System.out.println("New pressed");
            }

            if (e.getActionCommand().equals("Load")) {
                System.out.println("Load pressed");
            }
            changePanel(mainMenu);
        }
    }

    private class SexOptions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Male")) {
                System.out.println("Male is selected");
            }

            if (e.getActionCommand().equals("Female")) {
                System.out.println("Female is selected");
            }

        }
    }


}

