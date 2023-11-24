package ui;

import model.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
/*
        JFrame myFrame = new JFrame();
        JPanel panel1 = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Panel1", panel1);
        myFrame.add(tabbedPane);

        Object parent = panel1.getParent();
        Object root = SwingUtilities.windowForComponent(panel1);
        myFrame.add(panel1);

        assert (myFrame.equals(root))*/;

        testEntryUI();
        /*new FoodDiaryUI();*/
    }


    private static void testEntryUI() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Entry");


        Day day1;
        ;
        Day day2;

        Food food1;
        Food food2;
        Food food3;
        Food food4;

        day1 = new Day("Monday Oct 20th", 60, 1000);


        food1 = new Food("Apple", 50,
                MealType.BREAKFAST, "The apple is sweet");
        food2 = new Food("Chicken", 100,
                MealType.LUNCH, "Grilled with salt");
        food3 = new Food("Cookies", 120,
                MealType.SNACK, "Chocolate chip");
        food4 = new Food("Cookies",75,
                MealType.DINNER, "Oatmeal");

        day1.addFood(food2);
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        myFrame.add(new EntryUI(day1));
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
