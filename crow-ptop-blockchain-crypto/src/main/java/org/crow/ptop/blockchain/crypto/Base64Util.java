package org.crow.ptop.blockchain.crypto;

import org.bouncycastle.util.encoders.Base64;

/**
 * Base64工具类
 * @author chenn
 *
 */
public class Base64Util {

    /**
     * Base64编码
     */
    public static String encode(byte[] input) {
        return Base64.toBase64String(input);
    }

    /**
     * Base64解码
     */
    public static byte[] decode(String input) {
        return Base64.decode(input);
    }
}
