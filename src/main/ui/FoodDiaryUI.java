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

    //for saving data
    private static final String JSON_STORE = "./data/FoodDiary.json";
    private JsonReader reader;
    private JsonWriter writer;

    //Main screen is a tabbed pane consisting of profile page and entryUI
    private JTabbedPane mainScreen;
    private ProfileUI profilePage;
    private LogUI logPage;

    //Menu bar items
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem newDiary;
    private JMenuItem save;
    private JMenuItem load;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        mainScreen = new JTabbedPane();
        mainScreen.setBackground(new Color(254, 240, 229));
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
        logPage = new LogUI(foodDiary);
        mainScreen.addTab("Log", logPage);
        mainScreen.addTab("Profile", profilePage);
        mainScreen.setMnemonicAt(0,KeyEvent.VK_L);
        mainScreen.setMnemonicAt(1,KeyEvent.VK_P);

        revalidate();
        repaint();
    }

    //Handles adding
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
        logPage = new LogUI(foodDiary);

        mainScreen.addTab("Log", logPage);
        mainScreen.addTab("Profile", profilePage);
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

    //MODIFIES: this
    //EFFECTS: Opens a new tab with selected entries , see LogUI
    public void openEntry(Day day) {
        String title = day.getDate();

        // if tab for this day is already open then do nothing
        if (mainScreen.indexOfTab(title) != -1) {
            return;
        }

        EntryUI entryPage = new EntryUI(day);
        mainScreen.addTab(title,entryPage);

        int index = mainScreen.indexOfTab(title);
        addCloseTabComponent(index, title);
       /* revalidate();
        repaint();*/
    }

    //adds x component to close tab
    private void addCloseTabComponent(int index, String title) {
        JPanel closeTab = new JPanel(new FlowLayout(FlowLayout.LEFT,3,0));
        closeTab.setOpaque(false);

        JLabel tabTitle = new JLabel(title);
        JButton closeButton = new JButton("x"); //button to close tab
        closeButton.setBackground(new Color(254, 240, 229));

        closeTab.add(tabTitle);
        closeTab.add(closeButton);

        mainScreen.setTabComponentAt(index, closeTab);
        closeButton.addActionListener(new MyCloseActionHandler(title));
    }

    //Handles events for clicking x at tab
    private class MyCloseActionHandler implements ActionListener {
        private String tabName;

        public MyCloseActionHandler(String tabName) {
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }

        public void actionPerformed(ActionEvent evt) {
            int index = mainScreen.indexOfTab(getTabName());
            if (index >= 0) {
                mainScreen.removeTabAt(index);
                // It would probably be worthwhile getting the source
                // casting it back to a JButton and removing
                // the action handler reference ;)
            }
        }
    }
}

