package ui;

import model.Day;
import model.Food;
import model.MealType;
import model.exceptions.NegativeValueException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Represents a screen for managing a particular day entry, this class modifies the day entry it manages
//allowing user to add and remove food items
public class EntryUI extends JPanel implements ActionListener {

    Day day;//represents the day entry this gui manages
    private JPanel entryPanel;//to contain the title and table
    private JLabel dayNameLabel;//Represents title

    private JScrollPane scrollPane;//container for the table
    private JTable entryTable;//Tables for displaying food items
    private MyTableModel tableModel;//table model for managing data of table
    private TableRowSorter tableSorter;//to sort table

    private JPanel summaryPanel;// shows summary of food
    private JPanel newFoodPanel;// fields for user to provide input
    private JPanel buttonsPanel;//buttons for adding and removing food

    //summary labels
    private JLabel totalCaloriesLabel;
    private JLabel caloriesAllowedLabel;
    private JLabel caloriesLeftLabel;
    private JLabel weightLabel;

    //Components for adding new food Item
    private JButton addFood;
    private JTextField nameField;
    private JTextField caloriesField;
    private JComboBox mealTypeField;
    private JTextField notesField;

    //Button to remove food
    private JButton removeFood;

    public EntryUI(Day day) {
        this.day = day;

        //creates table of food items and add it to scroll pane
        tableModel = new MyTableModel(this.day.getFoods());
        tableSorter = new TableRowSorter(tableModel);
        entryTable = new JTable(tableModel);
        entryTable.setFillsViewportHeight(true);
        entryTable.setRowSorter(tableSorter);
        scrollPane = new JScrollPane(entryTable);

        //creates panels containing title and table
        entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel,BoxLayout.PAGE_AXIS));
        dayNameLabel = new JLabel(this.day.getDate());
        dayNameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        entryPanel.add(dayNameLabel);
        entryPanel.add(Box.createRigidArea(new Dimension(0,7)));
        entryPanel.add(scrollPane);
        entryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setupSummaryLabels(this.day);
        setupAddComponents();

        //creates panels containing summaries of day entry
        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.LINE_AXIS));
        summaryPanel.add(new JLabel("Total calories: "));
        summaryPanel.add(totalCaloriesLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        summaryPanel.add(new JLabel("Calories allowed: "));
        summaryPanel.add(caloriesAllowedLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        summaryPanel.add(new JLabel("Calorie allowance left: "));
        summaryPanel.add(caloriesLeftLabel);
        summaryPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        summaryPanel.add(new JLabel("Weight (in kg): "));
        summaryPanel.add(weightLabel);
        summaryPanel.add(Box.createHorizontalGlue()); //eats up any remaining horizontal space
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        //sets up pane for user to enter new Food information
        newFoodPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        newFoodPanel.add(new JLabel("Name:"));
        newFoodPanel.add(nameField);
        newFoodPanel.add(new JLabel("Calories:"));
        newFoodPanel.add(caloriesField);
        newFoodPanel.add(new JLabel("Meal type:"));
        newFoodPanel.add(mealTypeField);
        newFoodPanel.add(new JLabel("Notes:"));
        newFoodPanel.add(notesField);

        //sets up panel for buttons
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonsPanel.add(addFood);
        buttonsPanel.add(removeFood);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(entryPanel);
        add(summaryPanel);
        add(newFoodPanel);
        add(buttonsPanel);
    }

    //MODIFIES: this
    //EFFECTS: initialize all summary labels
    private void setupSummaryLabels(Day day) {
        totalCaloriesLabel = new JLabel(String.valueOf(day.getTotalCalories()));
        caloriesAllowedLabel = new JLabel(String.valueOf(day.getCaloriesAllowed()));
        caloriesLeftLabel = new JLabel(String.valueOf(day.getCaloriesLeft()));
        weightLabel = new JLabel(String.valueOf(day.getWeight()));
    }

    //MODIFIES: this
    //EFFECTS: sets up all components to add food
    private void setupAddComponents() {
        nameField = new JTextField();
        nameField.setColumns(10);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);

        mealTypeField = new JComboBox(MealType.values());
        mealTypeField.setBackground(new Color(254, 240, 229));
        mealTypeField.setSelectedIndex(0);

        notesField = new JTextField();
        notesField.setColumns(10);

        addFood = new JButton("Add food");
        addFood.setBackground(new Color(254, 240, 229));
        addFood.addActionListener(this);

        removeFood = new JButton("Remove food");
        removeFood.setBackground(new Color(254, 240, 229));
        removeFood.addActionListener(this);
    }

    //Custom table model to display food items in day entry, note that
    // this class can modify the food list in the day entry
    private class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Food", "Calories", "Meal Type", "Notes"};//columns of the table,
        // each displaying a particular data of food
        private List<Food> foods;//each food represents one row data

        public MyTableModel(List<Food> foods) {
            this.foods = foods;
        }

        //EFFECTS: this returns the class of the column referred to by columnIndex
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Integer.class;
                case 2:
                    return MealType.class;
                case 3:
                    return String.class;
            }
            return null;
            //return getValueAt(0, columnIndex).getClass();
        }

        //EFFECTS: returns the number of rows
        @Override
        public int getRowCount() {
            return foods.size();
        }

        //EFFECTS: returns the number of columns
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        //EFFECTS: returns the values corresponding to table indexes,
        //returns null if any one indexes are out of bounds
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Food food = foods.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return food.getName();
                case 1:
                    return food.getCalories();
                case 2:
                    return food.getMealType();
                case 3:
                    return food.getNotes();
            }
            return null;
        }

        //EFFECTS: returns the name of the column according to index
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        //MODIFIES: this
        //EFFECTS: adds a new food item to list of foods and updates table
        public void add(Food food) {
            foods.add(food);
            int row = foods.indexOf(food);
            fireTableRowsInserted(row, row);
        }

        //MODIFIES: this
        //EFFECTS: removes food items in foods with indexes specified and updates table
        public void remove(int[] indexes) {
            Arrays.sort(indexes);

            List<Food> foodsToRemove = new ArrayList<>();
            for (int i : indexes) {
                foodsToRemove.add(foods.get(i));
            }
            foods.removeAll(foodsToRemove);
          /*  //doesnt work
            for (int i : indexes) {
                foods.remove(i);
            }*/
            fireTableRowsDeleted(indexes[0], indexes[indexes.length - 1]);
        }
    }

    //MODIFIES: this
    //EFFECTS: makes all textfields empty and reset ComboBox to index 0
    private void resetFields() {
        nameField.setText("");
        caloriesField.setText("");
        mealTypeField.setSelectedIndex(0);
        notesField.setText("");
    }

    //MODIFIES: this
    //EFFECTS: updates total calories and calories left labels
    private void updateLabels() {
        totalCaloriesLabel.setText(String.valueOf(day.getTotalCalories()));
        caloriesLeftLabel.setText(String.valueOf(day.getCaloriesLeft()));
    }

    //EFFECTS: Creates new food based on user input, if input cannot be parsed, throws NumberFormatException,
    // if calories < 0, throws NegativeValueException
    public Food createFood() throws NumberFormatException, NegativeValueException {
        String name = nameField.getText();
        int calories = Integer.parseInt(caloriesField.getText());
        ;
        MealType mealType = (MealType) mealTypeField.getSelectedItem();
        String notes = notesField.getText();

        if (calories < 0) {
            throw new NegativeValueException();
        }
        return new Food(name, calories, mealType, notes);
    }

    //EFFECTS: returns a mapping of selected rows indexes to the
    //corresponding foods index
    public int[] convertIndexes(int[] selectedRows) {
        int[] selection = entryTable.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = entryTable.convertRowIndexToModel(selection[i]);
        }
        return selection;
    }

    //MODIFIES: this
    //EFFECTS: listens to events from addFood and removeFood button
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selection = convertIndexes(entryTable.getSelectedRows());

        Object source = e.getSource();
        if (source == addFood) {
            try {
                tableModel.add(createFood());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null,
                        "Invalid entry for calories", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NegativeValueException exception) {
                JOptionPane.showMessageDialog(null,
                        "Calories cannot be negative", "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateLabels();
            resetFields();
        } else if (source == removeFood) {
            if (selection.length == 0) {
                JOptionPane.showMessageDialog(null,
                        "No Food selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            tableModel.remove(selection);
            updateLabels();
        }
    }
}

