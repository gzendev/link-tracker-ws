package com.fierastudio.linktracker.ws.util;

public class Base10 {
	
	private static final String CHARACTERS_MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int MAP_SIZE = CHARACTERS_MAP.toCharArray().length;
	
	public static long encode(String str) {
		long base10 = 0;
		char[] chars = new StringBuilder(str).reverse().toString().toCharArray();
		
		for(int i = chars.length-1; i >= 0; i--) {
			base10 += CHARACTERS_MAP.indexOf(chars[i]) * (long) Math.pow(MAP_SIZE, i);
		}
		return base10;
	}
}
