package com.glassdoor;

import java.nio.charset.*;
import java.nio.*;

public class UTF8 {
	public static boolean isValidUTF8( byte[] input ) {

	    CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();

	    try {
	        cs.decode(ByteBuffer.wrap(input));
	        return true;
	    }
	    catch(CharacterCodingException e){
	        return false;
	    }
	}
	
	public static boolean isUTF8MisInterpreted( String input ) {
		//convenience overload for the most common UTF-8 misinterpretation
		//which is also the case in your question
		return isUTF8MisInterpreted( input, "Windows-1252");  
	}

	public static boolean isUTF8MisInterpreted( String input, String encoding) {

		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
		ByteBuffer tmp;
		try {
			tmp = encoder.encode(CharBuffer.wrap(input));
		}

		catch(CharacterCodingException e) {
			return false;
		}

		try {
			decoder.decode(tmp);
			return true;
		}
		catch(CharacterCodingException e){
			return false;
		}       
	}

	public static void main(String args[]) {
		System.out.println(isValidUTF8(new byte[]{24}));
		System.out.println(isValidUTF8(new byte[]{0,13}));
		System.out.println(isValidUTF8(new byte[]{40,00}));
		System.out.println(isValidUTF8(new byte[]{14,00,00}));
		//System.out.println(isValidUTF8(new byte[]{Byte.valueOf("192")}));
		
		String test = "guide (but, yeah, itâ€™s okay to share it with â€˜em).";
		String test2 = "guide (but, yeah, it’s okay to share it with ‘em).";
		System.out.println( isUTF8MisInterpreted(test)); //true
		System.out.println( isUTF8MisInterpreted(test2)); //false

	}
}
