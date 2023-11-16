package ui;

import model.*;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FoodDiaryUI extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private FoodDiary foodDiary;

    //for saving progress
    private static final String JSON_STORE = "./data/FoodDiary.json";
    private JsonReader reader;
    private JsonWriter writer;

    //Main screen is a tabbed pane
    private JTabbedPane mainScreen;
    private ProfileUI profilePage;
    private JTextField diaryName;

    //Menu bar items
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem newDiary;
    private JMenuItem save;
    private JMenuItem load;

    public FoodDiaryUI() {
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);

        //sets up window title
        setTitle("FoodDiary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        //creates menuBar for saving and loading
        menuBar = new JMenuBar();
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        newDiary = new JMenuItem("New", KeyEvent.VK_N);
        save = new JMenuItem("Save", KeyEvent.VK_S);
        load = new JMenuItem("Load", KeyEvent.VK_L);

        newDiary.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);

        file.add(newDiary);
        file.add(save);
        file.add(load);

        menuBar.add(file);
        this.setJMenuBar(menuBar);

        //For testing only
     /*   Person guy = new Person();
        guy.setName("John");
        guy.setAge(25);
        guy.setWeight(75);
        guy.setHeight(183);
        guy.setSex(Sex.MALE);
        guy.setActivityLevel(ActivityLevel.SEDENTARY);

        guy.setWeightGoal(69);
        guy.setDietPlan(DietPlan.PLAN1);

        profilePage = new ProfileUI(guy);*/



       /* //creates start screen of the app
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
*/
        mainScreen = new JTabbedPane();
        this.add(mainScreen, BorderLayout.CENTER);
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a new FoodDiary with a new profile, changes are lost if not saved
    private void createNewDiary() {
        mainScreen.removeAll();

        Person user = new Person();
        foodDiary = new FoodDiary("New Diary", user); //replaces old foodDiary

        profilePage = new ProfileUI(user); //replaces old profile Page
        mainScreen.addTab("Profile", profilePage);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newDiary) {
            createNewDiary();
        } else if (source == save) {
            saveFoodDiary();
        } else if (source == load) {
            loadFoodDiary();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads JSON_STORE to current food diary
    private void loadFoodDiary() {

        try {
            foodDiary = reader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Load failed: cannot load FoodDiary from: " + JSON_STORE, "Load Failed", JOptionPane.ERROR_MESSAGE);
            return;// exits method
        }
        mainScreen.removeAll();
        profilePage = new ProfileUI(foodDiary.getUser());

        mainScreen.addTab("Profile",profilePage);

        revalidate();
        repaint();
        JOptionPane.showMessageDialog(this,"Load successful");
    }

    //MODIFIES: this
    //EFFECTS: saves current food diary to JSON_STORE
    private void saveFoodDiary() {
        try {
            writer.open();
            writer.write(foodDiary);
            writer.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Save failed: cannot save to " + JSON_STORE, "Save failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Diary successfully saved to " + JSON_STORE);
    }

}

