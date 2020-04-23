import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GoogleQ9 {

    private String replaceStr(String str, int len, int pos) {
        return str.substring(0, pos) + (len > 0 ? Integer.toString(len) : "") + str.substring(pos + len, str.length());
    }

    private void findAbbRec(int pos, String word, List<String> ret) {
        if (pos >= word.length()) {
            return;
        }

        for (int j = 1; j <= word.length(); ++j) {
            for (int i = pos; i < word.length(); ++i) {
                if (i > 0 && Character.isDigit(word.charAt(i - 1))) {
                    continue;
                }
                if (i + j <= word.length()) {
                    String resStr = replaceStr(word, j, i);
                    ret.add(resStr);
                    findAbbRec(i + 1, resStr, ret);
                } else {
                    break;
                }
            }
        }
    }

    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        findAbbRec(0, word, ret);
        ret.add(word);
        return ret;
    }

    public void run() {
        String str = "wordword";
        List<String> ret = generateAbbreviations(str);
        System.out.println(Arrays.toString(ret.toArray()));
    }
}