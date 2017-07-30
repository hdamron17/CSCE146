public class Main {
    public static boolean contains(String inner, String outer) {
        int innerLength = inner.length();
        int outerLength = outer.length();

        if (innerLength == 0) {
            return true;
        }

        if (innerLength > outerLength || outerLength == 0) {
            return false;
        }

        if (inner.charAt(0) == outer.charAt(0)) {
            if (outer.length() > 0 &&
                    contains(inner.substring(1,innerLength), outer.substring(1, outerLength))) {
                return true;
            }
        } else {
            return contains(inner, outer.substring(1,outerLength));
        }

        return false;
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"", ""},
            {"Sand", "Sandwich"},
            {"Pickles", "Pickle"},
            {"ic", "Pickle"},
            {"lamp", "lamp"},
            {"no", "never"}
        };

        for (String[] pair : testCases) {
            System.out.println("'" + pair[0] + "' in '" + pair[1] + "'? " + (contains(pair[0], pair[1]) ? "Yes" : "No"));
        }
    }
}
