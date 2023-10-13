package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {
    Day day1;;

    @BeforeEach
    void setup(){
        day1 = new Day("Monday Oct 20th", 60, 1000);
    }

    @Test
    void testConstructor(){
        assertEquals("Monday Oct 20th", day1.getDate());
        assertEquals(60, day1.getWeight());
        assertEquals(1000, day1.getCaloriesAllowed());
    }

    @Test
    void testAddFood(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Chicken", 100, "Lunch", "Grilled with salt");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood("Burger",230, "Dinner", "Beef with eggs");

        List<Food> foods = day1.getFoods();
        assertEquals(4, foods.size());

        assertEquals(50,foods.get(0).getCalories());
        assertEquals(100,foods.get(1).getCalories());
        assertEquals(120, foods.get(2).getCalories());
        assertEquals(230, foods.get(3).getCalories());

        assertEquals("Apple",foods.get(0).getName());
        assertEquals("Chicken",foods.get(1).getName());
        assertEquals("Cookies",foods.get(2).getName());
        assertEquals("Burger",foods.get(3).getName());

        assertEquals("Breakfast",foods.get(0).getMealType());
        assertEquals("Lunch",foods.get(1).getMealType());
        assertEquals("Snack",foods.get(2).getMealType());
        assertEquals("Dinner",foods.get(3).getMealType());

        assertEquals("The apple is sweet",foods.get(0).getNotes());
        assertEquals("Grilled with salt",foods.get(1).getNotes());
        assertEquals("Chocolate chip",foods.get(2).getNotes());
        assertEquals("Beef with eggs",foods.get(3).getNotes());
    }

    @Test
    void testRemoveFood(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood("Cookies", 70, "Snack", "Oatmeal");

        day1.removeFood("Cookies"); // removed in the order they are added, so Chocolate chip is removed

        List<Food> foods = day1.getFoods();

        assertEquals(2, foods.size());
        assertEquals("Cookies", foods.get(1).getName());
        assertEquals("Oatmeal", foods.get(1).getNotes());
    }

    @Test
    void testRemoveFoodMany(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood("Cookies", 70, "Snack", "Oatmeal");

        day1.removeFood("Cookies"); // removed in the order they are added, so Chocolate chip is removed
        day1.removeFood("Cookies");

        List<Food> foods = day1.getFoods();

        assertEquals(1, foods.size());

        day1.removeFood("Apple");
        day1.removeFood("Apple");//test when set is empty

        assertEquals(0, foods.size());
    }

    @Test
    void testUpdateTotalCalories(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood("Chicken", 100, "Dinner", "Grilled with salt");

        assertEquals(270,day1.getTotalCalories() );

        day1.removeFood("Apple");

        assertEquals(220, day1.getTotalCalories());
    }

    @Test
    void testGetCaloriesLeftNoExceed(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood("Chicken", 100, "Dinner", "Grilled with salt");

        assertEquals(730,day1.getCaloriesLeft());

        day1.removeFood("Apple");

        assertEquals(780, day1.getCaloriesLeft());
    }

    @Test
    void testGetCaloriesLeftExceed(){
        day1.addFood("Apple", 50, "Breakfast", "The apple is sweet");
        day1.addFood("Cookies", 120, "Snack", "Chocolate chip");
        day1.addFood(" Fried Chicken", 1000, "Dinner", "Oily!");

        assertEquals(0, day1.getCaloriesLeft());
    }

}

