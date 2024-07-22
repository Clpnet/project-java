package utils;

import java.util.Base64;

public class Utils {

	    public static String encodeFileToBase64(byte[] fileBytes) {
	        return Base64.getEncoder().encodeToString(fileBytes);
	}
}
