package net.crow.ptop.blockchain.core.utils;

import java.io.File;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;

import net.crow.ptop.blockchain.core.config.GlobalConfig;

public class LevelDBUtil {

	private static WriteOptions writeOptions = new WriteOptions();
	
	static {
        writeOptions.sync(true);
        writeOptions.snapshot(true);
    }
	
	public static void put(DB db, String key, byte[] bytesValue) throws DBException {
        put(db,stringToBytes(key),bytesValue);
    }

    public static void put(DB db, byte[] bytesKey, byte[] bytesValue) throws DBException {
        db.put(bytesKey,bytesValue);
    }

    public static void write(DB db, WriteBatch writeBatch) throws DBException {
        db.write(writeBatch);
    }

    public static byte[] get(DB db, String key) throws DBException {
        return get(db,stringToBytes(key));
    }

    public static byte[] get(DB db, byte[] bytesKey) throws DBException {
        return db.get(bytesKey);
    }

    public static void delete(DB db,String key) throws DBException {
        delete(db,stringToBytes(key));
    }

    public static void delete(DB db,byte[] bytesKey) throws DBException {
        db.delete(bytesKey);
    }

    public static DB createDB(File dbFile) throws Exception{
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        return factory.open(dbFile, options);
    }

    public static byte[] stringToBytes(String strValue) {
        return strValue.getBytes(GlobalConfig.GLOBAL_CHARSET);
    }

    public static String bytesToString(byte[] bytesValue) {
        return new String(bytesValue, GlobalConfig.GLOBAL_CHARSET);
    }
}
