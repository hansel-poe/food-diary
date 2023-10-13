package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {

    //Fields
    Food testBreakfast;
    Food testLunch;
    Food testDinner;
    Food testSnack;

    @BeforeEach
    //Sets each variable to corresponding subtypes
    void setup() {
        testBreakfast = new Breakfast("Toast", 120, "Peanut Butter Spread");
        testLunch = new Lunch();
        testDinner = new Dinner();
        testSnack = new Snack();
    }

    //tests constructors
    @Test
    void testConstructor(){
    assertEquals("Toast", testBreakfast.getName());
    assertEquals(120, testBreakfast.getCalories());
    assertEquals("Peanut Butter Spread", testBreakfast.getNotes());
    }

    //Tests getMealType in each subclass of Food
    @Test
    void testGetMealType(){
        assertEquals("Breakfast", testBreakfast.getMealType());
        assertEquals("Lunch", testLunch.getMealType());
        assertEquals("Dinner", testDinner.getMealType());
        assertEquals("Snack", testSnack.getMealType());
    }
}