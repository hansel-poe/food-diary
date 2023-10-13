package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    Person jonny;
    Person jess;

    @BeforeEach
    void setup(){
        jonny = new Person();
        jonny.setName("Jonny Kicks");
        jonny.setSex(TRUE);
        jonny.setWeight(75);
        jonny.setWeightGoal(69);

        jess = new Person();
        jess.setName("Jess Kicks");
        jess.setSex(FALSE);
        jess.setWeight(59);
        jess.setWeightGoal(54);
    }

    @Test
    void testGetSex(){
        assertEquals("Male", jonny.getSex());
        assertEquals("Female", jess.getSex());
    }

    @Test
    void testGetActivityLevelFactor(){
        jonny.setActivityLevel(1);
        assertEquals(1.2f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(2);
        assertEquals(1.375f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(3);
        assertEquals(1.55f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(4);
        assertEquals(1.725f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(5);
        assertEquals(1.9f, jonny.getActivityLevelFactor());

        jonny.setActivityLevel(0);
        assertEquals(0, jonny.getActivityLevelFactor()); //unexpected value
    }

    @Test
    void testGetCalorieCutPerDay(){
        jess.setDietPlan(1);
       assertEquals(250, jess.getCaloriesCutPerDay());
       jess.setDietPlan(2);
       assertEquals(500, jess.getCaloriesCutPerDay());
       jess.setDietPlan(3);
       assertEquals(1000, jess.getCaloriesCutPerDay());
       jess.setDietPlan(0);
       assertEquals(0, jess.getCaloriesCutPerDay()); // invalid dietPlan selected
   }
    @Test
    void testGetGoalLength(){
        jonny.setDietPlan(1);
        assertEquals( 168, jonny.getGoalLength());

        jess.setDietPlan(3);
        assertEquals(35, jess.getGoalLength());

        jess.setDietPlan(2);
        assertEquals(70, jess.getGoalLength());

        jess.setWeightGoal(70);
        assertEquals(0, jess.getGoalLength()); // weightGoal > weight

        jess.setDietPlan(0);
        assertEquals(0, jess.getGoalLength()); //invalid dietPlan selected
    }

}
