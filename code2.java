import java.util.HashMap;

public class BoyerMooreSearch {
        private HashMap<Character, Integer> shiftValues;
        private Integer patternLength, comparisonCount = 0;

        private int findFirst(String p, String te) {
                return firstOccurrence(p, te, true);
        }

        public Integer getLastComparisonCount() {
                return this.comparisonCount;
        }

        public int findFirstInsensitive(String ptern, String tx) {
                return firstOccurrence(ptern, tx, false);
        }

        /**
         * 
         * @param pa The pattern that being checked if exists in the text
         * @param te The text which is checked by the pattern
         * @param cs The boolean showing the search algorithm is case sensitive or not
         * @return The first occurence index if found, -1 if not found pattern
         */
        private int firstOccurrence(String pa, String te, boolean cs) {
                shiftValues = calcShiftValues(pa);
                int teIdx = pa.length() - 1;
                while (teIdx < te.length()) {
                        int i = pa.length() - 1;
                        while (!(i < 0)) {
                                comparisonCount++;
                                char patternChar = pa.charAt(i);
                                char textChar = te.charAt((teIdx + i) - (pa.length() - 1));
                                if (!charEquals(patternChar, textChar, cs)) {
                                        teIdx += getShiftValue(textChar);
                                        break;
                                }
                                i--;
                        }
                        if (i == -1) {
                                return teIdx - pa.length() + 1;
                        }
                }
                return -1;
        }

        private boolean charEquals(char c1, char c2, boolean cs) {
                if (cs) {
                        return c1 == c2;
                }
                return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }

        /**
         * @param pa A string that used to produce table shiftValues
         * @return it return tables represent the position of pattern from right -> left
         *         except the last character
         */
        private HashMap<Character, Integer> calcShiftValues(String pa) {
                patternLength = pa.length();
                HashMap<Character, Integer> table = new HashMap<>();
                for (int i = pa.length() - 2; i >= 0; i--) {
                        char c = pa.charAt(i);
                        table.computeIfAbsent(c, k -> pa.length() - 1 - i);
                }
                return table;
        }

        private Integer getShiftValue(char c) {
                if (shiftValues.get(c) != null) {
                        return shiftValues.get(c);
                } else {
                        return patternLength;
                }
        }
}