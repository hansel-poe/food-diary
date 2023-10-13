package model;

//A class representing a Breakfast item with all the fields and methods of food
public class Breakfast extends Food {

    //Default constructor
    public Breakfast() {
        super();
    }

    //Constructor with parameters, calls superclass
    public Breakfast(String name, int calories, String notes) {
        super(name, calories, notes);
    }

    //Meal type breakfast
    private static final String BREAKFAST = "Breakfast"; //1

    //Effects: returns meal type
    @Override
    public String getMealType() {
        return BREAKFAST;
    }


}


