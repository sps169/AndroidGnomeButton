package com.example.androidproject;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonGnomos {
    private String file;
    private Context context;

    public JsonGnomos(String file, Context context) {
        this.file = file;
        this.context = context;
    }

    public void save (List<GnomoItem> listGnomos) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(listGnomos);
        Writer output = null;
        try {
            OutputStream out = context.openFileOutput(file, context.MODE_PRIVATE);
            output = new OutputStreamWriter(out);
            output.write(jsonList);
        }catch (FileNotFoundException e) {
            System.err.println("File not found, persistence couldn't happen");
        }catch (IOException e){
            System.err.println("Could not write JSON into persistence file");
        } finally {
            if (output != null){
                try {
                    output.close();
                }catch (IOException e) {
                    System.err.println("Could not close Output Stream");
                }
            }
        }
    }

    public void saveSettings (boolean nightMode) {
        Gson gson = new Gson();
        String json = gson.toJson(nightMode);
        Writer output = null;
        try {
            OutputStream out = context.openFileOutput(file, context.MODE_PRIVATE);
            output = new OutputStreamWriter(out);
            output.write(json);
        }catch (FileNotFoundException e) {
            System.err.println("File not found, persistence couldn't happen");
        }catch (IOException e){
            System.err.println("Could not write JSON into persistence file");
        } finally {
            if (output != null){
                try {
                    output.close();
                }catch (IOException e) {
                    System.err.println("Could not close Output Stream");
                }
            }
        }
    }

    public boolean loadSettings () {
        boolean nightMode = false;
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(file);
            reader = new BufferedReader(new InputStreamReader(in));
            Gson gson = new Gson();
            Type type = new TypeToken<Boolean>(){} .getType();
            nightMode = gson.fromJson(reader,type);
        } catch (FileNotFoundException e){
            nightMode = false;
        } finally {
            if(reader != null)
                try {
                    reader.close();
                }catch (IOException e) {
                    System.err.println("Error closing reader");
                }
        }
        return nightMode;
    }
    public List<GnomoItem> load (){
        List<GnomoItem> gnomos = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(file);
            reader = new BufferedReader(new InputStreamReader(in));
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<GnomoItem>>(){} .getType();
            gnomos = gson.fromJson(reader,type);
        } catch (FileNotFoundException e){
            gnomos = new ArrayList<GnomoItem>();
        } finally {
            if(reader != null)
                try {
                    reader.close();
                }catch (IOException e) {
                    System.err.println("Error closing reader");
                }
        }
        return gnomos;
    }
}
