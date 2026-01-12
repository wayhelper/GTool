package com.way.gtool.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class DESPlus {
	private static String strDefaultKey = "jingway";

	private SecretKeySpec keySpec;

	private static final int GCM_IV_LENGTH = 12; // 96 bits
	private static final int GCM_TAG_LENGTH = 128; // 128 bits

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public DESPlus() throws Exception {
		this(strDefaultKey);
	}

	public DESPlus(String strKey) throws Exception {
		this.keySpec = createKey(strKey.getBytes("UTF-8"));
	}

	private SecretKeySpec createKey(byte[] keyBytes) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] digest = sha.digest(keyBytes);
		byte[] key16 = Arrays.copyOf(digest, 16); // use AES-128
		return new SecretKeySpec(key16, "AES");
	}

	public byte[] encrypt(byte[] plain) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		byte[] iv = new byte[GCM_IV_LENGTH];
		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
		byte[] cipherText = cipher.doFinal(plain);
		byte[] out = new byte[iv.length + cipherText.length];
		System.arraycopy(iv, 0, out, 0, iv.length);
		System.arraycopy(cipherText, 0, out, iv.length, cipherText.length);
		return out;
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes("UTF-8")));
	}

	public byte[] decrypt(byte[] in) throws Exception {
		if (in.length < GCM_IV_LENGTH) {
			throw new IllegalArgumentException("Invalid input data");
		}
		byte[] iv = Arrays.copyOfRange(in, 0, GCM_IV_LENGTH);
		byte[] cipherText = Arrays.copyOfRange(in, GCM_IV_LENGTH, in.length);
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
		return cipher.doFinal(cipherText);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)), "UTF-8");
	}
}
