package ui;

import model.Day;
import model.Food;
import model.MealType;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Represents a screen for managing a particular day entry, this class modifies the day entry it manages
//allowing user to add and remove food items
public class EntryUI extends JPanel implements ActionListener {

    Day day;//represents the day entry this gui manages

    private JScrollPane scrollPane;//container for the table
    private JTable entryTable;//Tables for displaying food items
    private MyTableModel tableModel;
    private TableRowSorter tableSorter;//to sort table
    private JLabel dayName;//Represents the day name of the entry being viewed

    private JPanel bottomPanel;

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
        tableModel = new MyTableModel(this.day.getFoods());

        tableSorter = new TableRowSorter(tableModel);

        entryTable = new JTable(tableModel);
        entryTable.setFillsViewportHeight(true);
        entryTable.setRowSorter(tableSorter);

        scrollPane = new JScrollPane(entryTable);

        totalCaloriesLabel = new JLabel(String.valueOf(day.getTotalCalories()));
        caloriesAllowedLabel = new JLabel(String.valueOf(day.getCaloriesAllowed()));
        caloriesLeftLabel = new JLabel(String.valueOf(day.getCaloriesLeft()));
        weightLabel = new JLabel(String.valueOf(day.getWeight()));

        setupAddComponents();

        bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);

        c.gridy = 0;
        c.gridx = 0;

        bottomPanel.add(new JLabel("Total calories:"), c);

        c.gridx = 1;
        bottomPanel.add(totalCaloriesLabel, c);

        c.gridx = 2;
        bottomPanel.add(new JLabel("Calories allowed"), c);

        c.gridx = 3;
        bottomPanel.add(caloriesAllowedLabel, c);

        c.gridx = 4;
        bottomPanel.add(new JLabel("Calorie allowance left:"), c);

        c.gridx = 5;
        bottomPanel.add(caloriesLeftLabel, c);

        c.gridx = 6;
        bottomPanel.add(new JLabel("Weight (in kg)"), c);

        c.gridx = 7;
        bottomPanel.add(weightLabel, c);

        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.CYAN);
        bottomPanel.add(separator, c);
        c.gridwidth = 1;

        c.gridy = 2;
        c.gridx = 0;
        bottomPanel.add(new JLabel("Name:"), c);

        c.gridx = 1;
        bottomPanel.add(nameField, c);

        c.gridx = 2;
        bottomPanel.add(new JLabel("Calories"), c);

        c.gridx = 3;
        bottomPanel.add(caloriesField, c);

        c.gridx = 4;
        bottomPanel.add(new JLabel("Meal type:"), c);

        c.gridx = 5;
        bottomPanel.add(mealTypeField, c);

        c.gridx = 6;
        bottomPanel.add(new JLabel("Notes:"), c);

        c.gridx = 7;
        bottomPanel.add(notesField, c);

        c.gridx = 8;
        bottomPanel.add(addFood, c);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 3;
        c.gridx = 8;
        bottomPanel.add(removeFood, c);

        setLayout(new GridLayout(2,1));
        add(scrollPane);
        add(bottomPanel);
    }

    private void setupAddComponents() {
        nameField = new JTextField();
        nameField.setColumns(10);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);

        mealTypeField = new JComboBox(MealType.values());
        mealTypeField.setSelectedIndex(0);

        notesField = new JTextField();
        notesField.setColumns(10);

        addFood = new JButton("Add food");
        addFood.addActionListener(this);

        removeFood = new JButton("Remove food");
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
            return getValueAt(0, columnIndex).getClass();
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
            List<Food> foodsToRemove = new ArrayList<>();
            for (int i : indexes) {
                foodsToRemove.add(foods.get(i));
            }
            foods.removeAll(foodsToRemove);
          /*  //doesnt work
            for (int i : indexes) {
                foods.remove(i);
            }*/
            fireTableRowsDeleted(indexes[0],indexes[indexes.length - 1]);
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

    //EFFECTS: Creates new food based on user input
    public Food createFood() {
        String name = nameField.getText();
        int calories = 0;
        MealType mealType = (MealType) mealTypeField.getSelectedItem();
        String notes = notesField.getText();

        try {
            calories = Integer.parseInt(caloriesField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid entry for calories", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return new Food(name, calories, mealType, notes);
    }

    //EFFECTS: returns a mapping of selected rows indexes to the underlying model
    public int[] convertIndexes(int[]selectedRows) {
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
            tableModel.add(createFood());
            updateLabels();
            resetFields();
        } else if (source == removeFood) {
            tableModel.remove(selection);
            updateLabels();
        }
    }
}

