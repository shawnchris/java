package leetcode;

public class A393_UTF8_Validation {
	
	public boolean validUtf8(int[] data) {
		for (int i = 0; i < data.length; i++) {
			// find how many leading "1"
			int number = data[i], mask = 1 << 7, count = 0;
			while (number >= mask && number > 0) {
				count++;
				number ^= mask;
				mask >>= 1;
			}
			
			// corner cases
			if (count == 0) continue;
			if (count == 1) return false;
			
			// check "10"s
			int j = i + 1;
			count--;
			mask = (1 << 7) | (1 << 6); // 11000000
			while (j < data.length && count > 0) {
				number = data[j];
				number &= mask;
				if (number == (1 << 7)) {
					count--;
					j++;
				}
				else {
					return false;
				}
			}
			if (count != 0) return false;
			
			i = j - 1;
		}
		
		return true;
	}

	public static void main(String[] args) {
		A393_UTF8_Validation v = new A393_UTF8_Validation();
		int[] data1 = { 197, 130, 1 };
		System.out.println(v.validUtf8(data1)); // true
		int[] data2 = { 235, 140, 4 };
		System.out.println(v.validUtf8(data2)); // false
		int[] data3 = { 10 };
		System.out.println(v.validUtf8(data3)); // true
		int[] data4 = { 145 };
		System.out.println(v.validUtf8(data4)); // false
		int[] data5 = {228,189,160,229,165,189,13,10};
		System.out.println(v.validUtf8(data5)); // true
		
	}

}
