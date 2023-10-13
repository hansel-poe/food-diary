package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

//You can visit this link to check results of calculator:
// https://www.calculator.net/calorie-calculator.html?cage=30&csex=f&cheightfeet=5&cheightinch=10&cpound=165&cheightmeter=170&ckg=59&cactivity=1.55&cmop=1&coutunit=c&cformula=m&cfatpct=20&printit=0&ctype=metric&x=Calculate
public class CalorieCalculatorTest {
    Person jonny;
    Person jess;
    CalorieCalculator cc;

    @BeforeEach
    void setup() {
        jonny = new Person();
        jonny.setName("Jonny Kicks");
        jonny.setSex(TRUE);
        jonny.setAge(25);
        jonny.setWeight(75);
        jonny.setHeight(180);
        jonny.setWeightGoal(69);

        jess = new Person();
        jess.setName("Jess Kicks");
        jess.setSex(FALSE);
        jess.setAge(30);
        jess.setWeight(59);
        jess.setHeight(170);
        jess.setWeightGoal(54);

        cc = new CalorieCalculator();
    }

    @Test
    void testCalculateBmrMale() {
        assertEquals(1755, cc.calculateBmrMale(jonny));
    }

    @Test
    void testCalculateBmrFemale() {
        assertEquals(1342, cc.calculateBmrFemale(jess));
    }

    @Test
    void testCalculatetdEE() {
        jess.setActivityLevel(1); //sedentary
        assertEquals(1610, cc.calculatetdEE(jess, 1342));

        jess.setActivityLevel(2); //Light
        assertEquals(1845, cc.calculatetdEE(jess, 1342));


        jess.setActivityLevel(3); //active
        assertEquals(2080, cc.calculatetdEE(jess, 1342));

        jess.setActivityLevel(4); //very active
        assertEquals(2315, cc.calculatetdEE(jess, 1342));

        jess.setActivityLevel(5); // extra active
        assertEquals(2550, cc.calculatetdEE(jess, 1342));
    }

    @Test
    void testCalculateCalorieAllowance(){
        jess.setActivityLevel(1);
        jess.setDietPlan(1);

        assertEquals(1360, cc.calculateCalorieAllowance(jess));

        jess.setDietPlan(2);
        assertEquals(1110, cc.calculateCalorieAllowance(jess));

        jess.setDietPlan(3);
        assertEquals(610, cc.calculateCalorieAllowance(jess));
    }


}





