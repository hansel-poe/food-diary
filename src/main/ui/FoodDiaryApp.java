package ui;

import model.*;
import model.persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//DEPRECATED, NOT INCLUDED IN UML DIAGRAM
public class FoodDiaryApp {

    //Save file destination
    private static final String JSON_STORE = "./data/FoodDiary.json";
    //Declaring fields
    private FoodDiary foodDiary;
    private Scanner input;
    private JsonWriter writer;
    private JsonReader reader;

    //Effects: Initializes variables and runs the FoodDiary app
    public FoodDiaryApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);

        runFoodDiary();
    }

    //Modifies: this
    //Effects: runs Food diary App
    private void runFoodDiary() {
        boolean runs = true;

        loadOption();
        while (runs) {
            displayMenu();

            String option = null;
            option = readString();

            option = option.toLowerCase();
            option = option.trim();

            if (option.equals("q")) {
                runs = false;
            } else {
                processOption(option);
            }
        }
        System.out.println("Thanks for using FoodDiary :D");
    }

    private void loadOption() {
        System.out.println("\nHi!, welcome to FoodDiary, create a new FoodDiary or load an existing one.");
        System.out.println("\tn -> New FoodDiary");
        System.out.println("\tl -> Load an Existing FoodDiary\n");

        String option = readString();
        option = option.toLowerCase();
        option = option.trim();

        if (option.equals("n")) {
            setup();
        } else if (option.equals("l")) {
            loadFoodDiary();
        } else {
            System.out.println("Invalid input, Please try again");
            loadOption();
        }
    }

    private void processOption(String option) {
        if (option.equals("l")) {
            addEntry();
        } else if (option.equals("e")) {
            printEntry();
        } else if (option.equals("p")) {
            printProfile();
        } else if (option.equals("s")) {
            saveFoodDiary();
        } else if (option.equals("r")) {
            loadFoodDiary();
        } else {
            System.out.println("Invalid input, Please try again.");
        }
    }

    //Modifies: this
    //Effects: loads FoodDiary from JSON_STORE
    private void loadFoodDiary() {
        try {
            foodDiary = reader.read();
            System.out.println("Loaded " + foodDiary.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Load failed: cannot load FoodDiary from: " + JSON_STORE);
        }
    }

    //Modifies: this
    //Effects: saves FoodDiary to JSON_STORE
    private void saveFoodDiary() {
        try {
            writer.open();
            writer.write(foodDiary);
            writer.close();
            System.out.println("Saved : " + foodDiary.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Save failed: cannot save to " + JSON_STORE);
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select an option: ");
        System.out.println("\tl -> Log an entry");
        System.out.println("\te -> View all entries");
        System.out.println("\tp -> View Profile");
        System.out.println("\ts -> Save Food Diary");
        System.out.println("\tr -> Load Food Diary");
        System.out.println("\tq -> Quit app\n");
    }

    //Effects: prints the user profile
    private void printProfile() {
        Person user = foodDiary.getUser();
        System.out.println("Name: " + user.getName());
        System.out.println("Sex: " + user.getSex());
        System.out.println("Age: " + user.getAge() + " years");
        System.out.println("Height: " + user.getHeight() + " cm");
        System.out.println("Weight: " + user.getWeight() + " kg");
        System.out.println("Weight goal: " + user.getWeightGoal());
        System.out.println("Activity level: " + user.getActivityLevel());
        System.out.println("Diet plan: " + user.getDietPlan() + ", " + user.getDietPlan().getMsg());
        System.out.println("Daily Calorie Allowance: " + user.getCalorieAllowance());
        System.out.println("You will reach your goal in: " + user.getGoalLength() + " days");
    }

    private void printEntry() {
        System.out.println("You have " + foodDiary.getNumDays() + " entries");
        System.out.println("Your entries :\n");

        for (Day day : foodDiary) {
            printDay(day);
        }
        System.out.println("\n End of entries");
    }

    private void printDay(Day day) {
        System.out.println(day.getDate());

        printBreakfast(day);
        printLunch(day);
        printDinner(day);
        printSnack(day);

        System.out.println("The total calories for the day: " + day.getTotalCalories());
        System.out.println("Calories allowed for this day: " + day.getCaloriesAllowed());
        System.out.println("Calories Left: " + day.getCaloriesLeft() + "\n");
    }

    private void printBreakfast(Day day) {
        System.out.println("Breakfast:");

        for (Food food : day) {
            if (food.getMealType() == MealType.BREAKFAST) {
                System.out.println(food);
            }
        }
    }

    private void printLunch(Day day) {
        System.out.println("Lunch:");

        for (Food food : day) {
            if (food.getMealType() == MealType.LUNCH) {
                System.out.println(food.toString());
            }
        }
    }

    private void printDinner(Day day) {
        System.out.println("Dinner:");

        for (Food food : day) {
            if (food.getMealType() == MealType.DINNER) {
                System.out.println(food.toString());
            }
        }
    }

    private void printSnack(Day day) {
        System.out.println("Snack:");

        for (Food food : day) {
            if (food.getMealType() == MealType.SNACK) {
                System.out.println(food.toString());
            }
        }
    }

    private void addEntry() {
        boolean logging = true;
        String userInput = null;
        String option;

        System.out.println("Please enter a date: ");

        userInput = readString();
        Day day = new Day(userInput, foodDiary.getUser().getWeight(),
                foodDiary.getUser().getCalorieAllowance());
        System.out.println("You are logging for: " + userInput);

        while (logging) {
            logFood(day);

            System.out.println("select q to quit, any other key to continue logging");
            option = readString();
            option = option.toLowerCase();

            if (option.equals("q")) {
                break;
            }
        }
        foodDiary.addDay(day);
    }

    private void logFood(Day day) {
        String foodName;
        int calories;
        MealType foodType;
        String notes;

        System.out.println("Please enter the name of food: ");
        foodName = readString();

        System.out.println("Please enter the calories for this food: ");
        calories = readValues();

        foodType = readMealType();

        System.out.println("Enter some notes: ");
        notes = readString();

        Food food = null;
        food = new Food(foodName, calories, foodType, notes);
        day.addFood(food);
    }

    //Read Functions each deals with a specific type of user input
    private String readString() {
        String userInput;
        userInput = input.nextLine();
        return userInput;
    }

    private int readValues() {
        int userInput;
        userInput = input.nextInt();
        input.nextLine(); //consumes \n
        return userInput;
    }

    private Sex readSex() {
        System.out.println("Please indicate your sex:");

        int menuLabel = 1;
        for (Sex s : Sex.values()) {
            System.out.println(menuLabel + ": " + s);
            menuLabel++;
        }

        int choice = input.nextInt();
        input.nextLine();
        return Sex.values()[choice - 1];
    }

    private ActivityLevel readActivityLevel() {
        System.out.println("Please indicate your activity level:");

        int menuLabel = 1;
        for (ActivityLevel l : ActivityLevel.values()) {
            System.out.println(menuLabel + ": " + l);
            menuLabel++;
        }

        int choice = input.nextInt();
        input.nextLine();
        return ActivityLevel.values()[choice - 1];
    }

    private DietPlan readDietPlan() {
        System.out.println("Please pick a diet plan for one of the ");

        int menuLabel = 1;
        for (DietPlan p : DietPlan.values()) {
            System.out.println(menuLabel + ": " + p + ", " + p.getMsg());
            menuLabel++;
        }
        int choice = input.nextInt();
        input.nextLine();
        return DietPlan.values()[choice - 1];
    }

    private MealType readMealType() {
        System.out.println("What type of meal is this?");

        int menuLabel = 1;
        for (MealType t : MealType.values()) {
            System.out.println(menuLabel + ": " + t);
            menuLabel++;
        }

        int choice = input.nextInt();
        input.nextLine();
        return MealType.values()[choice - 1];
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setup() {
        String userName;
        Sex sex;
        int age;
        int weight;
        int height;
        int weightGoal;
        ActivityLevel activityLevel;
        DietPlan dietPlan;

        System.out.println("Let's setup your user profile!");
        System.out.println("Please enter your name:");

        userName = readString();
        System.out.println("Hi " + userName + " ,just a few more steps to get you started!");

        sex = readSex();

        System.out.println("Please enter your age:");
        age = readValues();

        System.out.println("Please enter your weight in kg:");
        weight = readValues();

        System.out.println("Please enter your height in cm:");
        height = readValues();

        System.out.println("Please enter your weight goal in kg:");
        weightGoal = readValues();

        activityLevel = readActivityLevel();
        dietPlan = readDietPlan();

        Person user = new Person();
        user.setName(userName);
        user.setSex(sex);
        user.setAge(age);
        user.setWeight(weight);
        user.setHeight(height);
        user.setWeightGoal(weightGoal);
        user.setActivityLevel(activityLevel);
        user.setDietPlan(dietPlan);

        foodDiary = new FoodDiary(userName + "'s FoodDiary", user);
        foodDiary.updateCalorieAllowance();

        System.out.println("You are done!! Here's a look at your profile:");
        printProfile();
    }

}

