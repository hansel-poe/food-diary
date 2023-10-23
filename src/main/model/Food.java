package model;

import model.exceptions.InvalidMealTypeException;

//A class representing a food item with information about its calories, meal type, and notes
public class Food {
    //Fields
    private String name; //name of food
    private int calories; //How much calories this food contains
    private String mealType; //the type of meal this food belongs to,has to be one of breakfast, lunch, dinner, or snack
    private String notes; //User notes on this particular item, can be used to indicate quantity of food

    //Default constructor, creates a food item with no name, 0 calories, and no notes
    public Food() {
        name = " ";
        calories = 0;
        mealType = "Breakfast";
        notes = "";
    }

    //Copy Constructor
    //Effects: creates a new Food item based on food
    public Food(Food food) {
        this.name = food.name;
        this.calories = food.calories;
        this.mealType = food.mealType;
        this.notes = food.notes;
    }

    //Requires: name is not empty and calories > 0
    //Effects: creates a food item with name, calories, and notes as specified
    public Food(String name, int calories, String mealType, String notes) throws InvalidMealTypeException {
        if (!isValidMealType(mealType)) {
            throw new InvalidMealTypeException();
        }
        this.name = name;
        this.calories = calories;
        this.mealType = mealType;
        this.notes = notes;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public String getNotes() {
        return notes;
    }

    public String getMealType() {
        return mealType;
    }

    //setters

    //Requires: calories > 0
    //Modifies: this
    //Effects: set the amount of calories that Food contains to calories
    public void setCalories(int calories) {
        this.calories = calories;
    }

    //Requires: name is not empty
    //Modifies: this
    //Effects: sets the name of Food to name
    public void setName(String name) {
        this.name = name;
    }

    //Modifies : this
    //Effects: adjusts user notes to notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    //Modifies : this
    //Effects: sets the meal type, throws an exception if mealType is invalid
    public void setMealType(String mealType) throws InvalidMealTypeException {
        if (!isValidMealType(mealType)) {
            throw new InvalidMealTypeException();
        }
        this.mealType = mealType;
    }

    //Effects: returns true if mealType is one of "Breakfast", "Lunch", "Dinner", or "Snack"
    // false otherwise
    private boolean isValidMealType(String mealType) {
        return mealType.equals("Breakfast") || mealType.equals("Lunch")
                || mealType.equals("Dinner") || mealType.equals("Snack");
    }

    //Effects : prints the name of food, its calories, and notes about this food
    public String toString() {
        return ("[" + name + ", cal = " + calories + ", " + notes + "]");
    }
}
