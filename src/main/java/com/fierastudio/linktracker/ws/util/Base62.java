package com.fierastudio.linktracker.ws.util;

public class Base62 {
	
	private static final char MAP[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	private static final int MAP_SIZE = MAP.length;
	
	public static String encode(long n) {
		StringBuffer shorturl = new StringBuffer();
		while (n > 0) {  
            shorturl.append(MAP[(int) n % MAP_SIZE]); 
            n = n / 62;  
        }  
        return shorturl.reverse().toString(); 
	}
}
