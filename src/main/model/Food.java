package model;

//A class representing a food item containing information such as its name, calories, meal type, and notes
public class Food {
    //Fields
    private String name; //name of food
    private int calories; //How much calories this food contains
    private MealType mealType; //the type of meal this food belongs to,
                               // has to be one of breakfast, lunch, dinner, or snack
    private String notes; //User notes on this particular item, can be used to indicate quantity of food

    //Default constructor, creates a food item with no name, 0 calories, Breakfast MealType and no notes
    public Food() {
        name = "";
        calories = 0;
        mealType = null;
        notes = "";
    }

    //Copy Constructor
    //Effects: creates a new Food item based on food argument
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

    //Effects : Returns a string containing food
    public String toString() {
        return "[" + getName() + ", " + getCalories() + " cal, " + getNotes() +"]";
    }


}
