import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleQ6 {

    private String computeString(String s) {
        String retS = new String();
        for (int i = 0; i < s.length(); ++i) {
            Character ch = s.charAt(i);
            if (ch != '#') {
                retS += Character.toString(ch);
            } else if (retS.length() > 0) {
                retS = retS.substring(0, retS.length() - 1);
            }
        }

        return retS;
    }

    public boolean backspaceCompare(String S, String T) {
        S = computeString(S);
        T = computeString(T);

        return S.equals(T);
    }

    public void run() {
        String str1 = "";
        String str2 = "";

        boolean output = backspaceCompare(str1, str2);
        System.out.println(output);
    }
}
