package my.ftsm.spfk.common.infrastructure;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public final class CryptoUtil {

	

	private static byte[] secretKey;

	static {
		String aesKey = SystemPropertiesUtil.getStringProperty("security.aes_key");
		secretKey = aesKey.getBytes();
	}

	private CryptoUtil() {
	}

	public static byte[] encrypt(byte[] byteArray) {
		byte[] result = null;

		try {

			SecretKeySpec skeySpec = new SecretKeySpec(secretKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			result = cipher.doFinal(byteArray);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static byte[] decrypt(byte[] byteArray) {
		byte[] result = null;

		try {
			SecretKeySpec skeySpec = new SecretKeySpec(secretKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE,
					new SecretKeySpec(skeySpec.getEncoded(), "AES"));

			result = cipher.doFinal(byteArray);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

}

