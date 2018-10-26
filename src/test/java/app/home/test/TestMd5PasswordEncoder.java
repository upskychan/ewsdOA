package app.home.test;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class TestMd5PasswordEncoder {
	public static void main(String[] args) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String account = "admin";
		String psw = "81dc9bdb52d04dc20036dbd8313ed055";
		String password = md5.encodePassword(psw, account);
		System.out.println(password);//528777384d344684c78d62c07b4c302e
	}
}
