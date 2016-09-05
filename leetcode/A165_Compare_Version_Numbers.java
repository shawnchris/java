package leetcode;

public class A165_Compare_Version_Numbers {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int m = Math.min(v1.length, v2.length);
        boolean longer = (v1.length>v2.length) ?  true : false;
        for (int i=0; i<m; i++)
            if (Integer.valueOf(v1[i])>Integer.valueOf(v2[i]))
                return 1;
            else if (Integer.valueOf(v1[i])<Integer.valueOf(v2[i]))
                return -1;
        if (v1.length==v2.length)
            return 0;
        if (longer) {
        	for (int i=m; i<v1.length; i ++)
        		if (Integer.valueOf(v1[i])!=0)
        			return 1;
        }
        else {
        	for (int i=m; i<v2.length; i ++)
        		if (Integer.valueOf(v2[i])!=0)
        			return -1;
        }
        return 0;
    }
}
