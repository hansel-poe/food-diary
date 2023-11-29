package ui;

import model.Day;
import model.FoodDiary;
import model.exceptions.EmptyInputException;

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

    //Panels containing title and entries table
    private JPanel diaryPanel;
    private JLabel diaryNameLabel;//For displaying name of diary at the top of UI

    //Entries table components
    private JScrollPane scrollPane; //container for the table
    private JTable entriesTable; //Tables for displaying entries in our diary
    private EntriesTableModel tableModel; //table model containing entries
    private TableRowSorter tableSorter; //to sort table

    //For adding, removing, and viewing entry
    private JPanel buttonsPanel;
    private JButton addEntry;
    private JButton removeEntry;
    private JButton viewEntry;
    private JButton editDiaryName;

    public LogUI(FoodDiary foodDiary) {
        this.foodDiary = foodDiary;
        //creates table of entries
        tableModel = new EntriesTableModel(this.foodDiary);
        tableSorter = new TableRowSorter(tableModel);
        entriesTable = new JTable(tableModel);
        entriesTable.setFillsViewportHeight(true);
        entriesTable.setRowSorter(tableSorter);
        scrollPane = new JScrollPane(entriesTable);

        //creates panels containing title and table
        diaryPanel = new JPanel();
        diaryPanel.setLayout(new BoxLayout(diaryPanel, BoxLayout.PAGE_AXIS));
        diaryNameLabel = new JLabel(foodDiary.getName());
        diaryNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        diaryPanel.add(diaryNameLabel);
        diaryPanel.add(scrollPane);
        diaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        setupButtons();
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(addEntry);
        buttonsPanel.add(removeEntry);
        buttonsPanel.add(viewEntry);
        buttonsPanel.add(editDiaryName);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(diaryPanel);
        add(buttonsPanel);
    }

    //MODIFIES: this
    //EFFECTS:Sets up buttons
    private void setupButtons() {
        addEntry = new JButton("Add New Entry");
        addEntry.setBackground(new Color(254, 240, 229));
        addEntry.addActionListener(this);

        removeEntry = new JButton("Remove Entry");
        removeEntry.setBackground(new Color(254, 240, 229));
        removeEntry.addActionListener(this);

        viewEntry = new JButton("View Entry");
        viewEntry.setBackground(new Color(254, 240, 229));
        viewEntry.addActionListener(this);

        editDiaryName = new JButton("Edit diary name");
        editDiaryName.setBackground(new Color(254, 240, 229));
        editDiaryName.addActionListener(this);
    }

    //Class representing the model for the table displaying all the entries in the diary
    private class EntriesTableModel extends AbstractTableModel {
        private FoodDiary foodDiary; //the food diary that contains the entries displayed in this table
        private String[] columnNames = {"Date", "Weight", "Total Calories", "Calories Allowed"};

        //EFFECTS: constructs new Table model with food diary set
        EntriesTableModel(FoodDiary foodDiary) {
            this.foodDiary = foodDiary;
        }

        //EFFECTS: returns number of entries
        @Override
        public int getRowCount() {
            return foodDiary.getNumDays();
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
            Day day = foodDiary.getDay(rowIndex);
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

        //EFFECTS: returns column names
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        //MODIFIES: this
        //EFFECTS: adds a new day entry to the list of entries
        public void add(Day newEntry) {
            foodDiary.addDay(newEntry);
            int row = foodDiary.indexOf(newEntry);
            fireTableRowsInserted(row, row);
        }

        //MODIFIES: this
        //EFFECTS: removes entries as specified by indexes from List
        public void remove(int[] indexes) {
            Arrays.sort(indexes);

            List<Day> daysToRemove = new ArrayList<>();
            for (int i : indexes) {
                daysToRemove.add(foodDiary.getDay(i));
            }
            foodDiary.removeDays(daysToRemove);
            fireTableRowsDeleted(indexes[0], indexes[indexes.length - 1]);
        }

        //EFFECTS : returns day entry according to index
        public Day get(int index) {
            return foodDiary.getDay(index);
        }
    }

    //Listens for events from buttons
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selection = convertIndexes(entriesTable.getSelectedRows());

        Object source = e.getSource();
        if (source == addEntry) {
            try {
                tableModel.add(createDay());
            } catch (EmptyInputException ex) {
                JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this),
                        "Entry name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == removeEntry) {
            if (selection.length == 0) {
                JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this),
                        "No Entry selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            tableModel.remove(selection);
        } else if (source == viewEntry) {
            if (selection.length == 0) {
                JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this),
                        "No Entry selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Day day = tableModel.get(selection[0]);

            FoodDiaryUI root = (FoodDiaryUI) SwingUtilities.windowForComponent(this);
            root.openEntry(day);
        } else if (source == editDiaryName) {
            editDiaryName();
        }
    }

    //EFFECTS: creates a new day with name specified, current weight
    //and calorie allowance of user with zero entries if user input is empty, throws EmptyInputException
    private Day createDay() throws EmptyInputException {
        String dayName = JOptionPane.showInputDialog(SwingUtilities.windowForComponent(this),
                "Enter the date of your new entry: ", "New Entry", JOptionPane.PLAIN_MESSAGE);
        int currentWeight = foodDiary.getUser().getWeight();
        int currentCalorieAllowance = foodDiary.getUser().getCalorieAllowance();

        if ((dayName != null) && (dayName.length() > 0)) {
            return new Day(dayName, currentWeight, currentCalorieAllowance);
        } else {
            throw new EmptyInputException();
        }
    }

    //MODIFIES:this
    //EFFECTS: edits name of food diary by asking user input if user input is empty,
    // then an error is shown and no changes is made
    private void editDiaryName() {
        String diaryName = JOptionPane.showInputDialog(SwingUtilities.windowForComponent(this),
                "Enter the name of your diary: ", "Edit diary name", JOptionPane.PLAIN_MESSAGE);

        if ((diaryName != null) && (diaryName.length() > 0)) {
            foodDiary.setName(diaryName);
            diaryNameLabel.setText(diaryName);
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this),
                    "Error: Invalid input for name of new entry", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: returns a mapping of selected row indexes to the
    //corresponding foods index
    private int[] convertIndexes(int[] selectedRows) {
        int[] selection = entriesTable.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = entriesTable.convertRowIndexToModel(selection[i]);
        }
        return selection;
    }
}


