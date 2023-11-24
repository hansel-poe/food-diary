/*
package ui;

import model.Day;
import model.Food;
import model.FoodDiary;
import model.Person;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Class representing main window for logging entries,
// displays the list of day entries and allows user to select
// and entry for editing (this brings the user to the entry UI)
public class LogUI extends JPanel implements ActionListener {
    private FoodDiary foodDiary;//the food diary that this ui manages

    //
    private JPanel diaryPanel;
    //For displaying name of diary at the top of UI
    private JLabel diaryNameLabel;

    //Entries table components
    private JScrollPane scrollPane; //container for the table
    private JTable entriesTable; //Tables for displaying entries in our diary
    private EntriesTableModel entriesTableModel; //table model containing entries
    private TableRowSorter tableSorter; //to sort table

    //For adding, removing, and viewing entry
    private JPanel buttonsPanel;
    private JButton addEntry;
    private JButton removeEntry;
    private JButton viewEntry

    LogUI(FoodDiary foodDiary) {
        this.foodDiary = foodDiary;

        //creates title at the top of UI
        diaryPanel = new JPanel(new FlowLayout();
        diaryNameLabel = new JLabel(foodDiary.getName());
        diaryNameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        titlePanel.add(diaryNameLabel);

        //creates table of entries
        entriesTableModel = new EntriesTableModel(this.foodDiary.getDays());
        tableSorter = new TableRowSorter(entriesTableModel);
        entriesTable = new JTable(entriesTableModel);
        entriesTable.setFillsViewportHeight(true);
        entriesTable.setRowSorter(tableSorter);
        scrollPane = new JScrollPane(entriesTable);

        setupButtonsPanel();
        setLayout(new BoxLayout());
    }


    //MODIFIES: this
    //EFFECTS:Sets up buttonsPanel at bottom of UI
    private void setupButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        addEntry = new JButton("Add New Entry");
        addEntry.setBackground(new Color(254, 240, 229));
        addEntry.addActionListener(this);

        removeEntry = new JButton("Remove Entry");
        removeEntry.setBackground(new Color(254, 240, 229));
        removeEntry.addActionListener(this);

        viewEntry = new JButton("View Entry");
        viewEntry.setBackground(new Color(254, 240, 229));
        viewEntry.addActionListener(this);

        buttonsPanel.add(addEntry);
        buttonsPanel.add(removeEntry);
        buttonsPanel.add(viewEntry);
    }

    //Class representing the model for the table displaying all the entries in the diary
    private class EntriesTableModel extends AbstractTableModel {
        private List<Day> entries;
        private String[] columnNames = {"Date", "Weight", "Total Calories", "Calories Allowed"};

        //EFFECTS: constructs new Table model with List of day entries
        EntriesTableModel(List<Day> entries) {
            this.entries = entries;
        }

        //EFFECTS: returns number of entries
        @Override
        public int getRowCount() {
            return entries.size();
        }

        //EFFECTS: returns size of columns
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        //EFFECTS: return values as specified by rowIndex and columnIndex,
        //rowIndex corresponds to a particular day in the list and
        //columnIndex corresponds to the particular field of day
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Day day = entries.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return day.getDate();
                case 1:
                    return day.getWeight();
                case 2:
                    return day.getTotalCalories();
                case 3:
                    return day.getCaloriesAllowed();
            }
            return null;
        }

        //EFFECTS : returns the class of values referred to by columnIndex
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Integer.class;
                case 2:
                    return Integer.class;
                case 3:
                    return Integer.class;
            }
            return null;
        }

        //MODIFIES: this
        //EFFECTS: adds a new day entry to the list of entries
        public void add(Day newEntry) {
            entries.add(newEntry);
            int row = entries.indexOf(newEntry);
            fireTableRowsInserted(row, row);
        }

        //MODIFIES: this
        //EFFECTS: removes entries as specified by indexes from List
        public void remove(int[] indexes) {
            Arrays.sort(indexes);

            List<Day> daysToRemove = new ArrayList<>();
            for (int i : indexes) {
                daysToRemove.add(entries.get(i));
            }
            entries.removeAll(daysToRemove);

            fireTableRowsDeleted(indexes[0], indexes[indexes.length - 1]);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    //EFFECTS: creates a new day with name specified, current weight
    //and calorie allowance of user with zero entries
    public Day createDay() {
        String dayName = JOptionPane.showInputDialog(null, "Enter the date of your new entry: ",
                "New Entry", JOptionPane.PLAIN_MESSAGE);

 if (dayName.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error: Invalid entry for name of new entry", "Error", JOptionPane.ERROR_MESSAGE);
        }

        int currentWeight = foodDiary.getUser().getWeight();
        int currentCalorieAllowance = foodDiary.getUser().getCalorieAllowance();

        return new Day(dayName, currentWeight, currentCalorieAllowance);
    }




}


}
*/
