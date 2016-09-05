package leetcode;

public class A168_Excel_Sheet_Column_Title {
    public String convertToTitle(int n) {
        String result = "";
        while (n > 0) {
            char c = (char)((n - 1) % 26 + 'A');
            result = c + result;
            n = (n - 1) / 26;
        }

        return result;
    }
}
