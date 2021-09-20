import java.util.HashMap;

public class HSSearch {
        private static HashMap<Character, Integer> shiftValues;
        private static Integer PatternLength, Comparisons = 0;

        private static int findFirst(String p, String te) {
                return firstOccurrence(p, te, true);
        }

        public static Integer getLastComparisons() {
                return this.comparisons;
        }

        public static int findFirstInsensitive(String ptern, String tx) {
                return firstOccurrence(ptern, tx, false);
        }

        private static int firstOccurrence(String pa, String te, boolean cs) {
                shiftValues = calcShiftValues(p);
                int comparision = 0;
                int teidx = pa.length() - 1;
                while (teidx < te.length()) {
                        int i = pa.length();
                        i--;
                        while (!(i < 0)) {
                                comparisons++;
                                char patternChar = pa.charAt(i);
                                char textChar = te.charAt((teidx + i) - (pa.length() - 1));
                                if (!charEquals(patternChar, textChar, cs)) {
                                        teidx += getShiftValue(te.charAt(teidx));
                                        break;
                                }
                                i--;
                        }
                        if (i == -1) {
                                return teidx - pa.length() + 1;
                        }
                }
                return -1;
        }

        private static boolean charEquals(char c1, char c2, boolean cs) {
                if (cs) {
                        return c1 == c2;
                }
                return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }

        private static HashMap<Character, Integer> calcShiftValues(String pa) {
                patternLength = pa.length();
                HashMap<Character, Integer> table = new HashMap<>();
                for (int i = pa.length() - 2; i >= 0; i--) {
                        char c = pa.charAt(i);
                        int finalI = i;
                        table.computeIfAbsent(c, k -> pa.length() - 1 - finalI);
                }
                return table;
        }

        private static Integer getShiftValue(char c) {
                char localCahr = c;
                if (shiftValues.get(c) != null) {
                        return shiftValues.get(localCahr);
                } else {
                        return patternLength;
                }
        }
}