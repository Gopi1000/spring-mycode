package com.scube.techboot.security;

import org.springframework.security.crypto.password.PasswordEncoder;



public class CustomPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence Password) {
		// TODO Auto-generated method stub
		String password = String.valueOf(Password);
		try {
			return com.scube.techboot.utils.EncryptAndDecrypt
			.encrypt(password,
					com.scube.techboot.utils.EncryptAndDecrypt.TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean matches(CharSequence Password, String dbPassword) {
		String password = String.valueOf(Password);
		String checkPassword = "";
		try {
			checkPassword = com.scube.techboot.utils.EncryptAndDecrypt
					.decrypt(dbPassword,
							com.scube.techboot.utils.EncryptAndDecrypt.TOKEN);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return password.equals(checkPassword);
	}


}
