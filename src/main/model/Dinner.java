package model;

//A Class that represents a Dinner item, has all methods and fields in Food
public class Dinner extends Food {

    //Default constructor
    public Dinner() {
        super();
    }

    //Constructor with parameters, calls superclass
    public Dinner(String name, int calories, String notes) {
        super(name, calories, notes);

    }

    //Meal type Dinner
    private static final String DINNER = "Dinner";

    //Effects: returns meal type
    @Override
    public String getMealType() {
        return DINNER;
    }
}
