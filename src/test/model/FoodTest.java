package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    //Fields
    Food testFood1;
    Food testFood2;
    Food testFood3;
    Food testFood4;

    @BeforeEach
    //Sets each variable to corresponding subtypes
    void setup() {
        testFood1 = new Food("Toast", 100, MealType.BREAKFAST, "Peanut Butter Spread");
        testFood2 = new Food("Steak", 200,MealType.DINNER, "Ribeye");
        testFood3 = new Food(testFood1);
        testFood4 = new Food();
    }

    @Test
    void testDefaultConstructor() {
        assertTrue(testFood4.getName().isEmpty());
        assertTrue(testFood4.getNotes().isEmpty());
        assertNull(testFood4.getMealType());
        assertEquals(0, testFood4.getCalories());
    }

    //tests constructor with arguments
    @Test
    void testConstructorArgs(){
    assertEquals("Toast", testFood1.getName());
    assertEquals(100, testFood1.getCalories());
    assertEquals("Peanut Butter Spread", testFood1.getNotes());
    assertEquals(MealType.BREAKFAST, testFood1.getMealType());

    assertEquals("Steak", testFood2.getName());
    assertEquals(200, testFood2.getCalories());
    assertEquals("Ribeye", testFood2.getNotes());
    assertEquals(MealType.DINNER, testFood2.getMealType());
    }

    //tests the copy constructor
    @Test
    void testCopyConstructor() {
        assertEquals("Toast", testFood3.getName());
        assertEquals(100, testFood3.getCalories());
        assertEquals("Peanut Butter Spread", testFood3.getNotes());
        assertEquals (MealType.BREAKFAST, testFood3.getMealType());
    }

    @Test
    void testSetMealType() {
        testFood1.setMealType(MealType.SNACK);
        testFood3.setMealType(MealType.LUNCH);

        assertEquals(MealType.SNACK, testFood1.getMealType());
        assertEquals(MealType.LUNCH, testFood3.getMealType());
    }

    @Test
    void testSetCalories () {
        testFood1.setCalories(150);
        assertEquals(150, testFood1.getCalories());

        testFood2.setCalories(75);
        assertEquals(75,testFood2.getCalories());
    }

    @Test
    void testSetName() {
        testFood1.setName("Chicken");
        assertEquals("Chicken", testFood1.getName());

        testFood2.setName("French fries");
        assertEquals("French fries", testFood2.getName());
    }

    @Test
    void testsetNotes() {
        testFood1.setNotes("Jam");
        assertEquals("Jam", testFood1.getNotes());

        testFood2.setNotes("Sirloin");
        assertEquals("Sirloin", testFood2.getNotes());
    }


}
