package com.gitee;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES编/解码器
 * @author fukui
 */
public abstract class AESCodec {

	public static String Encrypt(String sSrc, String key) {
		if (key == null) {
			throw new NullPointerException("key为空null");
		}
		// 判断Key是否为16位
		if (key.length() != 16) {
			throw new AESException("Key长度不是16位");
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher;
		byte[] encrypted = new byte[0];
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encrypted = cipher.doFinal(sSrc.getBytes());
		} catch (Exception e) {
			throw new AESException("加密失败", e);
		}
		return byte2hex(encrypted).toLowerCase();
	}

	public static String EncryptBase64(String sSrc, String key) {
		if (key == null) {
			return null;
		}
		// 判断Key是否为16位
		if (key.length() != 16) {
			return null;
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes());
			return new String(Base64.encodeBase64(encrypted));
		} catch (Exception e) {
			throw new AESException("加密失败", e);
		}
	}


    // 解密
	public static String Decrypt(String sSrc, String key) {
		try {
			// 判断Key是否正确
			if (key == null) {
				throw new NullPointerException("Key为空null");
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				throw new AESException("Key长度不是16位");
			}
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = parseHexStr2Byte(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception ex) {
			throw new AESException("解密失败", ex);
		}
	}

    // 解密
	public static String DecryptBase64(String sSrc, String key) {
		try {
			// 判断Key是否正确
			if (key == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				throw new AESException("Key长度不是16位");
			}
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception ex) {
			throw new AESException("解密失败", ex);
		}
	}

	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

    // 十六进制字符串转字节数组
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	
}
