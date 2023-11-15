package ui;

import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//Represents UI of a form for editing user Profile
//This class' purpose is to handle modifying the user of FoodDiary
public class ProfileUI extends JPanel implements PropertyChangeListener, ActionListener, DocumentListener {

    private Person user;//user from FoodDiary, values from user input will be used to modify this
    private CalorieCalculator cc;//calorie calculator to calculate calories allowed
    private JPanel formPane; //Panels for storing all the form components

    //Fields storing user input
    private String name = "John Doe";
    private int age = 25;
    private int weight = 70;
    private int height = 175;
    private Sex sex = Sex.MALE;

    private int weightGoal = 65;
    private ActivityLevel activityLevel = ActivityLevel.SEDENTARY;
    private DietPlan dietPlan = DietPlan.PLAN1;
    private int caloriesAllowed = 1000;
    private int goalLength = 156;

    //Labels
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel weightLabel;
    private JLabel heightLabel;
    private JLabel sexLabel;
    private JLabel activityLevelLabel;
    private JLabel weightGoalLabel;
    private JLabel dietPlanLabel;
    private JLabel caloriesAllowedLabel;
    private JLabel goalLengthLabel;

    //TextFields
    private JTextField nameField;
    private JFormattedTextField ageField;
    private JFormattedTextField weightField;
    private JFormattedTextField heightField;
    private JFormattedTextField weightGoalField;
    private JFormattedTextField caloriesAllowedField; //is uneditable
    private JFormattedTextField goalLengthField;//is uneditable

    //Combo box for sex options
    private JComboBox sexList;

    //Combo box for diet plan options
    private JComboBox planList;

    //Combo box for activity level options
    private JComboBox activityList;

    //Button for disabling and enabling editing and resetting values in fields
    private JPanel buttonsPanel;
    private JButton set;
    private JButton edit;
    private JButton reset;

    public ProfileUI() {
        //this.user = user;

        /*this.name = user.getName();
        this.age = user.getAge();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.sex = user.getSex();*/

        initLabels();
        setupTextFields();
        setupComboBoxes();
        setupButtons();
        setEditing(true);

        formPane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0; //first rows for labels
        c.gridy = 0;
        formPane.add(nameLabel, c);

        c.gridy = 1;
        formPane.add(ageLabel, c);

        c.gridy = 2;
        formPane.add(weightLabel, c);

        c.gridy = 3;
        formPane.add(heightLabel, c);

        c.gridy = 4;
        formPane.add(sexLabel, c);

        c.gridy = 5;
        formPane.add(activityLevelLabel, c);

        c.gridy = 6;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        formPane.add(new JSeparator(), c);//Line separator

        c.gridy = 7;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        formPane.add(weightGoalLabel, c);

        c.gridy = 8;
        formPane.add(dietPlanLabel, c);

        c.gridy = 9;
        formPane.add(caloriesAllowedLabel, c);

        c.gridy = 10;
        formPane.add(goalLengthLabel, c);

        c.gridx = 1; //Second columns for components
        c.gridy = 0;
        formPane.add(nameField, c);

        c.gridy = 1;
        formPane.add(ageField, c);

        c.gridy = 2;
        formPane.add(weightField, c);

        c.gridy = 3;
        formPane.add(heightField, c);

        c.gridy = 4;
        formPane.add(sexList, c);

        c.gridy = 5;
        formPane.add(activityList, c);

        c.gridy = 7;
        formPane.add(weightGoalField, c);

        c.gridy = 8;
        formPane.add(planList, c);

        c.gridy = 9;
        formPane.add(caloriesAllowedField, c);

        c.gridy = 10;
        formPane.add(goalLengthField, c);

        c.gridy = 11;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        formPane.add(buttonsPanel, c);

        this.add(formPane, BorderLayout.CENTER);
    }

    //EFFECTS: Sets up labels for the ui
    private void initLabels() {
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        weightLabel = new JLabel("Weight (in Kg):");
        heightLabel = new JLabel("Height (in cm):");
        sexLabel = new JLabel("Sex:");
        activityLevelLabel = new JLabel("Activity Level:");
        weightGoalLabel = new JLabel("Weight goal (in kg):");
        dietPlanLabel = new JLabel("Diet plan:");
        caloriesAllowedLabel = new JLabel("Daily calorie allowance:");
        goalLengthLabel = new JLabel("Days to reach goal: ");
    }

    //EFFECTS: Sets up the set and edit buttons
    private void setupButtons() {
        set = new JButton("Set");
        set.addActionListener(this);

        edit = new JButton("Edit");
        edit.addActionListener(this);

        reset = new JButton("Reset");
        edit.addActionListener(this);

        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);

        buttonsPanel = new JPanel(gridLayout);

