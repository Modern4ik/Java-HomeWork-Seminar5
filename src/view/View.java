package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

import model.Model;
import controller.Controller;

public class View {
    public static int getInputKey() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Controller.consoleClear();
            printMenu();

            System.out.println();
            System.out.printf(Text.inputMessage);

            int input = scanner.nextInt();

            return input;
        }

    }

    public static void printPhones(HashMap<String, String[]> book) {
        TreeMap<String, Integer> tree = new TreeMap<>();
        LinkedHashMap<String, Integer> lMapCheck = new LinkedHashMap<>();

        for (var el : book.entrySet()) {
            tree.put(el.getKey(), el.getValue().length);
        }

        Model.getSortedMap(lMapCheck, tree);

        for (var el : lMapCheck.entrySet()) {
            System.out.printf("%s - %s\n", el.getKey(), Arrays.toString(
                    book.get(el.getKey())).replace(
                            "[", "")
                    .replace("]", ""));
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
    }

    public static void printMessage(String message) {
        System.out.println(String.format("%50s", "").replace(" ", "="));
        System.out.println(message);
        System.out.println(String.format("%50s", "").replace(" ", "="));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
    }

    public static String getName() {
        Scanner scan = new Scanner(System.in);

        System.out.printf(Text.inputName);

        String name = scan.nextLine();
        name = capitalize(name);

        return name;
    }

    public static String getPhone() {
        Scanner scan = new Scanner(System.in);

        System.out.printf(Text.inputPhone);

        String phone = scan.nextLine();
        phone = capitalize(phone);

        return phone;
    }

    private static void printMenu() {
        System.out.println(Text.menu);
    }

    private static String capitalize(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        return str;
    }

}
