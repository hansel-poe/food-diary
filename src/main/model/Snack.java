package model;

//A class representing a Snack item, has all methods and fields from Food
public class Snack extends Food {

    //Default constructor
    public Snack() {
        super();
    }

    //Constructor with parameters, calls superclass
    public Snack(String name, int calories, String notes) {
        super(name, calories, notes);
    }

    //Meal type Snack
    private static final String SNACK = "Snack";

    //Effects: returns meal type
    @Override
    public String getMealType() {
        return SNACK;
    }
}
