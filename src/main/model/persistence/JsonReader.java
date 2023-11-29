package model.persistence;

import model.*;
import org.json.JSONArray;
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
        JSONObject jsonDiary = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event(jsonDiary.getString("name") + " loaded from " + targetFile));
        return parseFoodDiary(jsonDiary);
    }

    // EFFECTS: reads targetFile file as string and returns it
    private String readFile(String targetFile) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(targetFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> fileContent.append(s));
        }

        return fileContent.toString();
    }

    private FoodDiary parseFoodDiary(JSONObject jsonDiary) {
        String name = jsonDiary.getString("name");
        Person user = parseUser(jsonDiary);
        FoodDiary foodDiary = new FoodDiary(name, user);
        addDays(foodDiary, jsonDiary);

        return foodDiary;
    }

    //Modifies: foodDiary
    //Effects: extracts days from jsonDiary and adds them to foodDiary
    private void addDays(FoodDiary foodDiary, JSONObject jsonDiary) {
        JSONArray jsonDays = jsonDiary.getJSONArray("days");
        for (Object jsonDay : jsonDays) {
            JSONObject nextDay = (JSONObject) jsonDay;
            addDay(foodDiary, nextDay);
        }
    }

    //Modifies: foodDiary
    //Effects: Extracts Day from jsonDay to foodDiary
    private void addDay(FoodDiary foodDiary, JSONObject jsonDay) {
        String date = jsonDay.getString("date");
        int caloriesAllowed = jsonDay.getInt("calories allowed");
        //int totalCalories = jsonDay.getInt("total calories"); // this is actually redundant
        int weight = jsonDay.getInt("weight");

        Day day = new Day(date, weight, caloriesAllowed);
        addFoods(day, jsonDay);

        foodDiary.addDay(day);
    }

    //Modifies: day
    //effects: Extracts foods from jsonDay and add it to day
    private void addFoods(Day day, JSONObject jsonDay) {
        JSONArray jsonFoods = jsonDay.getJSONArray("foods");
        for (Object jsonFood : jsonFoods) {
            JSONObject nextFood = (JSONObject) jsonFood;
            addFood(day, nextFood);
        }
    }

    //Effects: Extracts the user information from json and returns it
    private Person parseUser(JSONObject jsonDiary) {
        JSONObject user = jsonDiary.getJSONObject("user");
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

    //Modifies: day
    //Effects: retrieves each food item from json and add it to day)
    private void addFood(Day day, JSONObject jsonFood) {
        String name = jsonFood.getString("name");
        int calories = jsonFood.getInt("calories");
        MealType mealType = MealType.valueOf(jsonFood.getString("meal type"));
        String notes = jsonFood.getString("notes");

        Food food = new Food(name, calories, mealType, notes);
        day.addFood(food);
    }

}
