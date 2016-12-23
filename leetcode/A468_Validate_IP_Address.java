package leetcode;



public class A468_Validate_IP_Address {
    public String validIPAddress(String IP) {
        if(IP == null || IP.isEmpty()) return "Neither";
        if(IP.contains(":")){
            return isV6(IP) ? "IPv6" : "Neither";
        }
        else {
            return isV4(IP) ? "IPv4" : "Neither";
        }
    }
    
    private boolean isV6(String ip){
        String gr[] = ip.split(":");
        if (gr.length != 8) return false;
        for(String g : gr){
            if(g.length() == 0 || g.length() > 4) return false;
            for(int i = 0; i < g.length(); i++) {
                char ch = g.charAt(i);
                if(!((ch >= '0' && ch <= '9') || (ch>='a' && ch<='f') || (ch>='A' &&ch<='F'))) return false;
            }
        }
        return true;
    }
    
    private boolean isV4(String ip){
    	if (ip.startsWith(".") || ip.endsWith(".")) return false;
        String gr[] = ip.split("\\.");
        if(gr.length != 4) return false;
        for(String g: gr){
            if(g.length() == 0 || g.length() > 3) return false;
            for(int i = 0; i < g.length(); i++){
                char ch = g.charAt(i);
                if(ch < '0' || ch > '9') return false;
            }
            int val = Integer.parseInt(g);
            if(val > 255 ) return false;
            if(g.charAt(0) == '0' && (val != 0 || g.length() != 1) ) return false;
        }
        return true;
    }
    
	public static void main(String[] args) {
		A468_Validate_IP_Address vi = new A468_Validate_IP_Address();
		System.out.println(vi.validIPAddress("1.1.1.1."));
		System.out.println(vi.validIPAddress("172.16.254.1"));
		System.out.println(vi.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
		System.out.println(vi.validIPAddress("256.256.256.256"));
		System.out.println(vi.validIPAddress("172.16.254.01"));
		System.out.println(vi.validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
		System.out.println(vi.validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334"));
		System.out.println(vi.validIPAddress("2001:0db8:85a3::8A2E:0370:7334"));
		System.out.println(vi.validIPAddress("2001:0db8:85a3::8A2E:0370:-0"));
	}

}

