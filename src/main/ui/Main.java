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

        /*testEntryUI();*/
        /*testLogUI();*/
        new FoodDiaryUI();
    }

    //For testing purposes only
    /*private static void testEntryUI() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("EntryUI");

        Day day1 = new Day("Monday Oct 20th", 60, 1000);

        Food food1 = new Food("Apple", 50,
                MealType.BREAKFAST, "The apple is sweet");
        Food food2 = new Food("Chicken", 100,
                MealType.LUNCH, "Grilled with salt");
        Food food3 = new Food("Cookies", 120,
                MealType.SNACK, "Chocolate chip");
        Food food4 = new Food("Cookies",75,
                MealType.DINNER, "Oatmeal");

        day1.addFood(food2);
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        myFrame.add(new EntryUI(day1));
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private static void testLogUI() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("LogUI");

        Person hansel = new Person();
        hansel.setCalorieAllowance(900);
        hansel.setWeight(60);

        FoodDiary foodDiary = new FoodDiary("Hansel's Diary",hansel);

        Day day1 = new Day("Monday Oct 20th", 65, 1000);
        Day day2 = new Day("Tuesday oct 21st", 64,1000);

        Food food1 = new Food("Apple", 50,
                MealType.BREAKFAST, "The apple is sweet");
        Food food2 = new Food("Chicken", 100,
                MealType.LUNCH, "Grilled with salt");
        Food food3 = new Food("Cookies", 120,
                MealType.SNACK, "Chocolate chip");
        Food food4 = new Food("Cookies",75,
                MealType.DINNER, "Oatmeal");

        day1.addFood(food2);
        day1.addFood(food1);
        day2.addFood(food3);
        day2.addFood(food4);
        foodDiary.addDay(day1);
        foodDiary.addDay(day2);

        myFrame.add(new LogUI(foodDiary));
        myFrame.pack();
        myFrame.setVisible(true);
    }*/
}
