package model;

import model.persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// a class representing a food diary, with a name,
// a person , and list of Day entries
public class FoodDiary implements Writable, Iterable<Day> {
    //Fields
    String name;// name of Diary
    Person user; //represents user of this diary
    List<Day> days; //represents entries
    CalorieCalculator cc;// represents calculator

    //EFFECTS: creates a Food Diary with empty name,
    //empty user, empty list of days and a calorie calculator
    public FoodDiary() {
        this.name = "";
        this.user = null;
        days = new ArrayList<>();
        cc = new CalorieCalculator();
    }

    //EFFECTS: Creates a Food Diary with name, and user as specified
    //an empty list of days and a calorie calculator
    public FoodDiary(String name, Person user) {
        this.name = name;
        this.user = user;
        days = new ArrayList<>();
        cc = new CalorieCalculator();
    }

    //MODIFIES: this
    //EFFECTS: adds day to food diary
    public void addDay(Day day) {
        days.add(day);
        EventLog.getInstance().logEvent(new Event(day + " is added to " + getName()));
    }

    //MODIFIES: this
    //EFFECTS: removes entries in daysToRemove from diary
    public void removeDays(Collection<Day> daysToRemove) {
        days.removeAll(daysToRemove);
        EventLog.getInstance().logEvent(new Event(daysToRemove + " is removed from " + getName()));
    }

    //REQUIRES : user is not null
    //MODIFIES : this
    //EFFECTS : calculate a new calorie allowance for user
    public void updateCalorieAllowance() {
        int newCalorieAllowance = cc.calculateCalorieAllowance(user);
        user.setCalorieAllowance(newCalorieAllowance);
    }

    //EFFECTS : returns the name of Food Diary
    public String getName() {
        return name;
    }

    //EFFECTS: returns the day entry corresponding to the index
    public Day getDay(int index) {
        return days.get(index);
    }

    //EFFECTS: returns the index corresponding to day
    public int indexOf(Day day) {
        return days.indexOf(day);
    }

    //EFFECTS: returns the user of food diary
    public Person getUser() {
        return user;
    }

    //EFFECTS: returns the number of entries
    public int getNumDays() {
        return days.size();
    }

    //EFFECTS: returns true if there are no entries
    public boolean isEmpty() {
        return days.isEmpty();
    }

    //EFFECTS: returns true if day is in diary, false if not
    public boolean contains(Day day) {
        return days.contains(day);
    }

    //setters

    //MODIFIES: this
    //EFFECTS: sets the name of the food diary
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Food Diary name is changed to " + getName()));
    }

    //EFFECTS: returns a json object of this Food Diary
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("user", user.toJson());
        json.put("days", daysToJson());
        return json;
    }

    //EFFECTS: returns a json array of the entries
    public JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Day day : days) {
            jsonArray.put(day.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns the iterator for our entries
    @Override
    public Iterator<Day> iterator() {
        return days.iterator();
    }
}
