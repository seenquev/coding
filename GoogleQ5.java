import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleQ5 {

    private static HashMap<Character, Integer> cache = new HashMap<>();

    private static int getOrder(Character ch, String order) {
        Integer orderCache = cache.get(ch);
        if (orderCache != null) {
            return orderCache;
        }

        for (int i = 0; i < order.length(); ++i) {
            if (order.charAt(i) == ch) {
                cache.put(ch, i);
                return i;
            }
        }

        return Integer.MIN_VALUE;
    }

    public boolean isAlienSorted(String[] words, String order) {
        int maxStrSize = Integer.MIN_VALUE;

        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            int strSize = words[i].length();
            if (maxStrSize < strSize) {
                maxStrSize = strSize;
            }
            wordList.add(words[i]);
        }

        for (int j = 0; j < maxStrSize; j++) {
            int previousOrder = Integer.MAX_VALUE;
            for (int k = wordList.size() - 1; k >= 0; --k) {
                String word = wordList.get(k);

                Character ch = (word.length() - 1 >= j ? word.charAt(j) : null);
                int orderNum = Integer.MIN_VALUE;
                if (ch != null) {
                    orderNum = getOrder(ch, order);
                    if (orderNum > previousOrder) {
                        return false;
                    } else if (orderNum < previousOrder && previousOrder < Integer.MAX_VALUE) {
                        wordList.remove(k + 1);
                    }
                }
                if (orderNum < previousOrder) {
                    previousOrder = orderNum;
                }
            }

            if (wordList.size() <= 1) {
                return true;
            }
        }

        return true;
    }

    public void run() {
//        String[] words = new String[]{"hello","leetcode"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";

//        String[] words = new String[]{"word","world","row"};
//        String order = "worldabcefghijkmnpqstuvxyz";

        String[] words = new String[]{"mtkwpj","wlaees"};
        String order = "evhadxsqukcogztlnfjpiymbwr";

        boolean output = isAlienSorted(words, order);
        System.out.println(output);
    }
}
