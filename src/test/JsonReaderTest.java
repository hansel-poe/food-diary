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

public class JsonReaderTest {

    @Test
    void testReaderFileNotFound() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        FoodDiary testDiary = null;
        try {
            testDiary = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testFoodDiaryEmpty() {
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




