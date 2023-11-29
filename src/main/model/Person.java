package model;

import model.persistence.Writable;
import org.json.JSONObject;

//stores profile info for user, containing personal health information
// weight goal and other options specified by the user.
public class Person implements Writable {

    private String name;//name of person
    private Sex sex; //Determines the formula used to calculate bmr
    private int age; //in years
    private int weight; // in kg
    private int height; //in cm

    private int calorieAllowance; //Daily calorie allowance
                                  // for weight loss // attained from calorieCalculator class
    private int weightGoal; // this is the targeted weight ,
                            // used to calculate how long it takes to reach this goal

    private ActivityLevel activityLevel; //used to calculate total daily energy expenditure,
    //calories burnt per day accounting for daily activities

    private DietPlan dietPlan; //3 options of diet plans are given,
    //each specifies a particular amount of calories to cut per day
    //which determines how much weight will be lost per week

    //EFFECTS: creates a new person with an empty name, sex specified as male,
    // diet plan specified as plan 1, and activity level specified as sedentary
    // and with age, height, weight, calorie allowance, weight goal all set to zero
    public Person() {
        name = "";
        sex = Sex.MALE;
        age = 0;
        weight = 0;
        height = 0;

        calorieAllowance = 0;
        weightGoal = 0;

        activityLevel = ActivityLevel.SEDENTARY;
        dietPlan = DietPlan.PLAN1;
    }

    //Effects: returns calories cut per day according to diet plan
    public int getCaloriesCutPerDay() {
        return dietPlan.getCaloriesCutPerDay();
    }

    //Effects: returns number of days required to reach weight goal according to diet plan
    //If (weightGoal > weight) returns 0
    public int getGoalLength() {
        int goalLength = 0;
        int weightToLose;

        if (weightGoal < weight) {
            weightToLose = weight - weightGoal;
        } else {
            weightToLose = 0;
        }

        goalLength = (int) (weightToLose / dietPlan.getWeightLossPerWeek() * 7);

        return goalLength;
    }

    //Effects: returns the activity level factor based on
    //the activity Level of this person
    public float getActivityLevelFactor() {
        return activityLevel.getActivityLevelFactor();
    }

    //getter functions
    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public int getCalorieAllowance() {
        return calorieAllowance;
    }

    public int getWeightGoal() {
        return weightGoal;
    }

    public DietPlan getDietPlan() {
        return dietPlan;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }


    //Setter functions

    //Requires: name is not empty
    //Modifies: this
    //Effefcts: set name
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Profile name is set to " + getName()));
    }

    //Requires: sex is not null
    //Modifies: this
    //Effects: set sex
    public void setSex(Sex sex) {
        this.sex = sex;
        EventLog.getInstance().logEvent(new Event("Profile sex is set to " + getSex()));
    }

    //Requires: age > 0
    //Modifies: this
    //Effects: set age
    public void setAge(int age) {
        this.age = age;
        EventLog.getInstance().logEvent(new Event("Profile age is set to " + getAge()));
    }

    //Requires: weight > 0
    //Modifies: this
    //Effects: set weight
    public void setWeight(int weight) {
        this.weight = weight;
        EventLog.getInstance().logEvent(new Event("Profile weight is set to " + getWeight()));
    }

    //Requires: height > 0
    //Modifies: this
    //Effects: set height
    public void setHeight(int height) {
        this.height = height;
        EventLog.getInstance().logEvent(new Event("Profile height is set to " + getHeight()));
    }

    //Requires: calorieAllowance > 0
    //Modifies: this
    //Effects: set the daily calorie allowance
    public void setCalorieAllowance(int calorieAllowance) {
        this.calorieAllowance = calorieAllowance;
        EventLog.getInstance().logEvent(new Event("Profile calorie allowance is set to " + getCalorieAllowance()));
    }

    //
    //Modifies : this
    public void setWeightGoal(int weightGoal) {
        this.weightGoal = weightGoal;
        EventLog.getInstance().logEvent(new Event("Profile weight goal is set to " + getWeightGoal()));
    }

    //Requires: activityLevel is not null
    //Modifies: this
    //Effects: sets the activity level for this person
    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
        EventLog.getInstance().logEvent(new Event("Profile activity level is set to " + getActivityLevel()));
    }

    //Requires: choice is not null
    //Modifies : this
    //Effects : sets the diet plan
    public void setDietPlan(DietPlan choice) {
        this.dietPlan = choice;
        EventLog.getInstance().logEvent(new Event("Profile diet plan is set to " + getDietPlan()));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sex", sex);
        json.put("age", age);
        json.put("weight", weight);
        json.put("height", height);
        json.put("calorie allowance", calorieAllowance);
        json.put("weight goal", weightGoal);
        json.put("activity level", activityLevel);
        json.put("diet plan", dietPlan);

        return json;
    }
}



