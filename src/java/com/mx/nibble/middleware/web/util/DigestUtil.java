package com.mx.nibble.middleware.web.util;

/**
 * Author: Administrator
 * Date: 2008/3/24
 * Time: 下午 11:19:20
 */
public class DigestUtil {
    /**
     * The byte[] returned by MessageDigest does not have a nice
     * textual representation, so some form of encoding is usually performed.
     * <p/>
     * This implementation follows the example of David Flanagan's book
     * "Java In A Nutshell", and converts a byte array into a String
     * of hex characters.
     * <p/>
     * Another popular alternative is to use a "Base64" encoding.
     *
     * @param input byte
     * @return byte array into a String of hex characters
     */
    public static String hexEncode(byte[] input) {
        StringBuffer result = new StringBuffer();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < input.length; ++idx) {
            byte b = input[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }
    
}
