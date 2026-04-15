# Appium Framework - Java

A comprehensive base Appium framework built in Java for mobile application testing with full CI/CD support.

## Features

- ✅ Page Object Model (POM) design pattern
- ✅ TestNG for test management
- ✅ Explicit and implicit waits
- ✅ Logging with Log4j
- ✅ CI/CD integration with GitHub Actions
- ✅ Cross-platform support (Android & iOS)
- ✅ Configurable via properties file

---

## Quick Start

### Prerequisites

- Java 11+
- Maven 3.6+
- Android SDK (for Android testing)
- Appium server installed globally: `npm install -g appium`

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd appium-framework
```

2. Install dependencies:
```bash
mvn clean install
```

3. Configure your device/emulator in `src/main/resources/config.properties`

4. Run tests:
```bash
mvn test
```

---

## Project Structure

```
appium-framework/
├── src/
│   ├── main/
│   │   ├── java/com/appium/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java          # Base test class with setup/teardown
│   │   │   ├── utils/
│   │   │   │   ├── CapabilityManager.java # Manages device capabilities
│   │   │   │   ├── AppiumServerManager.java # Manages Appium server lifecycle
│   │   │   │   └── WaitUtil.java          # Explicit wait utilities
│   │   │   └── pages/
│   │   │       └── BasePage.java          # Base page object class
│   │   └── resources/
│   │       ├── config.properties          # Configuration file
│   │       └── apk/
│   │           └── sample-app.apk         # Sample APK for testing
│   └── test/
│       ├── java/com/appium/tests/
│       │   └── SampleTest.java            # Sample test cases
│       └── resources/
├── .github/workflows/
│   └── ci.yml                            # GitHub Actions CI configuration
├── pom.xml                               # Maven configuration
├── testng.xml                            # TestNG suite configuration
└── README.md                             # This file
```

---

## Core Components

### 1. BaseTest.java
Entry point for all tests. Handles:
- **setUp()**: Initializes Appium driver before each test
- **tearDown()**: Cleans up and closes driver after each test
- **getDriver()**: Returns the active driver instance

```java
@BeforeClass
public void setUp() throws MalformedURLException {
    AppiumServerManager.startServer();
    DesiredCapabilities capabilities = CapabilityManager.getCapabilities();
    driver = new AppiumDriver(new URL(appiumUrl), capabilities);
}

@AfterClass
public void tearDown() {
    if (driver != null) driver.quit();
}
```

### 2. CapabilityManager.java
Manages device capabilities from config.properties:
- **getCapabilities()**: Returns DesiredCapabilities object
- **getAppiumUrl()**: Returns Appium server URL
- Supports Android & iOS configurations

### 3. AppiumServerManager.java
Manages Appium server lifecycle:
- **isServerRunning()**: Checks if server is alive
- **startServer()**: Starts Appium server automatically
- **stopServer()**: Gracefully stops the server

### 4. WaitUtil.java
Provides explicit wait mechanisms:
- **waitForElement()**: Waits for element presence
- **waitForElementToBeClickable()**: Waits for element clickability
- **waitForElementToDisappear()**: Waits for element invisibility
- **implicitWait()**: Thread sleep alternative

### 5. BasePage.java
Base Page Object class with common methods:
- **findElement()**: Finds element with explicit wait
- **click()**: Clicks an element
- **sendKeys()**: Enters text in input fields
- **getText()**: Retrieves element text
- **isElementDisplayed()**: Checks element visibility

### 6. SampleTest.java
Demonstrates test writing patterns:
- `testAppLaunch()`: Verifies app launches
- `testElementPresence()`: Validates UI elements
- `testTextInput()`: Tests input interactions

---

## Configuration (config.properties)

```properties
# Appium Server
appiumServerUrl=http://127.0.0.1:4723

# Device Details
platformName=Android
deviceName=emulator-5554
automationName=UiAutomator2

# App Details
appPath=src/main/resources/apk/sample-app.apk
appPackage=com.example.app
appActivity=.MainActivity

# Timeouts
implicitWaitTimeout=10
explicitWaitTimeout=15
```

### Configuration Parameters

| Parameter | Description |
|-----------|-------------|
| `appiumServerUrl` | Appium server URL and port |
| `platformName` | Android or iOS |
| `deviceName` | Device/emulator name |
| `automationName` | UiAutomator2 (Android) or XCUITest (iOS) |
| `appPath` | Path to APK/IPA file |
| `appPackage` | Android app package name |
| `appActivity` | Android app main activity |

---

## Test Execution Flow

### Test Lifecycle
```
1. setUp() [@BeforeClass]
   ├─ Start Appium server
   ├─ Load capabilities from config.properties
   └─ Initialize AppiumDriver

