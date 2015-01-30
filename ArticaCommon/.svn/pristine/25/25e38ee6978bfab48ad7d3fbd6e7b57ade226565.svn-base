package com.artica.telesalud.common.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionServiceTest {

	@Test
	public void testEncrypt() {
		EncryptionService service = new EncryptionService();
		
		String str = service.encrypt("admin", "MD5");
		
		assertNotSame(str, "admin");
	}

}
