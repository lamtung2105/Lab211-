package Main;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Random;

/**
 * This class provides utility methods for account validation and captcha
 * generation.
 */
public class Model {

    // Regular expression pattern for a valid account number.
    private static final String ACCOUNT_NUMBER_VALID = "^\\d{10}$";
    // Array of characters used for generating captchas.
    private static final char[] captchaChars = {'1', 'A', 'a', 'B', 'b', 'C', 'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n', 'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x', 'Y', 'y', 'Z', 'z', '9'};

    /**
     * Validates an account number.
     *
     * @param accountNumber The account number to validate.
     * @return True if the account number is valid (exactly 10 digits),
     * otherwise false.
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches(ACCOUNT_NUMBER_VALID);
    }

    /**
     * Validates a password based on length and character criteria.
     *
     * @param password The password to validate.
     * @return True if the password is valid (between 8 and 31 characters with
     * at least one letter and one digit), otherwise false.
     */
    public static boolean isValidPassword(String password) {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) {
            return false;
        } else {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword; i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                } else if (Character.isLetter(password.charAt(i))) {
                    countLetter++;
                }
            }
            return countDigit >= 1 && countLetter >= 1;
        }
    }

    /**
     * Generates a random captcha text.
     *
     * @return The generated captcha text (6 characters chosen from a predefined
     * character set).
     */
    public static String generateCaptchaText() {
        StringBuilder captchaTextBuilder = new StringBuilder();
        int length = 6;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(captchaChars.length);
            captchaTextBuilder.append(captchaChars[index]);
        }
        return captchaTextBuilder.toString();
    }

    /**
     * Checks if the input captcha matches the generated captcha.
     *
     * @param userInput The input captcha text.
     * @param generatedCaptcha The generated captcha text to compare with.
     * @return True if the input captcha matches the generated captcha,
     * otherwise false.
     */
    public static boolean isCaptchaCorrect(String userInput, String generatedCaptcha) {
        if (userInput.length() != generatedCaptcha.length()) {
            return false;
        }

        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) != generatedCaptcha.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
