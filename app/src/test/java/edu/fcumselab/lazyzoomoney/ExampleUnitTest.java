package edu.fcumselab.lazyzoomoney;

import org.junit.Test;

import io.appium.java_client.android.AndroidDriver;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private AndroidDriver driver;

    /*@Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Pixel 4 XL API30");
        //desiredCapabilities.setCapability("appPackage", "edu.fcumselab.lazyzoomoney");
        //desiredCapabilities.setCapability("appActivity", "Login");
        desiredCapabilities.setCapability("app", "C:\\Users\\yochun\\Desktop\\LazyZooMoney\\app\\release\\app-release.apk");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }*/

    @Test
    /*public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.fcumselab.lazyzoomoney", appContext.getPackageName());
    }*/
    public void sampleTest() {

        //MobileElement el1 = (MobileElement) driver.findElementByClassName("Login");

        /*MobileElement el8 = (MobileElement) driver.findElementById("edu.fcumselab.lazyzoomoney.MainActivity:id/btn_register");
        el8.click();
        MobileElement el9 = (MobileElement) driver.findElementById("edu.fcumselab.lazyzoomoney.Login:id/usernameR");
        el9.sendKeys("test");*/
        /*MobileElement el10 = (MobileElement) driver.findElementById("edu.fcumselab.lazyzoomoney:id/passwordR");
        el10.sendKeys("test");
        MobileElement el11 = (MobileElement) driver.findElementById("edu.fcumselab.lazyzoomoney:id/email");
        el11.sendKeys("test2@gmail.com");
        el11.click();
        MobileElement el12 = (MobileElement) driver.findElementById("edu.fcumselab.lazyzoomoney:id/registerbutton");
        el12.click();*/


    }

    /*@After
    public void tearDown() {
        driver.quit();
    }*/
}