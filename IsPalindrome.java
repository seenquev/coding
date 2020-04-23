public class IsPalindrome {
    public boolean isPalindrome(String s) {
        // Convert string
        String str = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");


        // Check if valid
        int j = str.length() - 1;
        for (int i = 0; i < str.length(); i++) {
            Character ch1 = str.charAt(i);
            Character ch2 = str.charAt(j);

            if (!ch1.equals(ch2)) {
                return false;
            }
            j--;
        }

        return true;
    }

    public void run() {
        String str = "A man, a plan, a canal: Panama";
        boolean ret = isPalindrome(str);
        System.out.println(ret);
    }
}
