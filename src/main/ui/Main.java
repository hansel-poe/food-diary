package ui;

import model.ActivityLevel;
import model.DietPlan;
import model.Person;
import model.Sex;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*JFrame myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setTitle("profile");

        Person guy = new Person();
        guy.setName("John");
        guy.setAge(25);
        guy.setWeight(75);
        guy.setHeight(183);
        guy.setSex(Sex.MALE);
        guy.setActivityLevel(ActivityLevel.SEDENTARY);

        guy.setWeightGoal(69);
        guy.setDietPlan(DietPlan.PLAN1);

        myFrame.add(new ProfileUI(guy));
        myFrame.setVisible(true);*/
       /* JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);

        myFrame.add(new NewDiaryUI());
        myFrame.setVisible(true);*/

        new FoodDiaryUI();
    }
}