        buttonsPanel.add(reset);
        buttonsPanel.add(set);
        buttonsPanel.add(edit);
    }

    //EFFECTS: Sets up the two ComboBoxes in the form
    private void setupComboBoxes() {
        activityList = new JComboBox(ActivityLevel.values());
        activityList.setSelectedItem(activityLevel);
        activityList.addActionListener(this);

        planList = new JComboBox(DietPlan.values());
        planList.setSelectedItem(dietPlan);
        planList.addActionListener(this);

        sexList = new JComboBox(Sex.values());
        sexList.setSelectedItem(sex);
        sexList.addActionListener(this);
    }

    //EFFECTS: Sets up text fields
    private void setupTextFields() {
        nameField = new JTextField(15);
        nameField.setText(name);
        nameField.addActionListener(this);
        nameField.getDocument().addDocumentListener(this);

        ageField = new JFormattedTextField();
        ageField.setValue(new Integer(age));
        ageField.setColumns(10);
        ageField.addPropertyChangeListener("value", this);

        weightField = new JFormattedTextField();
        weightField.setValue(new Integer(weight));
        weightField.setColumns(10);
        weightField.addPropertyChangeListener("value", this);

        heightField = new JFormattedTextField();
        heightField.setValue(new Integer(height));
        heightField.setColumns(10);
        heightField.addPropertyChangeListener("value", this);

        weightGoalField = new JFormattedTextField();
        weightGoalField.setValue(new Integer(weightGoal));
        weightGoalField.setColumns(10);
        weightGoalField.addPropertyChangeListener("value", this);

        caloriesAllowedField = new JFormattedTextField();
        caloriesAllowedField.setValue(new Integer(caloriesAllowed));
        caloriesAllowedField.setColumns(10);
        caloriesAllowedField.setEditable(false);

        goalLengthField = new JFormattedTextField();
        goalLengthField.setValue(new Integer(goalLength));
        goalLengthField.setColumns(10);
        goalLengthField.setEditable(false);
    }

    //For debugging purposes only
    private void printValues() {
        System.out.println(name);
        System.out.println(age);
        System.out.println(weight);
        System.out.println(height);
        System.out.println(sex);
        System.out.println(activityLevel);
        System.out.println(weightGoal);
        System.out.println(dietPlan);
        System.out.println(caloriesAllowed);
        System.out.println(goalLength);
    }

    //EFFECTS: if state is true, all fields, ComboBoxes and buttons will be enabled.
    //otherwise, all fields, ComboBoxes and buttons will be disabled
    private void setEditing(boolean state) {
        nameField.setEditable(state);
        ageField.setEditable(state);
        weightField.setEditable(state);
        heightField.setEditable(state);
        weightGoalField.setEditable(state);
        sexList.setEnabled(state);
        activityList.setEnabled(state);
        planList.setEnabled(state);

        set.setEnabled(state);
        reset.setEnabled(state);
        edit.setEnabled(!state);
    }

    //EFFECTS: Registers user input into user, also calculates new calorieAllowance and weightGoal
    private void setProfile() {
        user.setName(name);
        user.setAge(age);
        user.setWeight(weight);
        user.setHeight(height);
        user.setSex(sex);
        user.setActivityLevel(activityLevel);
        user.setWeightGoal(weightGoal);
        user.setDietPlan(dietPlan);

        caloriesAllowed = cc.calculateCalorieAllowance(user);
        user.setCalorieAllowance(caloriesAllowed);
        goalLength = user.getGoalLength();
        //GoalLength is not part of user's fields therefore we do not modify it in the user
    }

    //EFFECTS: resets fields and values displayed
    private void resetValues() {
        name = user.getName();
        age = user.getAge();
        weight = user.getWeight();
        height = user.getHeight();
        sex = user.getSex();
        activityLevel = user.getActivityLevel();

        weightGoal = user.getWeightGoal();
        dietPlan = user.getDietPlan();
        //caloriesAllowed and goalLength does not to be reset as
        //they only change once set is clicked

    }

    //EFFECTS: updates values displayed in components
    private void updateComponents() {
        nameField.setText(name);
        ageField.setValue(new Integer(age));
        weightField.setValue(new Integer(weight));
        heightField.setValue(new Integer(height));
        sexList.setSelectedItem(sex);
        activityList.setSelectedItem(activityLevel);

        weightGoalField.setValue(new Integer(weightGoal));
        planList.setSelectedItem(dietPlan);
        caloriesAllowedField.setValue(new Integer(caloriesAllowed));
        goalLengthField.setValue(new Integer(goalLength));
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Listeners~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Effects: Listens for Formatted textFields changes and records changes in corresponding variables
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == ageField) {
            age = ((Number) ageField.getValue()).intValue();
        } else if (source == weightField) {
            weight = ((Number) weightField.getValue()).intValue();
        } else if (source == heightField) {
            height = ((Number) heightField.getValue()).intValue();
        } else if (source == weightGoalField) {
            weightGoal = ((Number) weightGoalField.getValue()).intValue();
        }
        printValues();
    }

    //Listens for changes in ComboBoxes and set, edit and reset buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == activityList) {
            JComboBox cb = (JComboBox) source;
            activityLevel = (ActivityLevel) cb.getSelectedItem();
        } else if (source == planList) {
            JComboBox cb = (JComboBox) source;
            dietPlan = (DietPlan) cb.getSelectedItem();
        } else if (source == sexList) {
            JComboBox cb = (JComboBox) source;
            sex = (Sex) cb.getSelectedItem();
        } else if (source == set) {
            //setProfile();
            updateComponents();
            setEditing(false);
        } else if (source == edit) {
            setEditing(true);
        } else if (source == reset) {
            resetValues();
            updateComponents();
        }
        printValues();
    }

    //Listens for changes in name field and records changes in variables
    @Override
    public void insertUpdate(DocumentEvent e) {
        this.name = nameField.getText();
        System.out.println(name);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.name = nameField.getText();
        System.out.println(name);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //Do nothing
    }

}


