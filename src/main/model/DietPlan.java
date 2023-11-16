package model;

//Represents the possible options of diet plans,
//each specifying the amount of calories to be cut per day,
//and the weight shed off per week.
public enum DietPlan {
    PLAN1(250,0.25f, "Lose 0.25 kg per week"),
    PLAN2(500,0.5f, "Lose 0.5 kg per week"),
    PLAN3(1000,1.0f, "Lose 1 kg per week");

    // 0.45 kg is equal to about 3500 calories

    //Fields
    private final int caloriesCutPerDay;
    private final float weightLossPerWeek;//in kg
    private final String msg;

    //constructor
    DietPlan(int caloriesCutPerDay, float weightLossPerWeek, String msg) {
        this.caloriesCutPerDay = caloriesCutPerDay;
        this.weightLossPerWeek = weightLossPerWeek;
        this.msg = msg;
    }

    //Effects: returns the amount of calories cut per day
    public int getCaloriesCutPerDay() {
        return caloriesCutPerDay;
    }

    //Effects: returns the weight loss per week in kg
    public float getWeightLossPerWeek() {
        return weightLossPerWeek;
    }

    public String getMsg() {
        return msg;
    }

    //EFFECTS: returns the name of the enum appended with message
    @Override
    public String toString() {
        return super.toString() + ", " + getMsg();
    }
}
