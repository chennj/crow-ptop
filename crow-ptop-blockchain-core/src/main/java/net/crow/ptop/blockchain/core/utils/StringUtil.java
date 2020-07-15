package net.crow.ptop.blockchain.core.utils;

public class StringUtil {

	public static boolean isEquals(String obj1,String obj2){
        if(obj1 == obj2){
            return true;
        }
        if(obj1 == null || obj2 == null){
            return false;
        }
        return obj1.equals(obj2);
    }
}
