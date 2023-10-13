package ui;

import model.CalorieCalculator;
import model.Day;
import model.Food;
import model.Person;

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

            if (option == 1) {
                addEntry();
            } else if (option == 2) {
                printEntry();
            } else if (option == 3) {
                printProfile();
            } else if (option == 4) {
                runs = false;
            } else {
                System.out.println("Invalid input, Please try again.");
            }
        }
        System.out.println("Thanks for using FoodDiary :)");
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
        System.out.println("Your entry : ");

        for (Day day : days) {
            System.out.println("Date: " + day.getDate());

            List<Food> foods = day.getFoods();
            for (Food food : foods) {
                System.out.println(food);
            }
            System.out.println("The total calories for the day: " + day.getTotalCalories());
            System.out.println("Calories allowed for this day: ");
            System.out.println("Calories Left: " + day.getCaloriesLeft());
        }
    }

    private void addEntry() {
        boolean logging = true;
        String userInput = null;

        System.out.println("Please enter a date: ");

        userInput = input.nextLine();

        Day day = new Day(userInput, user.getWeight(), user.getCalorieAllowance());

        System.out.println("You are logging for: " + userInput);

        while (logging) {
            String foodName;
            int calories;
            String foodType;
            String notes;

            System.out.println("Please enter the name of food: ");
            foodName = input.nextLine();
            System.out.println("Please enter the calories for this food: ");
            calories = input.nextInt();
            input.nextLine();

            System.out.println(" What type of meal is this? Breakfast, Lunch, Dinner, or Snack");
            foodType = input.nextLine();
            System.out.println("Enter some notes: ");
            notes = input.nextLine();

            day.addFood(foodName, calories, foodType, notes);

            System.out.println("select 1 to quit, 2 to continue logging");
            int option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                logging = false;
            }
        }
        days.add(day);
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

        if (userInfo == 1) {
            user.setSex(true);
        } else if (userInfo == 2) {
            user.setSex(false);
        } else {
            System.out.println("Error: Invalid option");
            exit(1);
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

        user.setActivityLevel(userInfo);

        System.out.println("Choose one of the diet plans below below: ");
        System.out.println("1. Lose 0.25 Kg per week");
        System.out.println("2. Lose 0.5 Kg per week");
        System.out.println("3. Lose 1 Kg per week");

        userInfo = input.nextInt();
        if (userInfo < 1 || userInfo > 3) {
            System.out.println("Error: Invalid input");
            exit(1);
        }
        user.setDietPlan(userInfo);

        updateCalorieAllowance();

        System.out.println("Your daily calorie allowance is : " + user.getCalorieAllowance());
        System.out.println("Based on your diet plan, you will reach your weight goal in: " + user.getGoalLength() + " days");
        System.out.println("Setup complete! please log regularly to monitor your progress.");
    }

    private void updateCalorieAllowance() {
        user.setCalorieAllowance(cc.calculateCalorieAllowance(user));
    }


}
