package org.crow.ptop.blockchain.crypto;

import java.security.MessageDigest;

/**
 * Sha256消息摘要工具类
 * 
 * @author chenn
 *
 */
public class SHA256Util {

	/**
     * Sha256消息摘要
     */
    public static byte[] applySha256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] sha256Digest = digest.digest(input);
            return sha256Digest;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
