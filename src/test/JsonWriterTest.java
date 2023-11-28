import model.*;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    FoodDiary foodDiary;
    Person jess;

    Day day1;
    Day day2;

    Food food1;
    Food food2;
    Food food3;
    Food food4;

    JsonWriter file;

    @BeforeEach
    void setup() {
        setupFood();
        setupDay();
        setupPerson();
    }

    void setupFood () {
        food1 = new Food("Apple", 50,
                MealType.BREAKFAST, "The apple is sweet");
        food2 = new Food("Chicken", 100,
                MealType.LUNCH, "Grilled with salt");
        food3 = new Food("Cookies", 120,
                MealType.SNACK, "Chocolate chip");
        food4 = new Food("Cookies",75,
                MealType.DINNER, "Oatmeal");
    }

    private void setupDay() {
        day1 = new Day("Monday Oct 20th", 60, 1000);
        day2 = new Day("Tuesday Oct 21th", 61,1200);

        day1.addFood(food1);
        day1.addFood(food2);
        day2.addFood(food3);
        day2.addFood(food4);
    }

    private void setupPerson() {
        jess = new Person();
        jess.setName("Jess Kicks");
        jess.setSex(Sex.FEMALE);
        jess.setAge(30);
        jess.setWeight(59);
        jess.setHeight(170);
        jess.setWeightGoal(54);
        jess.setCalorieAllowance(1000);
        jess.setDietPlan(DietPlan.PLAN2);
        jess.setActivityLevel(ActivityLevel.SEDENTARY);
    }

    private void setupFoodDiaryEmpty() {
        foodDiary = new FoodDiary("Jess's Food Diary", jess);
    }

    private void setupFoodDiaryWithEntries() {
        foodDiary = new FoodDiary("Jess's Food Diary", jess);

        foodDiary.addDay(day1);
        foodDiary.addDay(day2);
    }

    @Test
    void testWriterFileNotFound() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testFoodDiaryEmpty() {
        setupFoodDiaryEmpty();

        file = new JsonWriter("./data/FoodDiaryEmptyDays.json");

        try {
            file.open();
        } catch (FileNotFoundException e) {
            fail("file exists");
        }

        file.write(foodDiary);
        file.close();

        JsonReader reader = new JsonReader("./data/FoodDiaryEmptyDays.json");

        FoodDiary testDiary = null;
        try {
            testDiary = reader.read();
        } catch (IOException e) {
            fail("File exists");
        }

        assertEquals("Jess's Food Diary",testDiary.getName());
        assertEquals(0, testDiary.getNumDays());
        testDiaryUser(testDiary);
    }

    @Test
    void testFoodDiaryWithEntries() {
        setupFoodDiaryWithEntries();

        file = new JsonWriter("./data/FoodDiaryWithEntries.json");

        try {
            file.open();
        } catch (FileNotFoundException e) {
            fail("file exists");
        }

        file.write(foodDiary);
        file.close();

        JsonReader reader = new JsonReader("./data/FoodDiaryWithEntries.json");

        FoodDiary testDiary = null;
        try {
            testDiary = reader.read();
        } catch (IOException e) {
            fail("File exists");
        }

        assertEquals("Jess's Food Diary",testDiary.getName());
        testDiaryUser(testDiary);

        testDiaryEntries(testDiary);
    }

    private void testDiaryEntries(FoodDiary testDiary) {
        assertEquals(2, testDiary.getNumDays());

        List<Day> testDays = testDiary.getDays();

        Day testDay1 = testDays.get(0);
        Day testDay2 = testDays.get(1);

        assertEquals("Monday Oct 20th", testDay1.getDate());
        assertEquals(60, testDay1.getWeight());
        assertEquals(1000, testDay1.getCaloriesAllowed());
        assertEquals(150, testDay1.getTotalCalories());
        assertEquals(2, testDay1.getNumFoods());

        assertEquals("Tuesday Oct 21th", testDay2.getDate());
        assertEquals(61, testDay2.getWeight());
        assertEquals(1200, testDay2.getCaloriesAllowed());
        assertEquals(195, testDay2.getTotalCalories());
        assertEquals(2, testDay2.getNumFoods());

        testFoodEntriesDay1(testDay1);
        testFoodEntriesDay2(testDay2);
    }

    private void testFoodEntriesDay1(Day testDay) {
        Food testFood1 = testDay.getFood(0);
        Food testFood2 = testDay.getFood(1);

        assertEquals("Apple", testFood1.getName());
        assertEquals(50, testFood1.getCalories());
        assertEquals(MealType.BREAKFAST, testFood1.getMealType());
        assertEquals("The apple is sweet", testFood1.getNotes());

        assertEquals("Chicken", testFood2.getName());
        assertEquals(100, testFood2.getCalories());
        assertEquals(MealType.LUNCH, testFood2.getMealType());
        assertEquals("Grilled with salt", testFood2.getNotes());
    }

    private void testFoodEntriesDay2(Day testDay) {

        Food testFood1 = testDay.getFood(0);
        Food testFood2 = testDay.getFood(1);

        assertEquals("Cookies", testFood1.getName());
        assertEquals(120, testFood1.getCalories());
        assertEquals(MealType.SNACK, testFood1.getMealType());
        assertEquals("Chocolate chip", testFood1.getNotes());

        assertEquals("Cookies", testFood2.getName());
        assertEquals(75, testFood2.getCalories());
        assertEquals(MealType.DINNER, testFood2.getMealType());
        assertEquals("Oatmeal", testFood2.getNotes());
    }

    private static void testDiaryUser(FoodDiary testDiary) {
        Person jess2 = testDiary.getUser();
        assertEquals("Jess Kicks", jess2.getName());
        assertEquals(Sex.FEMALE,jess2.getSex());
        assertEquals(30, jess2.getAge());
        assertEquals(59,jess2.getWeight());
        assertEquals(170, jess2.getHeight());
        assertEquals(54,jess2.getWeightGoal());
        assertEquals(1000,jess2.getCalorieAllowance() );
        assertEquals(ActivityLevel.SEDENTARY, jess2.getActivityLevel());
        assertEquals(DietPlan.PLAN2, jess2.getDietPlan());
    }

}
