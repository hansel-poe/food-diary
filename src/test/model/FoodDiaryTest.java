package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDiaryTest {
    FoodDiary foodDiary;
    Person jonny;
    Day day1;
    Day day2;
    Day day3;

    @BeforeEach
    void setup() {
        jonny = new Person();
        jonny.setName("Jonny Kicks");
        jonny.setSex(Sex.MALE);
        jonny.setAge(25);
        jonny.setWeight(75);
        jonny.setHeight(183);
        jonny.setWeightGoal(69);

        foodDiary = new FoodDiary("Jonny's diary", jonny);

        day1 = new Day();
        day2 = new Day();
        day3 = new Day();
    }

    @Test
    void testConstructor() {
        assertEquals("Jonny's diary", foodDiary.getName());
        assertEquals(jonny, foodDiary.getUser());
        assertTrue(foodDiary.isEmpty());
    }

    @Test
    void testAddDay() {
        foodDiary.addDay(day1);
        foodDiary.addDay(day2);
        foodDiary.addDay(day3);

        assertEquals(3, foodDiary.getNumDays());
    }
}