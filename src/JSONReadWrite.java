import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReadWrite{
    public static void read(ArrayList<String> arr) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("input.json"));
            JSONArray mathematicalEquations = (JSONArray) jsonObject.get("MathematicalEquations");
            for (Object o : mathematicalEquations)
            {
                JSONObject obj = (JSONObject) o;
                String temp = (String)obj.get("equation");
                arr.add(temp);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e)
        {}
    }

    public static void write(ArrayList<String> arr, ArrayList<Integer> result) {
        String jsonFilePath = "output.json";
        String inputText = new String();
        try {
            Iterator<String> iter1 = arr.iterator();
            Iterator<Integer> iter2 = result.iterator();

            while(iter1.hasNext()){
                String arr1 = iter1.next();
                Integer int1 = iter2.next();
                inputText += arr1 + ": " + int1 + ';';
            }

            String[] equations = inputText.split(";");
            List<JSONObject> equationsList = new ArrayList<>();

            for (String equation : equations) {
                String[] parts = equation.split(":");
                JSONObject equationObject = new JSONObject();
                equationObject.put("expression", parts[0].trim());
                equationObject.put("result", Integer.parseInt(parts[1].trim()));
                equationsList.add(equationObject);
            }

            JSONObject jsonOutput = new JSONObject();
            jsonOutput.put("equations", equationsList);

            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.write(jsonOutput.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
