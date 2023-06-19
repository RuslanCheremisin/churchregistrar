package efant.el.churchregistrar.util;

public class ValidateUtil {
    public static String stringIsValid(String string) {
        if (string.isEmpty() || string.isBlank() || string == null) {
            throw new IllegalArgumentException("Text value is empty/blank/null");
        }
        return string;
    }

    public static String phoneNumberIsValid(String phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber.isBlank() || phoneNumber == null) {
            throw new IllegalArgumentException("Text value is empty/blank/null");
        }
        String number = phoneNumber.replace(" ", "");
        if ((number.startsWith("+7") && number.length() == 12) || (number.startsWith("8") && number.length() == 11)) {
            return number;
        }else {
            throw new IllegalArgumentException("Incorrect phone number format! Use +7 123 4567890 or 8 123 4567890");
        }
    }
}
