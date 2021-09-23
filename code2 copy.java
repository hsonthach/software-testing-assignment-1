import java.util.HashMap;

public class HSSearch {
        private static HashMap<Character, Integer> shiftValues;
        private static Integer patternLength, comparisons = 0;

        public static int findFirst(String p, String te) { // REVIEW: This method should be public and should we
                                                           // findFirstSensitive
                return firstOccurrence(p, te, true);
        }

        public static Integer getLastComparisons() { // REVIEW: Should named getComparisionCount
                return comparisons;// REVIEW: should not call instance variables inside static variables
        }

        public static int findFirstInsensitive(String ptern, String tx) {
                return firstOccurrence(ptern, tx, false);
        }

        /**
         * 
         * @param pa The pattern that being checked if exists in the text
         * @param te The text which is checked by the pattern
         * @param cs The boolean showing the search algorithm is case sensitive or not
         * @return The first occurence index if found, -1 if not found pattern
         */
        // REVIEW: The method which have ambiguous name of parameter should be
        // documented
        private static int firstOccurrence(String pa, String te, boolean cs) {
                shiftValues = calcShiftValues(pa); // REVIEW: undefined p, should be pa
                int comparison = 0; // REVIEW: useless variable
                int teidx = pa.length() - 1; // REVIEW: Should named teIdx
                while (teidx < te.length()) {
                        int i = pa.length() - 1; // REVIEW: Should be i = pa.length() -1
                        while (!(i < 0)) {
                                comparisons++; // REVIEW: Should be comparisons
                                char patternChar = pa.charAt(i);
                                char textChar = te.charAt((teidx + i) - (pa.length() - 1)); // textChar start from right
                                                                                            // -> left of teidx
                                if (!charEquals(patternChar, textChar, cs)) {
                                        // REVIEW: Should be textChar
                                        teidx += getShiftValue(te.charAt(teidx)); // Shift right
                                        break;
                                }
                                i--;
                        }
                        // REVIEW: Should update texidx
                        if (i == -1) {
                                return teidx - pa.length() + 1; // Return the start of pattern in text
                        }
                }
                return -1;
        }

        private static boolean charEquals(char c1, char c2, boolean cs) { // REVIEW: Should be charsEqual
                if (cs) {
                        return c1 == c2;
                }
                return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }

        // REVIEW: This method behavior should be documented, it return tables represent
        // the position of pattern from right -> left except the last character
        private static HashMap<Character, Integer> calcShiftValues(String pa) { // REVIEW: Should only we private not
                                                                                // private static
                patternLength = pa.length();
                HashMap<Character, Integer> table = new HashMap<>();
                // pa.length() -2 because pa.length() - 1 is not used if we shift
                // pattern
                for (int i = pa.length() - 2; i >= 0; i--) {
                        char c = pa.charAt(i);
                        // REVIEW: Should not make useless alias
                        // REVIEW: i should be from 1 to be honest
                        // REVIEW: Should not show parameter if we are not gonna use it k -> to () ->
                        table.computeIfAbsent(c, k -> pa.length() - 1 - i);
                }
                return table;
        }

        private static Integer getShiftValue(char c) {
                char localCahr = c; // REVIEW: useless alias
                if (shiftValues.get(c) != null) {
                        return shiftValues.get(localCahr); // char not match pattern but belong to pattern, just have
                                                           // wrong order -> Shift to match order
                                                           // Ex: txt: qweqabcdeasda pattern fbc
                                                           // b is not match c but belong to pattern -> shift 1 to match
                                                           // order
                } else {
                        return patternLength; // text not match pattern and not text not belong to pattern -> shift
                                              // entire pattern
                        // Ex: txt: qweqabcdeasda pattern fbc
                }
        }
}