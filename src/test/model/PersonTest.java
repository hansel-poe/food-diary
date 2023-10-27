package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person jonny;
    Person jess;

    @BeforeEach
    void setup(){
        jonny = new Person();
        jonny.setName("Jonny Kicks");
        jonny.setSex(Sex.MALE);
        jonny.setWeight(75);
        jonny.setWeightGoal(69);

        jess = new Person();
        jess.setName("Jess Kicks");
        jess.setSex(Sex.FEMALE);
        jess.setWeight(59);
        jess.setWeightGoal(54);
    }

    @Test
    void testConstructor() {
        jonny = new Person();

        assertTrue(jonny.getName().isEmpty());
        assertNull(jonny.getSex());
        assertEquals(0,jonny.getAge());
        assertEquals(0, jonny.getWeight());
        assertEquals(0, jonny.getHeight());

        assertEquals(0, jonny.getCalorieAllowance());
        assertEquals(0, jonny.getWeightGoal());
        assertNull(jonny.getDietPlan());
        assertNull(jonny.getActivityLevel());
    }
    @Test
    void testGetSex() {
        assertEquals(Sex.MALE, jonny.getSex());
        assertEquals(Sex.FEMALE, jess.getSex());
    }

    @Test
    void testGetActivityLevelFactor(){
        jonny.setActivityLevel(ActivityLevel.SEDENTARY);
        assertEquals(1.2f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(ActivityLevel.LIGHT);
        assertEquals(1.375f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(ActivityLevel.ACTIVE);
        assertEquals(1.55f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(ActivityLevel.VERY_ACTIVE);
        assertEquals(1.725f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(ActivityLevel.EXTRA_ACTIVE);
        assertEquals(1.9f, jonny.getActivityLevelFactor());
    }

    @Test
    void testGetCalorieCutPerDay() {
        jess.setDietPlan(DietPlan.PLAN1);
        assertEquals(250, jess.getCaloriesCutPerDay());
        jess.setDietPlan(DietPlan.PLAN2);
        assertEquals(500, jess.getCaloriesCutPerDay());
        jess.setDietPlan(DietPlan.PLAN3);
        assertEquals(1000, jess.getCaloriesCutPerDay());
   }

    @Test
    void testGetGoalLength(){
        jonny.setDietPlan(DietPlan.PLAN1);
        assertEquals( 168, jonny.getGoalLength());

        jess.setDietPlan(DietPlan.PLAN3);
        assertEquals(35, jess.getGoalLength());

        jess.setDietPlan(DietPlan.PLAN2);
        assertEquals(70, jess.getGoalLength());

        jess.setWeightGoal(70);
        assertEquals(0, jess.getGoalLength()); // weightGoal > weight
    }
}
