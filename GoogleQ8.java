import java.util.Arrays;

public class GoogleQ8 {

    private static int[] contains(String source, Character ch, int index) {
        int[] ret = new int[2];

        int retIndex = source.indexOf(ch, index);
        if (retIndex == -1) {
            ret[1] = 1;
            retIndex = source.indexOf(ch);
        }
        ret[0] = retIndex;

        return ret;
    }

    public int shortestWay(String source, String target) {
        // Let's determine whether it is possible at all
        for (int i = 0; i < target.length(); ++i) {
            Character ch = target.charAt(i);
            if (!source.contains(Character.toString(ch))) {
                return -1;
            }
        }

        // Let's create buffer string we will trying to fit in source
        int count = 1;
        int[] data = {-1, 0};

        for (int j = 0; j < target.length(); ++j) {
            if (j + 1 <= target.length()) {
                Character ch = target.charAt(j);
                data = contains(source, ch, data[0] + 1);
                if (data[1] > 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public void run() {
//        String source = "abc";
//        String target = "abcbc";

//        String source = "abc";
//        String target = "abcdbc";

//        String source = "xyz";
//        String target = "xzyxz";

//        String source = "adbsc";
//        String target = "addddddddddddsbc";

//        String source = "aaaaa";
//        String target = "aaaaaaaaaaaaa";

//        String source = "adbsc";
//        String target = "addddddddddddsbc";

//        String source = "ugxhfjvmzvkzzlmpryyiqxcujshflkreqqorcbefzvjsnfokfydgajitaqcsqlywizwvkjsqjqpjagvf"; // 30
//        String target = "rfuexnetdlhtlniubuqmalbrmmxrzhmkrzcwswytnudndovcwbixttqrqnsglyhkmbwphztjottflnoj";

//        String source = "hqexgigbagwejpefllkztrdrbvplbgprsxsuzbwlkaneolxurhvjczyhjqvqbkumbwfvkzerwqjlzgrn"; // 29
//        String target = "bwyxsbcoarwkwhnuhowgymflyrovhozsfuqrlzcldopejqlpagsjnnpfeqrlgjlulcglvswrboyforwdfhkofpxemtyrqpieltpp";

        String source = "crlzlnuhhwgrwwjcbkixocaatjtezwztgnbuljzkxrrjspgqxiuuqavodztpppdohemdtzjswmcvndiqviahniycneowwfephukgrgfphgkwqogyhimchmpbwmfonpqxtyfvswmwgpdosbsvasrtuhtnreurrirrmowutmtrikpzdtnasltkhgfaamuorumoywxbpxucbiwnugkiqmihidqbomqlyfignjmvdlpnxhskkmlwnvegayiafdqmmdaamzlbtposcxyuoapqffjxqgeskbemstkpamdfwpmkmqvzxrlojwfxvtzwjdtjzgbaxgdijivnvlylhlxvqmosjmpnhiziqjexwnthxynvuznuxreoukjrhmludkihconwhpxgdsxhjqiplijcdetghfmctzeonlpuvyfuovyyksxbbscdmfnkdgwanxpjeulicphtolprwulwvfuypzyvnpensxodxxexplhnivbwskjjvygfdsodubduzuiauyukbfsyfpgigecccoafclrmwdjcuoildcznmqfgpbuorbghahlygddqhhzikvcjyxtwkjwviwanfqbvkvufrawpmvantjmmuujgautfpvtwwnpkrpkdyiovyqqasdyfhytg"; // 26
        String target = "qggsfsgbyhpzypxfghsvxunjoxdqxndvfisweotkchsnfmshwsanulfpwgcwailntbvaunxqmrlvmufdrxjamkixgyasjblwptpwyoskeksjvlbsfurlilvhkjhvozpsrnyrzkqqgyfobghqqoodchwlojbfzwpastapezusnlqffkiobybkszwyfgqjofclfvqebifrdvmmktfozxaqjruyhlashvofhjsxkuelknvisgnuktqisexxpeckrcvmfmhfgxlezlsidgiectdudwwfpvtohxsgiopummzwmhjjshlygagurauolxxcopyvpljjikgpbgskfqufcgtkrhagywvruziihstrspdriamgdwehzwojvwhhtyxhtrrnbvbddwobbkxxjutjrztrpwwooafkbaxiahdqwoctomeytmiauanfutejtkwdvmwbactthlbvgmgsypqxjpbkfmaoziawqgnirqjixencnlbimhooiqxjksuvkpmajhemmrhwvghvhosdwlblyaxkflycndgftjfxlvrerzywhxcbciuhatwszdhasggliwzpkrrxxydcbgflzwefxzlhtvbbniarqugasulcwsbxbldtitakwosjchrhpxwrdnfq";

        int ret = shortestWay(source, target);
        System.out.println(ret);
    }
}
