package model;

import model.persistence.Writable;
import org.json.JSONObject;

//A class representing a food item containing information such as its name, calories, meal type, and notes
public class Food implements Writable {
    //Fields
    private String name; //name of food
    private int calories; //How much calories this food contains
    private MealType mealType; //the type of meal this food belongs to,
    //see MealType Enum for more info
    private String notes; //User notes on this particular food item

    //Default constructor, creates a food item with empty name,
    //0 calories, Breakfast MealType and empty notes
    public Food() {
        name = "";
        calories = 0;
        mealType = MealType.BREAKFAST;
        notes = "";
    }

    //Copy Constructor
    //Effects: creates a new Food item with fields copied from food
    public Food(Food food) {
        this.name = food.name;
        this.calories = food.calories;
        this.mealType = food.mealType;
        this.notes = food.notes;
    }

    //Requires: name is not empty and calories > 0
    //Effects: creates a food item with name, calories, and notes as specified
    public Food(String name, int calories, MealType mealType, String notes) {
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

    public MealType getMealType() {
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
    //Effects: sets the meal type, to one of the MealType values
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    //Effects : prints food item
    public String toString() {
        return "[" + getName() + ", " + getCalories() + " cal, " + getNotes() + "]";
    }

    //Effects: returns a JSONObject based on this food
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        json.put("meal type", mealType);
        json.put("notes", notes);

        return json;
    }
}
