package config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class DriverFactory {

    private static final Properties BROWSERSTACK_PROPERTIES = loadProperties("browserstack.properties");

    private DriverFactory() {
    }

    public static AppiumDriver createDriver(String sessionName) throws MalformedURLException {
        if (isBrowserStackExecution()) {
            return createBrowserStackDriver(sessionName);
        }
        return createLocalDriver();
    }

    public static boolean isBrowserStackExecution() {
        return "browserstack".equalsIgnoreCase(System.getProperty("execution", "local"));
    }

    private static AppiumDriver createLocalDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "Pixel 5");
        capabilities.setCapability("appium:udid", "11281FDD4001CN");
        capabilities.setCapability("appium:platformVersion", "14");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.toucher");
        capabilities.setCapability("appium:appActivity", "com.toucher.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/");
        return new AppiumDriver(url, capabilities);
    }

    private static AppiumDriver createBrowserStackDriver(String sessionName) throws MalformedURLException {
        String username = requireEnv("BROWSERSTACK_USERNAME");
        String accessKey = requireEnv("BROWSERSTACK_ACCESS_KEY");

        Map<String, Object> browserStackOptions = new HashMap<>();
        browserStackOptions.put("userName", username);
        browserStackOptions.put("accessKey", accessKey);
        browserStackOptions.put("deviceName", BROWSERSTACK_PROPERTIES.getProperty("browserstack.device"));
        browserStackOptions.put("osVersion", BROWSERSTACK_PROPERTIES.getProperty("browserstack.os.version"));
        browserStackOptions.put("projectName", BROWSERSTACK_PROPERTIES.getProperty("browserstack.project"));
        browserStackOptions.put("buildName", BROWSERSTACK_PROPERTIES.getProperty("browserstack.build"));
        browserStackOptions.put("sessionName", sessionName);
        browserStackOptions.put("debug", true);
        browserStackOptions.put("networkLogs", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", BROWSERSTACK_PROPERTIES.getProperty("browserstack.app.package"));
        capabilities.setCapability("appium:appActivity", BROWSERSTACK_PROPERTIES.getProperty("browserstack.app.activity"));
        capabilities.setCapability("bstack:options", browserStackOptions);

        String app = BROWSERSTACK_PROPERTIES.getProperty("browserstack.app");
        if (app != null && !app.isBlank()) {
            capabilities.setCapability("appium:app", app.trim());
        }

        String hub = BROWSERSTACK_PROPERTIES.getProperty("browserstack.hub");
        URL url = new URL(String.format("https://%s:%s@%s", username, accessKey, hub.replace("https://", "")));
        return new AppiumDriver(url, capabilities);
    }

    private static Properties loadProperties(String resourceName) {
        Properties properties = new Properties();
        try (InputStream inputStream = DriverFactory.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IllegalStateException("Missing resource: " + resourceName);
            }
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load " + resourceName, exception);
        }
        return properties;
    }

    private static String requireEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Set the " + name + " environment variable before running BrowserStack tests.");
        }
        return value.trim();
    }
}
