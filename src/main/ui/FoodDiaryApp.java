package ui;

import model.CalorieCalculator;
import model.Day;
import model.Food;
import model.Person;
import model.exceptions.InvalidActivityLevelException;
import model.exceptions.InvalidDietPlanException;
import model.exceptions.InvalidMealTypeException;
import model.exceptions.InvalidSexException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class FoodDiaryApp {

    //Declaring fields
    private List<Day> days;
    private Person user;
    private CalorieCalculator cc;
    private Scanner input;

    //Effects: runs the app
    public FoodDiaryApp() {
        runFoodDiary();
    }

    //Modifies: this
    //Effects: runs Food diary App
    private void runFoodDiary() {
        boolean runs = true;

        init();// initializes variables
        setup();//sets up user profile

        while (runs) {
            displayMenu();

            int option;
            option = input.nextInt();
            input.nextLine();

            if (option == 4) {
                runs = false;
            } else {
                processOption(option);
            }
        }
        System.out.println("Thanks for using FoodDiary :)");
    }

    private void processOption(int option) {
        if (option == 1) {
            addEntry();
        } else if (option == 2) {
            printEntry();
        } else if (option == 3) {
            printProfile();
        } else {
            System.out.println("Invalid input, Please try again.");
        }
    }

    private static void displayMenu() {
        System.out.println("Please select an option: ");
        System.out.println("1. Log an entry");
        System.out.println("2. View all entries");
        System.out.println("3. View Profile");
        System.out.println("4. Quit app");
    }

    private void printProfile() {
        System.out.println("Name: " + user.getName());
        System.out.println("Sex: " + user.getSex());
        System.out.println("Age: " + user.getAge() + " years");
        System.out.println("Height: " + user.getHeight() + " cm");
        System.out.println("Weight: " + user.getWeight() + " kg");
        System.out.println("Daily Calorie Allowance: " + user.getCalorieAllowance());
        System.out.println("You will reach your goal in: " + user.getGoalLength() + " days");
    }

    private void printEntry() {
        System.out.println("Your entries : ");

        for (Day day : days) {
            System.out.println(day.getDate());

            printBreakfast(day);

            printLunch(day);
            printDinner(day);
            printSnack(day);

            System.out.println("The total calories for the day: " + day.getTotalCalories());
            System.out.println("Calories allowed for this day: " + day.getCaloriesAllowed());
            System.out.println("Calories Left: " + day.getCaloriesLeft() + "\n");
        }
    }

    private void printBreakfast(Day day) {
        System.out.println("Breakfast:");
        Food food;
        for (int i = 0; i < day.getNumFoods(); i++) {
            food = day.getFood(i);
            if ((food.getMealType()).equals("Breakfast")) {
                System.out.println(food.toString());
            }
        }
    }

    private void printLunch(Day day) {
        System.out.println("Lunch:");
        Food food;
        for (int i = 0; i < day.getNumFoods(); i++) {
            food = day.getFood(i);
            if (food.getMealType().equals("Lunch")) {
                System.out.println(food.toString());
            }
        }
    }

    private void printDinner(Day day) {
        System.out.println("Dinner:");
        Food food;
        for (int i = 0; i < day.getNumFoods(); i++) {
            food = day.getFood(i);
            if (food.getMealType().equals("Dinner")) {
                System.out.println(food.toString());
            }
        }
    }

    private void printSnack(Day day) {
        System.out.println("Snack:");
        Food food;
        for (int i = 0; i < day.getNumFoods(); i++) {
            food = day.getFood(i);
            if (food.getMealType().equals("Snack")) {
                System.out.println(food.toString());
            }
        }
    }

    private void addEntry() {
        boolean logging = true;
        String userInput = null;
        int option;

        System.out.println("Please enter a date: ");

        userInput = input.nextLine();
        Day day = new Day(userInput, user.getWeight(), user.getCalorieAllowance());
        System.out.println("You are logging for: " + userInput);

        while (logging) {
            logFood(day);

            System.out.println("select 1 to quit, any other key to continue logging");
            option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                break;
            }
        }
        days.add(day);
    }

    private void logFood(Day day) {
        String foodName;
        int calories;
        String foodType;
        String notes;

        System.out.println("Please enter the name of food: ");
        foodName = input.nextLine();

        System.out.println("Please enter the calories for this food: ");
        calories = input.nextInt();
        input.nextLine();

        System.out.println(" What type of meal is this? 1. Breakfast, 2.Lunch, 3.Dinner, 4.Snack");
        foodType = input.nextLine();

        System.out.println("Enter some notes: ");
        notes = input.nextLine();

        Food food = null;
        try {
            food = new Food(foodName, calories, foodType, notes);
            day.addFood(food);
        } catch (InvalidMealTypeException e) {
            System.out.println("Food type is not valid, please try again");
        }
    }

    private void init() {
        days = new ArrayList<Day>();
        user = new Person();
        cc = new CalorieCalculator();

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void setup() {
        System.out.println("Let's setup your user profile!");
        System.out.println("Please enter your name:");

        String userName = input.nextLine();
        user.setName(userName);

        System.out.println("Hi " + userName + " ,just a few more steps to get you started.");

        System.out.println("Please indicate your sex:");
        System.out.println("1. Male");
        System.out.println("2. Female");

        int userInfo = input.nextInt();

        try {
            if (userInfo == 1) {
                user.setSex("Male");
            } else if (userInfo == 2) {
                user.setSex("Female");
            } else {
                System.out.println("Error: Invalid option");
                exit(1);
            }
        } catch (InvalidSexException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Please enter your age: ");
        userInfo = input.nextInt();

        if (userInfo < 0) {
            System.out.println("Error: Invalid input");
            exit(1);
        }
        user.setAge(userInfo);

        System.out.println("Please enter your Height in cm: ");
        userInfo = input.nextInt();

        if (userInfo < 0) {
            System.out.println("Error: Invalid input");
            exit(1);
        }
        user.setHeight(userInfo);

        System.out.println("Please enter your Weight in Kg: ");
        userInfo = input.nextInt();

        if (userInfo < 0) {
            System.out.println("Error: Invalid input");
            exit(1);
        }

        user.setWeight(userInfo);

        System.out.println("Please enter your weight goal: ");
        userInfo = input.nextInt();

        if (userInfo > user.getWeight()) {
            System.out.println("Error: Invalid input");
            exit(1);
        }

        user.setWeightGoal(userInfo);

        System.out.println("Please choose one of the activity levels below: ");
        System.out.println("1.`Sedentary : little to no exercise : ");
        System.out.println("2. Light exercise");
        System.out.println("3. Active");
        System.out.println("4. Very active");
        System.out.println("5. Extremely active");

        userInfo = input.nextInt();
        if (userInfo < 1 || userInfo > 5) {
            System.out.println("Error: Invalid input");
            exit(1);
        }

        try {
            user.setActivityLevel(userInfo);
        } catch (InvalidActivityLevelException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Choose one of the diet plans below below: ");
        System.out.println("1. Lose 0.25 Kg per week");
        System.out.println("2. Lose 0.5 Kg per week");
        System.out.println("3. Lose 1 Kg per week");

        userInfo = input.nextInt();
        if (userInfo < 1 || userInfo > 3) {
            System.out.println("Error: Invalid input");
            exit(1);
        }

        try {
            user.setDietPlan(userInfo);
        } catch (InvalidDietPlanException e) {
            System.out.println(e.getMessage());
        }

        updateCalorieAllowance();

        System.out.println("Your daily calorie allowance is : " + user.getCalorieAllowance());
        System.out.println("Based on your diet plan, you will reach your weight goal in: "
                + user.getGoalLength() + " days");
        System.out.println("Setup complete! please log regularly to monitor your progress.");
    }

    private void updateCalorieAllowance() {
        user.setCalorieAllowance(cc.calculateCalorieAllowance(user));
    }

}

