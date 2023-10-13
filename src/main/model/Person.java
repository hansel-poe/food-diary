package model;


import static java.lang.Boolean.TRUE;

//stores current personal health information for the user
public class Person {
    private static final String MALE = "Male"; //T
    private static final String FEMALE = "Female"; //F

    //Fields
    private String name;
    private int weight; // in kg
    private int height; //in cm
    private int age; //in years
    private boolean sex; //corresponds to sex specified above
    private int activityLevel; // ranges from 1 to 5

    public Person() {
        name = "";
        age = 0;
        weight = 0;
        height = 0;
        sex = TRUE;
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

    //Effects: returns Male if sex is true, Female is it is false;
    public String getSex() {
        if (sex) {
            return MALE;
        } else {
            return FEMALE;
        }
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


    public double getActivityLevelFactor() {
        switch (activityLevel) {
            case 1:
                return 1.2;
            case 2:
                return 1.375;
            case 3:
                return 1.55;
            case 4:
                return 1.725;
            case 5:
                return 1.9;
            default:
                throw new IllegalStateException("Unexpected value: " + activityLevel);
        }
    }
}
