package interview.google;

public class RegEx {
	public static boolean isValidEmail(String email) {
		String pattern = "[0-9a-zA-Z_\\-]+@[0-9a-zA-Z]+\\.[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)*";
		return email.matches(pattern);
	}
	
	public static void main(String[] args) {
		System.out.println(isValidEmail("abc@def.com"));
		System.out.println(isValidEmail("lkdi_-2391@9skd.com.cn.cn.cn.cn")); //true
		System.out.println(isValidEmail("1")); //false
		System.out.println(isValidEmail("1@2")); //false
		System.out.println(isValidEmail("@.c")); //false
		System.out.println(isValidEmail("")); //
	}

}
