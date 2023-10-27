package model;

import model.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// a class representing a food diary, with a name,
// a person , and list of entries
public class FoodDiary implements Writable {
    //Fields
    String name;// name of Diary
    Person user; //represents user of this diary
    List<Day> days; //represents entries
    CalorieCalculator cc;

    //Effects: creates a Food Diary with empty name,
    //empty user, empty list of days and a calorie calculator
    public FoodDiary() {
        this.name = null;
        this.user = null;
        days = new ArrayList<>();
        cc = new CalorieCalculator();
    }

    //Effects: Creates a Food Diary with name, and user as specified
    //,an empty list of days and a calorie calculator
    public FoodDiary(String name, Person user) {
        this.name = name;
        this.user = user;
        days = new ArrayList<>();
        cc = new CalorieCalculator();
    }

    //Requires : day is not null
    //Modifies: this
    //Effects: adds day to list of days
    public void addDay(Day day) {
        days.add(day);
    }

    //Requires : user is not null
    //Modifies : this
    //Effects : calculate a new calorie allowance for user
    public void updateCalorieAllowance() {
        int newCalorieAllowance = cc.calculateCalorieAllowance(user);
        user.setCalorieAllowance(newCalorieAllowance);
    }

    //getters

    //Effects :returns the name of Food Diary
    public String getName() {
        return name;
    }

    //Effects : returns the entries
    public List<Day> getDays() {
        return days;
    }

    //Effects: returns the user of food diary
    public Person getUser() {
        return user;
    }

    //EFFECTs: returns the number of entries
    public int getNumDays() {
        return days.size();
    }

    //setters

    //Requires: name is not empty
    //Modifies: this
    //Effects: sets the name of the food diary
    public void setName(String name) {
        this.name = name;
    }

    //Effects: returns a json object of this Food Diary
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("user",user.toJson());
        json.put("days",daysToJson());
        return json;
    }

    public JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Day day : days) {
            jsonArray.put(day.toJson());
        }
        return jsonArray;
    }

    //effects: returns true if there are no entries
    public boolean isEmpty() {
        return days.isEmpty();
    }
}
