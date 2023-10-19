package Main;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * This class, View, is responsible for handling user interactions, including
 * input, validation, and displaying messages.
 */
public class Controller {

    private static final Scanner in = new Scanner(System.in);

    /**
     * Get an integer input within a specified range.
     *
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @param language The selected language for displaying messages.
     * @return The validated integer input.
     */
    public static int getInputIntLimit(int min, int max, Locale language) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(in.nextLine());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException ex) {
                getWordLanguage(language, "errorCheckInputIntLimit");
                System.out.println();
            }
        }
    }

    /**
     * Get a non-empty string input.
     *
     * @param language The selected language for displaying messages.
     * @return The validated non-empty string input.
     */
    public static String getInputString(Locale language) {
        String result;
        while (true) {
            result = in.nextLine();
            if (result.length() == 0) {
                getWordLanguage(language, "errCheckInputIntLimit");
                System.out.println();
            } else {
                return result;
            }
        }
    }

    /**
     * Get a valid account number input.
     *
     * @param language The selected language for displaying messages.
     * @return The validated account number as an integer.
     */
    public static int getAccountInput(Locale language) {
        while (true) {
            String result = getInputString(language);
            if (Model.isValidAccountNumber(result)) {
                return Integer.parseInt(result);
            }
            getWordLanguage(language, "errCheckInputAccount");
            System.out.println();
        }
    }

    /**
     * Get a valid password input.
     *
     * @param language The selected language for displaying messages.
     * @return The validated password as a string.
     */
    public static String getPasswordInput(Locale language) {
        String password;

        while (true) {
            password = getInputString(language);

            if (Model.isValidPassword(password)) {
                break; // Break out of the loop if the password is valid
            }

            // Display error messages based on the specific validation issues
            if (password.length() < 8 || password.length() > 31) {
                getWordLanguage(language, "errCheckLengthPassword");
            } else if (!password.matches(".*\\d.*") && !password.matches(".*[a-zA-Z].*")) {
                getWordLanguage(language, "errCheckAlphanumericPassword");
            } else if (!password.matches(".*\\d.*")) {
                getWordLanguage(language, "errCheckDigitPassword");
            } else if (!password.matches(".*[a-zA-Z].*")) {
                getWordLanguage(language, "errCheckLetterPassword");
            }
            System.out.println();
        }

        return password;
    }

    /**
     * Display the login interface, validate user input, and handle captcha
     * verification.
     *
     * @param language The selected language for displaying messages.
     */
    public static void display(Locale language) {
        getWordLanguage(language, "enterAccountNumber");
        int accountNumber = getAccountInput(language);
        getWordLanguage(language, "enterPassword");
        String password = getPasswordInput(language);
        String captchaGenerated = Model.generateCaptchaText();
        while (true) {
            System.out.println(captchaGenerated);
            getWordLanguage(language, "enterCaptcha");
            String captchaInput = getInputString(language);
            if (Model.isCaptchaCorrect(captchaInput, captchaGenerated)) {
                getWordLanguage(language, "loginSuccess");
                System.out.println();
                return;
            } else {
                getWordLanguage(language, "errCaptchaIncorrect");
                System.out.println();
            }
        }
    }

    /**
     * Retrieve a language-specific message and print it to the console.
     *
     * @param curLocate The selected language locale.
     * @param key The key to access the message.
     */
    public static void getWordLanguage(Locale curLocate, String key) {
        ResourceBundle words = ResourceBundle.getBundle("Language/" + curLocate, curLocate);
        String value = words.getString(key);
        System.out.printf(value);
    }
}
