# My Personal Project

### Food Diary
This project will consist of a program that will help the user reach their weight goal <br>
by tracking the calories of  foods the user eats in a day. A daily calorie allowance will <br>
be calculated  based on information provided by the user and the program will help<br>
the user to keep watch of their calorie intake in a day. This program is intended for someone <br> 
who wishes to lose weight and monitor their eating. The idea for this program comes naturally<br> 
to me as I, myself, am currently trying to lose weight and be more mindful of what I eat. I figured <br>
this program will be helpful to someone who is on a weight loss journey like I am.

User Stories :  
- As a user, I want to be able to enter inputs  of my relevant personal health information such as age,  weight, height, sex and activity level.
- As a user, I want to specify a weight goal.
- As a user, I want to choose a diet plan which will determine how fast I will reach that weight goal. <br> <br>
(The three user stories above are the user specifying information that will make up the user profile) <br> <br>
- As a user, I want to know how much calorie I should consume in a day given my health information, specified weight goal and diet plan.
- As a user, I want to be able to add a day entry and specify a date, with each day recording the current weight and calorie allowance.
- As a user, I want to be able to add a food entry to a day entry, specifying its calories, what meal type it belongs to (Breakfast, Lunch, Dinner, or snack) as well as add some notes.
- As a user, I want to be able to view the list of entries (days) in my food diary.
- As a user, I want to be able to view the list of food items for a particular day.
- As a user, I want to be able to know the total calories of all food items in a particular day, and the calorie allowance left (How many calories I can still consume without exceeding that day's allowance).
- As a user, I want to be able to update my health information (such as weight) as time progresses and for the program to calculate a new daily calorie allowance based on the new information given.
- As a user, I want to be able to save my personal health information, weight goal, and diet plan.
- As a user, I want to be able to save my Day entries containing all the different food items for that day.
- As a user, I want to be able to load my Profile.
- As a user, I want to be able to load my Day entries.

### Instructions for Grader
1. When the app starts, a blank screen is shown. Click file on the  menu bar and select new to open a new diary. Two tabs appear, log and profile.
2. Click on profile tab to open the profile page. Notice that the name field is empty, all number fields are set to zero,<br>
and combo boxes are set to their first option. Edit these fields accordingly, and click on set, a new calorie allowance will be calculated from your
entered values. The number of days to reach your goal is also given.
3. Now click on log on the tab to open the log page, this is where you can manage your entries.
4. First thing you want to do is edit the diary name (the name displayed on the top of the page, currently <br> it just says New Diary because we just created a new diary).
5. Click on Add Entry to add a new entry, a dialog pops up asking for the name of your entry.<sup>1</sup>
   - you can try inputting an empty string to see what happens.
6. After you have created a new entry, notice that the calories allowed and weight shown matches that on the profile page.As you go along and edit your user profile (which is to be expected as you will lose weight throughout your journey), your day entry will record the most current profile info.
7. Select the entry by clicking on it and click view entry. A new tab pops up showing the entry with all the food items.
8. There is currently no food in the list, you can add some.<sup>1</sup>. Notice that the total calories of food updates (in the log page too!) and the calories allowed left also updates.
9. You can also select food items (use shift or ctrl to select multiple) and remove them<sup>2</sup> . the total calories and calorie allowance left will update.
10. Finally, you can sort items by clicking on the column headers<sup>2</sup> (order for meal type is Breakfast, Lunch, Dinner and then snack and calories will be sorted from highest/lowest).
10. Click on the X at the tab to close the tab for the entry.
11. Experiment! try adding and removing<sup>2</sup>  entries and adding food items to entries (You can open multiple entries in the app, but each entry can only have one tab opened).
12. I tried to design an attractive UI. notice there is a theme of light pink in the buttons, combo boxes, tabs (I think they're super cute). There are icons at the top of the log and profile tabs<sup>3</sup>.  
13. Finally, when you're done logging in your food Diary, click on file > save on the menu bar to save your program. <sup>4</sup> 
14. Try rebooting the program and loading your save file (File > load). All your precious entries will be restored :D.<sup>5</sup>

notes: 
1. required action for adding X's to Y's
2. second of the required action 
3. visual component 
4. saving 
5. loading 

### Phase 4: Task 2
Log: <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile name is set to Hansel <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile age is set to 20 <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile weight is set to 65 <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile height is set to 178 <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile sex is set to MALE <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile activity level is set to SEDENTARY <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile weight goal is set to 60 <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile diet plan is set to PLAN2, Lose 0.5 kg per week <br>
Thu Nov 30 10:46:17 PST 2023 <br>
Profile calorie allowance is set to 1502 <br>
Thu Nov 30 10:46:27 PST 2023 <br>
Food Diary name is changed to Hansel's Diary <br>
Thu Nov 30 10:46:37 PST 2023 <br>
[Nov 30 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0] is added to Hansel's Diary <br>
Thu Nov 30 10:46:58 PST 2023 <br>
[Apple , 50 cal, Gala apples] is added to Nov 30 2023 <br>
Thu Nov 30 10:47:12 PST 2023 <br>
[Steaks, 500 cal, Ribeye] is added to Nov 30 2023 <br>
Thu Nov 30 10:47:25 PST 2023 <br>
[Sandwich, 230 cal, Subway] is added to Nov 30 2023 <br>
Thu Nov 30 10:47:46 PST 2023 <br>
[Rat Poison, 50 cal, Deadly] is added to Nov 30 2023 <br>
Thu Nov 30 10:48:19 PST 2023 <br>
[Cyanide, 20 cal, Accidentally ingested] is added to Nov 30 2023 <br>
Thu Nov 30 10:48:27 PST 2023 <br>
[[Rat Poison, 50 cal, Deadly], [Cyanide, 20 cal, Accidentally ingested]] is removed from Nov 30 2023 <br>
Thu Nov 30 10:48:58 PST 2023 <br>
[Dec 1 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0] is added to Hansel's Diary <br>
Thu Nov 30 10:49:11 PST 2023 <br>
[Nov 31 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0] is added to Hansel's Diary <br>
Thu Nov 30 10:49:18 PST 2023 <br>
[Nov 32 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0] is added to Hansel's Diary <br>
Thu Nov 30 10:49:24 PST 2023 <br>
[[Nov 31 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0], [Nov 32 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0]] is removed from Hansel's Diary <br>
Thu Nov 30 10:49:30 PST 2023 <br>
Hansel's Diary is saved to ./data/FoodDiary.json <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Hansel's Diary loaded from ./data/FoodDiary.json <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile name is set to Hansel <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile sex is set to MALE <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile age is set to 20 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile weight is set to 65 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile height is set to 178 <br>
Thu Nov 30 10:49:38 PST 2023<br>
Profile calorie allowance is set to 1502 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile weight goal is set to 60 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile activity level is set to SEDENTARY <br>
Thu Nov 30 10:49:38 PST 2023 <br>
Profile diet plan is set to PLAN2, Lose 0.5 kg per week <br>
Thu Nov 30 10:49:38 PST 2023 <br>
[Apple , 50 cal, Gala apples] is added to Nov 30 2023 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
[Steaks, 500 cal, Ribeye] is added to Nov 30 2023 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
[Sandwich, 230 cal, Subway] is added to Nov 30 2023 <br>
Thu Nov 30 10:49:38 PST 2023 <br>
[Nov 30 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 780] is added to Hansel's Diary <br>
Thu Nov 30 10:49:38 PST 2023 <br>
[Dec 1 2023, Weight: 65, Cal Allowed: 1502, Total Calories: 0] is added to Hansel's Diary <br>

Logging is done when these actions are performed:
- When the diary name is changed
- When a food item is added to a day entry
- When a food item is removed from a day entry
- When a day entry is added to the diary
- When a day entry is removed from the diary
- When the profile is changed
- When the diary is saved 
- When the diary is loaded

### Phase 4: Task 3
- There's too much coupling in CalorieCalculator. It would be better if we eliminate  <br> 
the association profileUI and FoodDiary have with CalorieCalculator and instead have Person <br>
contain a field of CalorieCalculator and an update method that uses CalorieCalculator to calculate <br>
- new calorie allowance and update the field.
- FoodTableModel and EntriesTableModel needs to have a field of type Day and FoodDiary respectively, <br>
as they need to parse and modify the contents of Foods in Day and Days in FoodDiary. Similarly, EntryUI <br>
needs to contain a few information about day and LogUI needs to contain a few information about FoodDiary,<br>
therefore these associations cannot be removed.