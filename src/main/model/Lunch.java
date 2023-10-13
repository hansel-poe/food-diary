package model;

//A class that represents a Lunch item, has all methods and fields from Food.
public class Lunch extends Food {

    //Default constructor
    public Lunch() {
        super();
    }

    //Constructor with parameters, calls superclass
    public Lunch(String name, int calories, String notes) {
        super(name, calories, notes);
    }

    //Meal type Lunch
    private static final String LUNCH = "Lunch";

    //Effects: returns meal type
    @Override
    public String getMealType() {
        return LUNCH;
    }
}
