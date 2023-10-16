package model;

import java.util.ArrayList;
import java.util.List;

//A class representing a day entry containing a list of Foods eaten that day
public class Day {

    //Fields
    private List<Food> foods; //represents a List of Food items
    private int totalCalories; //totalcalories consumed for the day;

    private String date; //represents the name of the day
    private int caloriesAllowed; //total Calories allowed for this day
    private int weight; //weight for this particular day

    //Default constructor
    public Day() {
        this.date = "";
        this.weight = 0;
        this.caloriesAllowed = 0;
        totalCalories = 0;
        foods = new ArrayList<>();
    }

    //Effects: creates a Day entry with weight set to weight and calories Allowed set to
    // caloriesAllowed, totalcalories = 0, and empty food List
    public Day(String date, int weight, int caloriesAllowed) {
        this.date = date;
        this.weight = weight;
        this.caloriesAllowed = caloriesAllowed;

        totalCalories = 0;
        foods = new ArrayList<Food>();
    }

    //Requires : name is not empty, calories > 0,and mealType is one of "Breakfast", "Snack", "Lunch", or "Dinner"
    //Modifies: this
    //Effects: adds a food item to the list of foods in the day, updates total calories
    public void addFood(String name, int calories, String mealType, String notes) {
        Food food;

        if (mealType == "Breakfast") {
            food = new Breakfast();
        } else if (mealType == "Lunch") {
            food = new Lunch();
        } else if (mealType == "Dinner") {
            food = new Dinner();
        } else {
            food = new Snack();
        }

        food.setName(name);
        food.setCalories(calories);
        food.setNotes(notes);

        foods.add(food);
        updateTotalCalories();
    }


    //Modifies : this
    //Effects: removes food with the name name in the order they are added,
    // if no food with name is found, nothing happens. Updates total calories

    public void removeFood(String name) {
        for (Food food : foods) {
            if (food.getName() == name) {
                foods.remove(food);
                break;
            }
        }
        updateTotalCalories();
    }

    //Modifies : this
    //Effects : adds up all total calories from food to get total calories
    public void updateTotalCalories() {

        totalCalories = 0; // resets to recalculate

        for (Food food : foods) {
            totalCalories += food.getCalories();
        }
    }

    //getters

    public String getDate() {
        return date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getWeight() {
        return weight;
    }

    public int getCaloriesAllowed() {
        return caloriesAllowed;
    }

    //Effects: returns amount calorie allowance left for the day
    public int getCaloriesLeft() {
        if (caloriesAllowed - totalCalories > 0) {
            return caloriesAllowed - totalCalories;
        } else {
            return 0;
        }
    }

    public List<Food> getFoods() {
        return foods;
    }

    //setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCaloriesAllowed(int caloriesAllowed) {
        this.caloriesAllowed = caloriesAllowed;
    }

    //Effects: prints food lists
    public String toString() {
        String message = null;
        message = "Date: " + date + "\n";

        message += getMeals("Breakfast");
        message += getMeals("Lunch");
        message += getMeals("Dinner");
        message += getMeals("Snack");

        return message;
    }


    private String getMeals(String mealType) {
        String list;

        list = mealType + "\n";

        for (Food food : foods) {
            if (food.getMealType() == mealType) {
                list = list + food.toString() + "\n";
            }
        }
        return list;
    }

}

