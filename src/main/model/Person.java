package model;


import static java.lang.Boolean.TRUE;

//stores profile info for user, containing personal health information, weight goal and etc.
public class Person {

    //Fields
    private static final String MALE = "Male"; //T
    private static final String FEMALE = "Female"; //F

    //3 options of diet plans
    private static final int caloriesCutPerDay1 = 250; // loses 0.25kg per week //1
    private static final int caloriesCutPerDay2 = 500; // loses 0.5 kg per week //2
    private static final int caloriesCutPerDay3 = 1000; // loses 1 kg per week //3
    private int dietPlan; // corresponds to calories cut per day above

    private String name;
    private int weight; // in kg
    private int height; //in cm
    private int age; //in years
    private boolean sex; //corresponds to sex specified above

    private int activityLevel; // ranges from 1 to 5, each corresponds to a particular activity level factor

    private int calorieAllowance; //Daily calorie allowance for weight loss // attained from calorieCalculator class
    private int weightGoal; // this is the targeted weight ,used to calculate how long it takes to reach this goal

    public Person() {
        name = "";
        age = 0;
        weight = 0;
        height = 0;
        sex = TRUE;
        calorieAllowance = 0;
        weightGoal = 0;
    }

    //Effects: returns calories cut per day according to diet plan if diet plan is not 1,2 or 3, returns 0
    public int getCaloriesCutPerDay() {
        if (dietPlan == 1) {
            return caloriesCutPerDay1;
        } else if (dietPlan == 2) {
            return caloriesCutPerDay2;
        } else if (dietPlan == 3) {
            return caloriesCutPerDay3;
        } else {
            return 0;
        }
    }

    //Effects: returns number of days required to lose weight according to diet plan, if no plan is selected, returns 0;
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
        } else if (dietPlan == 3) {
            goalLength = (weightToLose / 1 * 7);
        } else {
            return 0;
        }
        return goalLength;
    }

    //Effects: returns Male if sex is true, Female is it is false;
    public String getSex() {
        if (sex) {
            return MALE;
        } else {
            return FEMALE;
        }
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

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setCalorieAllowance(int calorieAllowance) {
        this.calorieAllowance = calorieAllowance;
    }

    //Modifies : this
    public void setWeightGoal(int weightGoal) {
        this.weightGoal = weightGoal;
    }

    //Requires : 1<= activityLevel <= 5
    //modifies: this
    //effects: set activitylevel
    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    //Requires : 1 <= choice <= 3
    //Modifies : this
    //Effects : set choice to dietplan
    public void setDietPlan(int choice) {
        this.dietPlan = choice;
    }

}

