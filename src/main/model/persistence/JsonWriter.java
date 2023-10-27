package model.persistence;

import model.FoodDiary;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//A writer that writes JSON data to a file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter output;
    private String fileName;

    //Effects: constructs a writer with the name of the file to be written on
    public JsonWriter(String file) {
        this.fileName = file;
    }

    //Modifies : this
    //Effects : opens file to be written on, throws FileNotFoundException if file cannot be found
    public void open() throws FileNotFoundException {
        output = new PrintWriter(new File(fileName));
    }

    //Modifies: this
    //Effects: writes json representation of
    public void write(FoodDiary foodDiary) {
        JSONObject json = foodDiary.toJson();
        output.print(json.toString(TAB));
    }

    //Modifies: this
    //Effects: closes the file
    public void close() {
        output.close();
    }
}
