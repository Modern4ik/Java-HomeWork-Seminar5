package controller;

import java.util.HashMap;

import model.Model;
import view.View;
import view.Text;

public class Controller {
    public static boolean startProgramm() {
        HashMap<String, String[]> peoplePhones = new HashMap<>();
        String name = "";
        String phone = "";

        while (true) {
            int key = View.getInputKey();

            switch (key) {
                case 1:
                    Model.openFile(peoplePhones);
                    View.printMessage(Text.bookOpenSucess);
                    break;
                case 2:
                    Model.saveFile(peoplePhones);
                    View.printMessage(Text.saveSucess);
                    break;
                case 3:
                    View.printPhones(peoplePhones);
                    break;
                case 4:
                    name = View.getName();
                    phone = View.getPhone();

                    Model.addContact(name, phone, peoplePhones);

                    View.printMessage(Text.addingSucess);

                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    public static void consoleClear() {
        System.out.println("\033[H\033[J");
    }
}
