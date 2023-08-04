package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Model {
    private static String filePath = "C:/Users/Сергей/Desktop/Java-HomeWork-Seminar5/src/model/phoneBook.txt";

    public static void openFile(HashMap<String, String[]> phones) {
        List<String> list = new ArrayList<>();
        String str = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((str = br.readLine()) != null) {
                list = Arrays.asList(str.replaceAll(" ", "").split("-"));
                phones.put(list.get(0), list.get(1).split(","));
            }
            br.close();
        } catch (IOException ex) {
        }
    }

    public static void addContact(String phoneName, String phoneNumb, HashMap<String, String[]> phones) {
        if (phones.containsKey(phoneName)) {

            String[] oldValue = phones.get(phoneName);
            String[] newValue = new String[oldValue.length + 1];

            System.arraycopy(oldValue, 0, newValue, 0, oldValue.length);
            newValue[newValue.length - 1] = phoneNumb;

            phones.put(phoneName, newValue);

        } else {

            String[] newValue = new String[1];
            newValue[0] = phoneNumb;

            phones.put(phoneName, newValue);
        }

    }

    public static void saveFile(HashMap<String, String[]> phones) {
        TreeMap<String, Integer> tree = new TreeMap<>();
        LinkedHashMap<String, Integer> lMapCheck = new LinkedHashMap<>();

        for (var el : phones.entrySet()) {
            tree.put(el.getKey(), el.getValue().length);
        }

        getSortedMap(lMapCheck, tree);
        System.out.println(lMapCheck);

        // tree.entrySet().stream().sorted(
        //         Map.Entry.<String, Integer>comparingByValue().reversed()).forEach(
        //                 entry -> lMapCheck.put(entry.getKey(), entry.getValue()));

        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (var el : lMapCheck.entrySet()) {

                fw.write(el.getKey() + " - " + Arrays.toString(
                        phones.get(el.getKey())).replace(
                                "[", "")
                        .replace(
                                "]", "")
                        + "\n");
            }
            
            fw.close();

        } catch (IOException ex) {
        }
    }

    public static void getSortedMap(LinkedHashMap <String, Integer> check, TreeMap<String, Integer> tree){
        tree.entrySet().stream().sorted(
                Map.Entry.<String, Integer>comparingByValue().reversed()).forEach(
                        entry -> check.put(entry.getKey(), entry.getValue()));
    }
    
    
}