2. @Test Methods
   ├─ SampleTest.testAppLaunch()
   ├─ SampleTest.testElementPresence()
   └─ SampleTest.testTextInput()

3. tearDown() [@AfterClass]
   └─ Quit driver and clean up
```

### Method Flow in Tests
```
BaseTest
  └─ SampleTest
      ├─ Uses BasePage utilities (via inheritance)
      ├─ Calls driver.findElement(By)
      ├─ Uses WaitUtil for synchronization
      └─ Logs via Log4j
```

---

## Running Tests

### Local Execution
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=SampleTest

# Run specific test method
mvn test -Dtest=SampleTest#testAppLaunch
```

### CI/CD Execution (GitHub Actions)
```yaml
# Automatically runs on:
# - Push to main/develop branches
# - Pull requests to main/develop

# Workflow:
1. Checkout code
2. Setup Java 11
3. Build project (mvn clean compile)
4. Run tests (mvn test)
5. Upload test results as artifacts
```

---

## Utilities Reference

### WaitUtil Methods

```java
// Wait for element presence (default 10s)
WebElement element = WaitUtil.waitForElement(driver, By.id("id"));

// Wait for clickability
WebElement element = WaitUtil.waitForElementToBeClickable(driver, By.id("id"), 15);

// Wait for element to disappear
boolean disappeared = WaitUtil.waitForElementToDisappear(driver, By.id("id"));

// Thread sleep
WaitUtil.implicitWait(3); // waits 3 seconds
```

### BasePage Methods

```java
// Find element with wait
WebElement elem = findElement(By.id("elementId"));

// Click element
click(By.id("buttonId"));

// Input text
sendKeys(By.id("textFieldId"), "Your text");

// Get text
String text = getText(By.id("labelId"));

// Check visibility
boolean isVisible = isElementDisplayed(By.id("elementId"));
```

---

## Sample Test Implementation

```java
public class SampleTest extends BaseTest {
    
    @Test
    public void testUserLogin() {
        // Element locators
        By usernameField = By.id("com.app:id/username");
        By passwordField = By.id("com.app:id/password");
        By loginButton = By.id("com.app:id/login_btn");
        
        // Test execution
        driver.findElement(usernameField).sendKeys("testuser");
        driver.findElement(passwordField).sendKeys("password123");
        driver.findElement(loginButton).click();
        
        // Assertion
        Assert.assertTrue(driver.findElement(By.id("com.app:id/dashboard"))
            .isDisplayed(), "Login failed");
    }
}
```

---

## Logging

The framework uses **Log4j2** for comprehensive logging:

```java
private static final Logger logger = LogManager.getLogger(SampleTest.class);

logger.info("Test started");
logger.error("Test failed: " + e.getMessage());
```

Log output appears in console and can be configured in `log4j2.xml`

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Appium server won't start | Ensure Node.js and Appium are installed: `npm install -g appium` |
| Device not found | Check `deviceName` in config.properties |
| Elements not found | Verify element IDs match app structure |
| Timeout exceptions | Increase `explicitWaitTimeout` in config.properties |
| CI/CD failures | Check Java version (11+) and Maven cache |

---

## Extending the Framework

### Create New Page Object
```java
public class LoginPage extends BasePage {
    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.id("login");
    
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }
    
    public void login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }
}
```

### Create New Test Class
```java
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    
    @BeforeClass
    public void setup() throws MalformedURLException {
        super.setUp();
        loginPage = new LoginPage(driver);
    }
    
    @Test
    public void testValidLogin() {
        loginPage.login("user@example.com", "password");
        // Add assertions
    }
}
```

---

## Dependencies

- **Appium Java Client**: 8.6.0
- **Selenium**: 4.15.0
- **TestNG**: 7.8.1
- **Log4j**: 2.21.0
- **Gson**: 2.10.1

---

## CI/CD Tools

- **GitHub Actions**: Automated testing on push/PR
- **Maven**: Build and test management
- **TestNG**: Test framework and reporting

---

## Best Practices

✅ Always use Page Object Model  
✅ Use explicit waits instead of implicit  
✅ Keep locators in separate methods  
✅ Use descriptive test method names  
✅ Add logging for debugging  
✅ Clean up resources in tearDown()  
✅ Use config file for environment variables  

---

## License

This framework is open source and free to use.

---

## Support

For issues and questions:
1. Check troubleshooting section
2. Review Appium documentation: https://appium.io
3. Check Selenium documentation: https://www.selenium.dev

---

**Last Updated**: April 14, 2026  
**Version**: 1.0.0