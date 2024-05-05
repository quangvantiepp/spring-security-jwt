package nature.sales_website.models.checker;

public class Checker {
    public static Boolean isNumeric (String someString){
        if (someString == null || someString.length() == 0) {
            return false;
        }
        return someString.chars().allMatch(Character::isDigit);
    }

    public static Boolean isLetter (String someString){
        if (someString == null || someString.length() == 0) {
            return false;
        }
        return someString.chars().allMatch(Character::isLetter);
    }

    public static Boolean isNumericFirst (String someString){
        if (someString == null || someString.length() == 0) {
             return false;
        }
        char c = someString.charAt(0);
        return c >= '0' && c <= '9';
    }
}
