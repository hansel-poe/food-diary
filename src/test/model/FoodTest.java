package model;

import model.exceptions.InvalidMealTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FoodTest {

    //Fields
    Food testFood1;
    Food testFood2;
    Food testFood3;

    @BeforeEach
    //Sets each variable to corresponding subtypes
    void setup() {
        testFood1 = new Food("Toast", 100,"Breakfast", "Peanut Butter Spread");
        testFood2 = new Food("Steak", 200, "Dinner", "Ribeye");
        testFood3 = new Food(testFood1);
    }

    //tests constructor
    @Test
    void testConstructor(){
    assertEquals("Toast", testFood1.getName());
    assertEquals(100, testFood1.getCalories());
    assertEquals("Peanut Butter Spread", testFood1.getNotes());
    assertEquals ("Breakfast", testFood1.getMealType());
    }

    //tests the copy constructor
    @Test
    void testCopyConstructor() {
        assertEquals("Toast", testFood3.getName());
        assertEquals(100, testFood3.getCalories());
        assertEquals("Peanut Butter Spread", testFood3.getNotes());
        assertEquals ("Breakfast", testFood3.getMealType());
    }

    @Test
    void testSetMealTypeNoException() {
        try {
            testFood2.setMealType("Breakfast");
            testFood2.setMealType("Lunch");
            testFood2.setMealType("Dinner");
            testFood2.setMealType("Snack");
        } catch (InvalidMealTypeException e) {
            fail("Exception caught, no exception is expected");
        }
        assertEquals("Snack",testFood2.getMealType());
    }

    @Test
    void testSetMealTypeException() {

    }
}
