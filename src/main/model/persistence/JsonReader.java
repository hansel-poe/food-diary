package model.persistence;

import model.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents a reader to read Json Objects from file and interprets it
public class JsonReader {
    private String targetFile;

    //Effects: creates a new JsonReader with target file specified
    public JsonReader(String fileName) {
        this.targetFile = fileName;
    }

    // EFFECTS: reads Food diary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodDiary read() throws IOException {
        String jsonData = readFile(targetFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodDiary(jsonObject);
    }

    // EFFECTS: reads targetFile file as string and returns it
    private String readFile(String targetFile) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(targetFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> fileContent.append(s));
        }

        return fileContent.toString();
    }

    private FoodDiary parseFoodDiary(JSONObject json) {
        String name = json.getString("name");
        Person user = parseUser(json);
        return new FoodDiary(name,user);
    }


    private Person parseUser(JSONObject json) {
        JSONObject user = json.getJSONObject("user");
        Person person = new Person();
        person.setName(user.getString("name"));
        person.setSex(Sex.valueOf(user.getString("sex")));
        person.setAge(user.getInt("age"));
        person.setWeight(user.getInt("weight"));
        person.setHeight(user.getInt("height"));
        person.setCalorieAllowance(user.getInt("calorie allowance"));
        person.setWeightGoal(user.getInt("weight goal"));
        person.setActivityLevel(ActivityLevel.valueOf(user.getString("activity level")));
        person.setDietPlan(DietPlan.valueOf(user.getString("diet plan")));
        return person;
    }

   /* //Modifies: day
    //Effects: retrieves each food item from json and add it to day)
    private void addFood(Day day, JSONObject json) {
        String name = json.getString("name");
        int calories = json.getInt("calories");
        MealType mealType = MealType.valueOf(json.getString("meal type"));
        String notes = json.getString("notes");

        Food food = new Food(name, calories, mealType, notes);
        day.addFood(food);
    }*/

}
