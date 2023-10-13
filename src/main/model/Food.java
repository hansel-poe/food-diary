package model;

//A class representing a food item with information about its calories, etc.
public abstract class Food {

    //Fields
    protected String name; //name of food
    protected int calories; //How much calories this food contains
    protected String notes; //User notes on this particular item, can be used to indicate quantity of food

    //Default constructor
    public Food() {
        name = "";
        calories = 0;
        notes = "";
    }

    //Requires: name is not empty and calories > 0
    //Effects: setting name, calories to calories, notes to notes
    public Food(String name, int calories, String notes) {
        this.name = name;
        this.calories = calories;
        this.notes = notes;
    }

    //getters
    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    //Effects: returns meal types according to actual type
    public abstract String getMealType();

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

    public String toString() {
        return ("[ " + name + ", cal = " + calories + ", " + notes + " ]");
    }
}
