import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.IOSMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class UiAutomatorTest {
    enum Platform {Android};
    Platform platform = Platform.Android;
    private static AppiumDriver driver;
    private MobileObject mobileObject;
    private URL getUrl(){
        try {
            return new URL("http://127.0.0.1:4723");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(DEVICE_NAME, "sameName");
        desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(ENSURE_WEBVIEWS_HAVE_PAGES, true);
        desiredCapabilities.setCapability(NATIVE_WEB_SCREENSHOT, true);
        desiredCapabilities.setCapability(NEW_COMMAND_TIMEOUT, 3600);
        desiredCapabilities.setCapability(CONNECT_HARDWARE_KEYBOARD, true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        mobileObject = new MobileObject(driver);
    }

    @Test
    public void testChangeText() {
        mobileObject.btn1.isDisplayed();
        mobileObject.btn1.click();
        mobileObject.btn1.sendKeys("1234");

        mobileObject.btn2.isDisplayed();
        mobileObject.btn2.click();

        mobileObject.btn1.isDisplayed();
        mobileObject.btn1.click();
        mobileObject.btn1.sendKeys("   ");

        mobileObject.btn2.isDisplayed();
        mobileObject.btn2.click();

        mobileObject.text.isDisplayed();
        
        Assertions.assertEquals("1234", mobileObject.text.getText());
    }

    @Test
    public void testBlankText(){
        mobileObject.btn1.isDisplayed();
        mobileObject.btn1.click();
        String searchText = "1234";
        mobileObject.btn1.sendKeys(searchText);

        mobileObject.activity.isDisplayed();
        mobileObject.activity.click();

        var el4 = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + searchText + "\")"));

        Assertions.assertEquals("1234", el4.getText());
    }

    @AfterAll
    public static void tearDown() {
         driver.quit();
    }
}