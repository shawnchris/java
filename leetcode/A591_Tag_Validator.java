package leetcode;

public class A591_Tag_Validator {
    public boolean isValid(String code) {
        code = code.replaceAll("<!\\[CDATA\\[.*?\\]\\]>", "c");

        String prev = "";
        while (!code.equals(prev)) {
            prev = code;
            code = code.replaceAll("<([A-Z]{1,9})>[^<]*</\\1>", "t");
        }

        return code.equals("t");
    }
}
