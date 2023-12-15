package lv.workathome.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LocalDriverManager {

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private LocalDriverManager() {
    }

    public static WebDriver getInstance() {
        if(driver.get() == null) {
            if(ConfigurationProperties.getConfiguration().getBoolean("run.locally")) {
                driver.set(new ChromeDriver());
            } else {
                driver.set(configureRemote());
            }
        } else {
            return driver.get();
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }

    @SneakyThrows
    public static RemoteWebDriver configureRemote() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-zlavik228-8d90e");
        sauceOptions.put("accessKey", "d4c75ebb-1fa4-4101-94d4-67c22465c0e6");
        sauceOptions.put("build", "<123>");
        sauceOptions.put("name", "<new>");
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);
        return new RemoteWebDriver(url, browserOptions);
    }
}
