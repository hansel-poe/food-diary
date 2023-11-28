package model;

import model.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//A class representing a day entry containing a list of food items,
// each day entry also records the weight of the user and calories allowed.
// It also keeps track of the total calories consumed for the day
public class Day implements Writable, Iterable<Food> {

    //Fields
    private List<Food> foods; //represents a List of Food items
    private String date; //represents the name of the day

    private int caloriesAllowed; //total Calories allowed for this day
    private int weight; //weight for this day

    //Default constructor
    //EFFECTS: : constructs a day entry with empty date, 0 weight,
    // 0 calories allowed, 0 total calories, and empty list of food
    public Day() {
        this.date = "";
        this.weight = 0;
        this.caloriesAllowed = 0;
        this.foods = new ArrayList<>();
    }

    //EFFECTS: creates a Day entry with weight set to weight and calories allowed set to
    // caloriesAllowed, total calories = 0, and empty food List
    public Day(String date, int weight, int caloriesAllowed) {
        this.date = date;
        this.weight = weight;
        this.caloriesAllowed = caloriesAllowed;

        foods = new ArrayList<Food>();
    }

    //MODIFIES: this
    //EFFECTS: adds a food item to the list of foods in the day
    public void addFood(Food food) {
        foods.add(food);
    }

    //I have decided to remove this method and replace it with the one that accepts a Food param
    /*//Modifies : this
    //Effects: removes food with name and which belongs to mealType
    // if no food with name and mealType is found, nothing happens.
    // Updates total calories
    public void removeFood(String name, MealType mealType) {
        for (Food food : foods) {
            if (food.getName().equals(name) && food.getMealType() == mealType) {
                foods.remove(food);
                break;
            }
        }
    }*/

    //MODIFIES: this
    //EFFECTS: Removes food from day, if food is in day
                //if not then no changes are made
    public void removeFood(Food food) {
        foods.remove(food);
    }

    //MODIFIES: this
    //EFFECTS: removes all the foods in foodsToRemove from day
    public void removeAll(Collection<Food> foodsToRemove) {
        foods.removeAll(foodsToRemove);
    }

    //EFFECTS: returns the food corresponding to index, IndexOutOfBoundsExcpetion will be thrown if index >
    public Food getFood(int index) {
        return foods.get(index);
    }

    //EFFECTS: returns true if day contains food, false if not
    public boolean contains(Food food) {
        return foods.contains(food);
    }

    //getters
    public String getDate() {
        return date;
    }

    //EFFECTS: calculates and returns total calories of all food items
    //returns zero if foods is empty
    public int getTotalCalories() {
        int totalCalories = 0;
        for (Food food : foods) {
            totalCalories += food.getCalories();
        }
        return totalCalories;
    }

    public int getWeight() {
        return weight;
    }

    public int getCaloriesAllowed() {
        return caloriesAllowed;
    }

    //EFFECTS: returns the index corresponding to food
    public int indexOf(Food food) {
        return foods.indexOf(food);
    }

    //EFFECTS: returns amount calorie allowance left for the day,
    //if total calories exceed the calories allowed, return 0
    public int getCaloriesLeft() {
        int totalCalories = getTotalCalories();
        if (caloriesAllowed - totalCalories <= 0) {
            return 0;
        } else {
            return (caloriesAllowed - totalCalories);
        }
    }

    //EFFECTS: returns the number of food items
    public int getNumFoods() {
        return foods.size();
    }

    //EFFECTS: returns true if there is no food item
    public boolean isEmpty() {
        return foods.isEmpty();
    }

    //setters

    //MODIFIES: this
    //EFFECTS: Sets the date of day entry to value specified
    public void setDate(String date) {
        this.date = date;
    }

    //MODIFIES: this
    //EFFECTS: Sets the weight for this day entry to value specified
    public void setWeight(int weight) {
        this.weight = weight;
    }

    //MODIFIES: this
    //EFFECTS: Sets the Calories allowed to the value specified
    public void setCaloriesAllowed(int caloriesAllowed) {
        this.caloriesAllowed = caloriesAllowed;
    }

    //EFFECTS: Returns a JSON object for this day object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("date", date);
        json.put("weight", weight);
        json.put("calories allowed", caloriesAllowed);
        json.put("foods", foodsToJson());

        return json;
    }

    //EFFECTS: Returns a JSONArray of foods
    public JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Food food : foods) {
            jsonArray.put(food.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns the iterator for foods
    @Override
    public Iterator<Food> iterator() {
        return foods.iterator();
    }
}

