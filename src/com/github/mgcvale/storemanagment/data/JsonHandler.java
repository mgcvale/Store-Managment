package com.github.mgcvale.storemanagment.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonHandler {
    private final Gson gson;

    public JsonHandler() {
        this.gson = new Gson();
    }

    public HashMap<Integer, String[]> getHashMapFromJson(File jsonDir) throws IOException {
        try(Reader reader = new FileReader(jsonDir)){
            Type type = new TypeToken<HashMap<Integer, String[]>>() {}.getType();

            return gson.fromJson(reader, type);
        }
    }
    public void exportHashMap(File jsonDir, HashMap<Integer, String[]> map) throws IOException{
        try(Writer writer = new FileWriter(jsonDir)){
            gson.toJson(map, writer);
        }
    }

}
