package my.ftsm.spfk.common.infrastructure;



import java.util.Properties;

public final class SystemPropertiesUtil {

	private static volatile Properties instance;

	private SystemPropertiesUtil() {
	}

	public static int getIntProperty(String name) {
		return Integer.parseInt(getInstance().getProperty(name));
	}

	public static boolean getBooleanProperty(String name) {
		return Boolean.parseBoolean(getInstance().getProperty(name));
	}

	public static String getStringProperty(String name) {
		return getInstance().getProperty(name);
	}

	public static Properties getInstance() {
		if (instance == null) {
			instance = PropertyUtil
					.getPropertiesFromFileOnClasspath("system.properties");
		}

		return instance;
	}

}
