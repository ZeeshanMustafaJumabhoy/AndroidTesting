import config.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Collections;

public class AppTest {

    private AppiumDriver driver;  // Instance variable instead of static

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException {
        driver = DriverFactory.createDriver(method.getName());
    }

    // Test method
    @Test(priority = 1)
    public void openMobileApp() throws InterruptedException {
        // Wait for App to launch
        Thread.sleep(2000);

        // Smoke test start
        LoginPage loginPage = new LoginPage(driver, this);
        ScorePage scorePage = new ScorePage(driver, this);
        AddUserPage addUserPage = new AddUserPage(driver, this);
        loginPage.login("test@outlook.com", "Hello123");
        scorePage.rulescreen();
        Thread.sleep(2000);
        String[] numbers = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12"};
        String[] They_membersname = {"Alice123", "Bob456", "Charlie789", "Dave101", "Eve202"};
        String[] WE_membersname = {"Alice123", "Bob456", "Charlie789", "Dave101", "Eve202"};
        // For Round 1
        scorePage.AddRoundName("Round 1");
        scorePage.AddScore(numbers);
        addUserPage.Add_WE_Members(WE_membersname, 5);
        addUserPage.Add_They_Members(They_membersname, 5);
        scorePage.changeround();
        Thread.sleep(2000);
        // For Round 2
        scorePage.AddRoundName("Round 2");
        scorePage.AddScore(numbers);
        addUserPage.Add_WE_Members(WE_membersname, 5);
        addUserPage.Add_They_Members(They_membersname, 5);
        scorePage.changeround();
        Thread.sleep(2000);
        // For Round 3
        scorePage.AddRoundName("Round 3");
        scorePage.AddScore(numbers);
        addUserPage.Add_WE_Members(WE_membersname, 5);
        addUserPage.Add_They_Members(They_membersname, 5);
      //  scorePage.back_from_current_screen();
        Thread.sleep(2000);
    //       scorePage.back_from_current_screen();
        Thread.sleep(2000);
        scorePage.back_from_current_screen();
        // Smoke test end
        System.out.println("Application started successfully");
    }

    @Test(priority = 2)
    public void openMobileApp1() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver, this);
        loginPage.login("test@outlook.com", "Hello123");
        System.out.println("Login Successfully");
    }

    @Test(priority = 2)
    public void openMobileApp2() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver, this);
        loginPage.login("test@outloo.com", "Hello123");
        System.out.println("Test Has to Be Failed");
    }

    @Test(priority = 3)
    public void openMobileApp3() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver, this);
        ScorePage scorePage = new ScorePage(driver,this);
        AddUserPage addUserPage = new AddUserPage(driver,this);
        loginPage.login("test@outlook.com", "Hello123");
        System.out.println("Test Has to Be Failed");
        scorePage.rulescreen();
        Thread.sleep(2000);
        String[] numbers = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12"};
        String[] They_membersname = {"Alice123", "Bob456", "Charlie789", "Dave101", "Eve202"};
        String[] WE_membersname = {"Alice123", "Bob456", "Charlie789", "Dave101", "E"};
        // For Round 1
        scorePage.AddRoundName("Round 1");
        scorePage.AddScore(numbers);
        addUserPage.Add_WE_Members(WE_membersname, 5);
        addUserPage.Add_They_Members(They_membersname, 5);
        System.out.println("Invalid Name Given");
    }

    @Test(priority = 4)
    public void openMobileApp4() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver, this);
        ScorePage scorePage = new ScorePage(driver,this);
        AddUserPage addUserPage = new AddUserPage(driver,this);
        loginPage.login("test@outlook.com", "Hello123");
        System.out.println("Test Has to Be Failed");
        scorePage.rulescreen();
        Thread.sleep(2000);
        // For Round 1
        scorePage.AddRoundName("Round 1");
        scorePage.changeround();
        Thread.sleep(2000);
        // For Round 2
        scorePage.AddRoundName("Round 2");
        scorePage.changeround();
        Thread.sleep(2000);
        // For Round 3
        scorePage.AddRoundName("Round 3");
        Thread.sleep(2000);
        // scorePage.back_from_current_screen();
        Thread.sleep(2000);
//        scorePage.back_from_current_screen();
        Thread.sleep(2000);
        scorePage.back_from_current_screen();
        // Smoke test end
        System.out.println("Application started successfully");

    }

    // AfterClass to close the driver
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Generic click method
    public void click(By by) {
        waitForLoaderToDisappear();
        WebElement element = waitUntilElementIsClickable(by);
        element.click();
    }

    // Generic write method
    public void write(By by, String data) throws InterruptedException {
        waitForLoaderToDisappear();
        WebElement element = waitUntilElementIsVisible(by);
        element.sendKeys(data);
    }

    // Wait for element to be visible
    public WebElement waitUntilElementIsVisible(By by) throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElement(by);
    }

    // Wait for element to be clickable
    public WebElement waitUntilElementIsClickable(By by) {
        return driver.findElement(by);
    }

    // Wait for loader to disappear
    public void waitForLoaderToDisappear() {
        try {
            Thread.sleep(200);  // Replace with actual wait logic if necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollUntilElementIsVisible(By by, int maxScrolls) {
        int scrollCount = 0;
        boolean elementFound = false;

        while (scrollCount < maxScrolls) {
            try {
                WebElement element = waitUntilElementIsVisible(by);
                if (element.isDisplayed()) {
                    elementFound = true;
                    System.out.println("Element found!");
                    break;
                }
            } catch (Exception e) {
            }

            performSwipe(0.2, 0.7, 0.2, 0.2, 500); // Swipe from bottom to top
            scrollCount++;
        }

        if (!elementFound) {
            System.out.println("Element not found after " + maxScrolls + " scrolls.");
        }
    }

    public void performSwipe(double startXRatio, double startYRatio, double endXRatio, double endYRatio, int durationMillis) {
        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;

        int startX = (int) (screenWidth * startXRatio);
        int startY = (int) (screenHeight * startYRatio);
        int endX = (int) (screenWidth * endXRatio);
        int endY = (int) (screenHeight * endYRatio);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMillis), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}
