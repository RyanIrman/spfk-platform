package my.ftsm.spfk.common.infrastructure;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Properties;

public final class PropertyUtil {

	private PropertyUtil() {
	}

	public static Properties getPropertiesFromFileOnClasspath(String fileName) {
		Properties result = new Properties();

		try (InputStream in = SystemPropertiesUtil.class
				.getResourceAsStream("/" + fileName);
				Reader r = new InputStreamReader(in, "UTF-8")) {
			result.load(r);
		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);

		}

		return result;
	}
}
