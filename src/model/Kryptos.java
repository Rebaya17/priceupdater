/*
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 */

package model;

import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kryptos class.
 */
public class Kryptos {
    private static final MessageDigest SHA256 = genMessageDigest("SHA-256");
    private static final MessageDigest SHA1 = genMessageDigest("SHA-1");
    private static final MessageDigest MD5 = genMessageDigest("MD5");
    
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static final char DELIMITER = '=';
    
    /**
     * Return a MessageDigest object that implements the specified algorithm
     * 
     * @param algorithm The name of the algorithm request
     * @return 
     */
    public static MessageDigest genMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Kryptos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Get the SHA-256 hash of the supplied string
     * 
     * @param str String to calculate SHA-256
     * @return SHA-256 checksum
     */
    public static String sha256(String str) {
        if (str == null) return null;
        
        return new BigInteger(1, SHA256.digest(str.getBytes(StandardCharsets.UTF_8))).toString(16).toUpperCase();
    }
    
    /**
     * Get the SHA-1 hash of the supplied string
     * 
     * @param str String to calculate SHA-1
     * @return SHA-1 checksum
     */
    public static String sha1(String str) {
        if (str == null) return null;
        
        return new BigInteger(1, SHA1.digest(str.getBytes(StandardCharsets.UTF_8))).toString(16).toUpperCase();
    }
    
    /**
     * Get the MD5 hash of the supplied string
     * 
     * @param str String to calculate MD5
     * @return MD5 checksum
     */
    public static String md5(String str) {
        if (str == null) return null;
        
        return new BigInteger(1, MD5.digest(str.getBytes(StandardCharsets.UTF_8))).toString(16).toUpperCase();
    }
    
    /**
     * Enconde the supplied string
     * 
     * @param str String to encode
     * @return Encoded string
     */
    public static String encode(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length =  bytes.length;
        
        byte[] reversed = new byte[length];
        
        for (int i = 0, j = length - 1; i < length; i++, j--)
            reversed[j] = bytes[i] += length;
        
        return ENCODER.encodeToString(reversed) + DELIMITER + length;
    }
    
    /**
     * Denconde the supplied string
     * 
     * @param str String to decode
     * @return Decoded string
     * @throws IllegalArgumentException if the string has an invalid format
     */
    public static String decode(String str) {
        int index = str.lastIndexOf(DELIMITER);
        int length;
        byte[] bytes;
        byte[] reversed;
        
        try {
            length = parseInt(str.substring(index + 1));
            reversed = DECODER.decode(str.substring(0, index).getBytes());
            bytes = new byte[length];
            
            if (reversed.length != length) throw new IllegalArgumentException("Invalid length value");
        }  catch (IllegalArgumentException ex) {
            System.err.println("Error: invalid encoded string format");
            Logger.getLogger(Kryptos.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
        
        for (int i = 0, j = length - 1; i < length; i++, j--)
            bytes[i] = reversed[j] -= length;
        
        return new String(bytes);
    }
}
