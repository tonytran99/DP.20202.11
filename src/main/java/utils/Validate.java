package utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10 || !phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public static boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public static boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
