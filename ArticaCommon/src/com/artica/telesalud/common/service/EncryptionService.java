package com.artica.telesalud.common.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {
	
	public static final String CHARSET = "UTF8";
	
	public String encrypt(String message, String algorithmCode){
		try{
			byte[] defaultBytes = message.getBytes(CHARSET);
			MessageDigest algorithm = MessageDigest.getInstance(algorithmCode);
			algorithm.reset();
			byte messageDigest[] = algorithm.digest( defaultBytes );
			return new String(Base64Coder.encode(messageDigest));
		}catch(NoSuchAlgorithmException nsae){
			nsae.printStackTrace();
		    return message;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return message;
		} 
	}
}
