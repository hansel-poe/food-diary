package model;

import model.exceptions.InvalidActivityLevelException;
import model.exceptions.InvalidDietPlanException;
import model.exceptions.InvalidSexException;

//stores profile info for user, containing personal health information,
// weight goal and other options specified by the user.
public class Person {

    //3 options of diet plans
    private static final int caloriesCutPerDay1 = 250; // loses 0.25kg per week //1
    private static final int caloriesCutPerDay2 = 500; // loses 0.5 kg per week //2
    private static final int caloriesCutPerDay3 = 1000; // loses 1 kg per week //3
    int dietPlan; //ranges from 1 to 3, determines the calories cut per day for the person

    private String name;//name of person
    private String sex; //influences how we calculate our daily calorie allowance

    private int weight; // in kg
    private int height; //in cm
    private int age; //in years

    private int activityLevel; // ranges from 1 to 5, each corresponds to a particular activity level factor

    private int calorieAllowance; //Daily calorie allowance for weight loss // attained from calorieCalculator class
    private int weightGoal; // this is the targeted weight ,used to calculate how long it takes to reach this goal

    public Person() {
        name = "";
        sex = "Male";
        age = 0;
        activityLevel = 1;
        weight = 0;
        height = 0;
        calorieAllowance = 0;
        weightGoal = 0;
        dietPlan = 1;
    }

    //Effects: returns calories cut per day according to diet plan
    public int getCaloriesCutPerDay() {
        if (dietPlan == 1) {
            return caloriesCutPerDay1;
        } else if (dietPlan == 2) {
            return caloriesCutPerDay2;
        } else {
            return caloriesCutPerDay3;
        }
    }


    //Effects: returns number of days required to lose weight
    // according to diet plan,
    public int getGoalLength() {
        int goalLength;
        int weightToLose;

        if (weightGoal < weight) {
            weightToLose = weight - weightGoal;
        } else {
            weightToLose = 0;
        }

        if (dietPlan == 1) {
            goalLength = (int) (weightToLose / 0.25 * 7);
        } else if (dietPlan == 2) {
            goalLength = (int) (weightToLose / 0.5 * 7);
        } else {
            goalLength = (weightToLose / 1 * 7);
        }
        return goalLength;
    }

    //Effects: returns activityLevelFactor based on activityLevel if activityLevel is not 1 to 5, returns 0
    public float getActivityLevelFactor() {
        switch (activityLevel) {
            case 1:
                return 1.2f;
            case 2:
                return 1.375f;
            case 3:
                return 1.55f;
            case 4:
                return 1.725f;
            case 5:
                return 1.9f;
        }
        return 0;
    }

    //getter functions
    public String getName() {
        return name;
    }

    public String getSex() {
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

    //Setter functions
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSex(String sex) throws InvalidSexException {
        if (!isValidSex(sex)) {
            throw new InvalidSexException();
        }
        this.sex = sex;
    }

    private boolean isValidSex(String sex) {
        return (sex.equals("Male") || sex.equals("Female"));
    }


    public void setCalorieAllowance(int calorieAllowance) {
        this.calorieAllowance = calorieAllowance;
    }

    //Modifies : this
    public void setWeightGoal(int weightGoal) {
        this.weightGoal = weightGoal;
    }

    //modifies: this
    //effects: set activitylevel
    public void setActivityLevel(int activityLevel) throws InvalidActivityLevelException {
        if (activityLevel < 1 || activityLevel > 5) {
            throw new InvalidActivityLevelException();
        }
        this.activityLevel = activityLevel;
    }

    //Modifies : this
    //Effects : set choice to dietplan
    public void setDietPlan(int choice) throws InvalidDietPlanException {
        if (choice < 1 || choice > 3) {
            throw new InvalidDietPlanException();
        }
        this.dietPlan = choice;
    }
}



