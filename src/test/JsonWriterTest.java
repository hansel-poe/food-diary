import model.*;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    FoodDiary foodDiary1;
    Person jess;
    JsonWriter file;
    Day day1;
    Day day2;


    @BeforeEach
    void setup() {
        jess = new Person();
        jess.setName("Jess Kicks");
        jess.setSex(Sex.FEMALE);
        jess.setAge(30);
        jess.setWeight(59);
        jess.setHeight(170);
        jess.setWeightGoal(54);
        jess.setCalorieAllowance(1000);
        jess.setActivityLevel(ActivityLevel.SEDENTARY);
        jess.setDietPlan(DietPlan.PLAN1);

        foodDiary1 = new FoodDiary("Jess's Food Diary", jess);
    }

    @Test
    void testFoodDiaryEmpty() {
        file = new JsonWriter("./data/FoodDiaryEmptyDays.json");

        try {
            file.open();
        } catch (FileNotFoundException e) {
            fail("file exists");
        }

        file.write(foodDiary1);
        file.close();

        JsonReader reader = new JsonReader("./data/FoodDiaryEmptyDays.json");

        FoodDiary testDiary = null;
        try {
            testDiary = reader.read();
        } catch (IOException e) {
            fail("File exists");
        }

        Person jess2 = testDiary.getUser();
        assertEquals("Jess's Food Diary",testDiary.getName());
        assertEquals("Jess Kicks", jess2.getName());
        assertEquals(Sex.FEMALE,jess2.getSex());
        assertEquals(30, jess2.getAge());
        assertEquals(59,jess2.getWeight());
        assertEquals(170, jess2.getHeight());
        assertEquals(54,jess2.getWeightGoal());
        assertEquals(1000,jess2.getCalorieAllowance() );
        assertEquals(ActivityLevel.SEDENTARY, jess2.getActivityLevel());
        assertEquals(DietPlan.PLAN1, jess2.getDietPlan());
    }
}
