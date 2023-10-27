package model;

//Represents a calculator that calculates the daily calorie allowance for a person
//Has no fields
public class CalorieCalculator {

    //Effects: creates a new calorie calculator
    public CalorieCalculator() {
    }

    //Requires : Person is not null
    //Effects: Calculates the amount of daily calories allowed for weight loss
    public int calculateCalorieAllowance(Person person) {
        int bmr;
        int tdEE;

        if (person.getSex() == Sex.MALE) {
            bmr = calculateBmrMale(person);
        } else {
            bmr = calculateBmrFemale(person);
        }

        tdEE = calculatetdEE(person, bmr);

        return tdEE - person.getCaloriesCutPerDay();
    }

    //Effects: returns the BMR of person, if person is male
    private int calculateBmrMale(Person person) {
        return (Math.round(10.0f * person.getWeight() + 6.25f * person.getHeight() - 5.0f * person.getAge() + 5.0f));
        // this is the daily calories burnt from rest calculated using the Mifflin St Jeor equation
    }

    //Effects: returns the BMR of person, if person is female
    private int calculateBmrFemale(Person person) {
        return (Math.round(10.0f * person.getWeight() + 6.25f * person.getHeight() - 5.0f * person.getAge() - 161.0f));
        // this is the daily calories burnt from rest calculated using the Mifflin St Jeor equation
    }

    //Effects : returns the total daily energy expenditure of person
    private int calculatetdEE(Person person, int bmr) {
        return Math.round(bmr * person.getActivityLevelFactor());
        //this is the calories burnt taking into account daily activities
    }
}

