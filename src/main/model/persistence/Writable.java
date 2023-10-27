package model.persistence;

import org.json.JSONObject;

//a class containing methods for converting to JSONObjects
public interface Writable {

    public JSONObject toJson();
}
