package Main;

import java.util.Locale;

/**
 * The Controller class initiates the program, allowing users to choose a language and access the application.
 */
public class View {
    public static void main(String[] args) {
        Locale vietnamese = new Locale("vi");
        Locale english = Locale.ENGLISH;
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.print("Please choose one option: ");
        int choice = Controller.getInputIntLimit(1, 3, english);
        switch (choice) {
            case 1:
                Controller.display(vietnamese);
                break;
            case 2:
                Controller.display(english);
                break;
            case 3:
                return;
        }
    }
}
