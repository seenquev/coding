
public class GoogleQ4 {

        public String getHint(String secret, String guess) {
            int cows = 0;
            int bulls = 0;
            String copyStr = new String(guess);
            for (int i = 0; i < secret.length(); i++) {
                Character ch = secret.charAt(i);
                if (ch == guess.charAt(i)) {
                    int index = copyStr.indexOf(ch);
                    copyStr = copyStr.substring(0, i) + "*" + copyStr.substring(i + 1);
                    bulls++;
                } else if (copyStr.contains(Character.toString(ch))) {
                    boolean found = false;
                    while (true) {
                        int index = copyStr.indexOf(ch);
                        if (index < 0) {
                            break;
                        }
                        if (secret.charAt(index) == copyStr.charAt(index)) {
                            copyStr = copyStr.substring(0, index) + "*" + copyStr.substring(index + 1);
                            continue;
                        }
                        copyStr = copyStr.substring(0, index) + "*" + copyStr.substring(index + 1);
                        found = true;
                        break;
                    }
                    if (found) {
                        cows++;
                    }
                }
            }

            // Serialize
            return String.format("%dA%dB", bulls, cows);
        }


    public void run() {
//        String secret = "1123";
//        String guess = "0111";

        String secret = "011";
        String guess = "110";

        String output = getHint(secret, guess);
        System.out.println(output);
    }
}
