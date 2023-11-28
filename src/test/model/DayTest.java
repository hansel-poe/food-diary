package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    Day day1;;
    Day day2;

    Food food1;
    Food food2;
    Food food3;
    Food food4;

    @BeforeEach
    void setup(){
        day1 = new Day("Monday Oct 20th", 60, 1000);
        day2 = new Day();

        food1 = new Food("Apple", 50,
                MealType.BREAKFAST, "The apple is sweet");
        food2 = new Food("Chicken", 100,
                MealType.LUNCH, "Grilled with salt");
        food3 = new Food("Cookies", 120,
                MealType.SNACK, "Chocolate chip");
        food4 = new Food("Cookies",75,
                MealType.DINNER, "Oatmeal");
    }

    @Test
    void testDefaultConstructor() {
        assertTrue(day2.getDate().isEmpty());
        assertEquals(0, day2.getWeight());
        assertEquals(0, day2.getCaloriesAllowed());
        assertEquals(0, day2.getTotalCalories());
        assertTrue(day1.isEmpty());
    }

    @Test
    void testConstructorArgs() {
        assertEquals("Monday Oct 20th", day1.getDate());
        assertEquals(60, day1.getWeight());
        assertEquals(1000, day1.getCaloriesAllowed());
        assertEquals(0, day1.getTotalCalories());
        assertTrue(day1.isEmpty());
    }

    @Test
    void testAddFood(){
        day1.addFood(food1);
        day1.addFood(food2);
        day1.addFood(food3);
        day1.addFood(food4);

        assertEquals(4, day1.getNumFoods());

        assertTrue(day1.contains(food1));
        assertTrue(day1.contains(food2));
        assertTrue(day1.contains(food3));
        assertTrue(day1.contains(food4));

        assertEquals(50, day1.getFood(0).getCalories());
        assertEquals(100, day1.getFood(1).getCalories());
        assertEquals(120, day1.getFood(2).getCalories());
        assertEquals(75, day1.getFood(3).getCalories());

        assertEquals("Apple",day1.getFood(0).getName());
        assertEquals("Chicken",day1.getFood(1).getName());
        assertEquals("Cookies",day1.getFood(2).getName());
        assertEquals("Cookies",day1.getFood(3).getName());

        assertEquals(MealType.BREAKFAST,day1.getFood(0).getMealType());
        assertEquals(MealType.LUNCH,day1.getFood(1).getMealType());
        assertEquals(MealType.SNACK,day1.getFood(2).getMealType());
        assertEquals(MealType.DINNER,day1.getFood(3).getMealType());

        assertEquals("The apple is sweet",day1.getFood(0).getNotes());
        assertEquals("Grilled with salt",day1.getFood(1).getNotes());
        assertEquals("Chocolate chip",day1.getFood(2).getNotes());
        assertEquals("Oatmeal",day1.getFood(3).getNotes());
    }

    @Test
    void testRemoveAll() {
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        assertEquals(3, day1.getNumFoods());

        List<Food> foodsToRemove = new ArrayList<>();
        foodsToRemove.add(food1);
        foodsToRemove.add(food3);

        day1.removeAll(foodsToRemove);

        assertEquals(1, day1.getNumFoods());
        assertTrue(day1.contains(food4));

        foodsToRemove.add(food2); // not in day1 no change expected
        day1.removeAll(foodsToRemove);

        assertEquals(1, day1.getNumFoods());
        assertTrue(day1.contains(food4));
    }

   /* @Test
    void testRemoveFood(){
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        day1.removeFood("Cookies",MealType.DINNER); // Oatmeal Cookies is removed,

        assertEquals(2,day1.getNumFoods());

        List<Food> foods = day1.getFoods();
        assertEquals("Cookies", foods.get(1).getName());
        assertEquals("Chocolate chip", foods.get(1).getNotes());
    }*/

    /*@Test
    void testRemoveFoodMany(){
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        day1.removeFood("Cookies",MealType.DINNER);
        day1.removeFood("Cookies",MealType.SNACK);

        assertEquals(1, day1.getNumFoods());

        day1.removeFood("Apple", MealType.BREAKFAST);

        assertEquals(0, day1.getNumFoods());
    }*/

    @Test
    void testRemoveFood() {
        day1.addFood(food1);
        day1.addFood(food3);
        day1.addFood(food4);

        assertEquals(3, day1.getNumFoods());

        day1.removeFood(food1);
        assertEquals(2, day1.getNumFoods());
        assertFalse(day1.contains(food1));
        assertTrue(day1.contains(food3));
        assertTrue(day1.contains(food4));

        day1.removeFood(food2);// not in day1, no change is expected
        assertEquals(2, day1.getNumFoods());
        assertTrue(day1.contains(food3));
        assertTrue(day1.contains(food4));

        day1.removeFood(food3);
        assertEquals(1, day1.getNumFoods());
        assertFalse(day1.contains(food3));
        assertTrue(day1.contains(food4));

        day1.removeFood(food4);
        assertTrue(day1.isEmpty());
    }

    @Test
    void testGetTotalCalories(){
        day1.addFood(food1);
        day1.addFood(food2);
        day1.addFood(food3);

        assertEquals(270, day1.getTotalCalories());

        day1.removeFood(food1);
        assertEquals(220, day1.getTotalCalories());
    }

    @Test
    void testGetCaloriesLeftNoExceed(){
        day1.addFood(food1);
        day1.addFood(food2);
        day1.addFood(food3);

        assertEquals(730,day1.getCaloriesLeft());

        day1.removeFood(food1);

        assertEquals(780, day1.getCaloriesLeft());
    }

    @Test
    void testGetCaloriesLeftExceed(){
        day1.addFood(food1);
        day1.addFood(new Food("Fried Chicken", 1000, MealType.DINNER, "Oily!"));

        assertEquals(0, day1.getCaloriesLeft());
    }

}

