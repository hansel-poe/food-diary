package model;

//Represents the activity level choices for the user,
//each specifies an activity level factor which can
//be multiplied with the bmr to get the total daily energy expenditure,
//the total amount of calories the user burns per day.
public enum ActivityLevel {
    SEDENTARY(1.2f),//little to no exercise
    LIGHT(1.375f),//exercise 1-3 times a week
    ACTIVE(1.55f),//daily exercise or
    VERY_ACTIVE(1.725f),
    EXTRA_ACTIVE(1.9f);

    private final float activityLevelFactor;

    ActivityLevel(float activityLevelFactor) {
        this.activityLevelFactor = activityLevelFactor;
    }

    public float getActivityLevelFactor() {
        return activityLevelFactor;
    }
}
