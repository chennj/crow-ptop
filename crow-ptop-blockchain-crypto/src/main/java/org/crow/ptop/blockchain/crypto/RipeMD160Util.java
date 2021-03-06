package org.crow.ptop.blockchain.crypto;

import java.security.MessageDigest;

/**
 * RipeMD160消息摘要工具类
 * 
 * @author chenn
 *
 */
public class RipeMD160Util {

	/**
     * RipeMD160消息摘要
     */
    public static byte[] applyRipeMD160(byte[] data) {
        try {
            MessageDigest ripeMD160Digest = MessageDigest.getInstance("RipeMD160");
            return ripeMD160Digest.digest(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
